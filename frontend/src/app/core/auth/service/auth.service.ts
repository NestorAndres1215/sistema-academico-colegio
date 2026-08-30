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
    console.log('URL:', `${this.backendUrl}/auth/generate-token`);
    console.log('BODY:', loginData);

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

  logout(): Observable<unknown> {
    return this.http.post(`${this.backendUrl}/auth/logout`, {}).pipe(
      tap(() => {
        this.clearCurrentUser();
        this.loginStatusSubject.next(false);
      }),
      catchError((error) => {
        console.error('Error cerrando sesión:', error);

        this.clearCurrentUser();
        this.loginStatusSubject.next(false);

        return of(null);
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
