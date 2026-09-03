import { HttpClient } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { catchError, Observable, of, shareReplay, Subject, tap } from 'rxjs';

import { environment } from '../../../../environments/environment';
import { LoginRequest } from '../models/login-response';
import { TokenResponse } from '../models/token-response';
import { UserResponse } from '../../modules/user/models/user-response';

@Service()
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly backendUrl = environment.apiUrl;

  readonly loginStatusSubject = new Subject<boolean>();

  private currentUser$: Observable<UserResponse | null> | null = null;

  generateToken(loginData: LoginRequest): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(`${this.backendUrl}/auth/generate-token`, loginData);
  }

  getCurrentUser(): Observable<UserResponse | null> {
    if (!this.currentUser$) {
      this.currentUser$ = this.http.get<UserResponse>(`${this.backendUrl}/auth/current-user`).pipe(
        catchError(() => of(null)),
        shareReplay(1),
      );
    }

    return this.currentUser$;
  }

  clearCurrentUser(): void {
    this.currentUser$ = null;
  }

  logout(): Observable<void> {
    return this.http
      .post<void>(`${this.backendUrl}/auth/logout`, {}, { withCredentials: true })
      .pipe(
        tap(() => {
          this.clearCurrentUser();
          this.loginStatusSubject.next(false);
        }),
        catchError((error) => {
          this.clearCurrentUser();
          this.loginStatusSubject.next(false);

          return of(void 0);
        }),
      );
  }

  getHomeByRole(role: string): string {
    const map: Record<string, string> = {
      ROLE_ADMIN: '/admin',
      ROLE_GUARDIAN: '/guardian',
      ROLE_STAFF: '/staff',
      ROLE_TEACHER: '/teacher',
      ROLE_STUDENT: '/student',
    };

    return map[role] ?? '/';
  }
}
