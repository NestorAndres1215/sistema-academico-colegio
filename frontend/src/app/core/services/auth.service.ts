import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private backendUrl = environment.baseUrl;

  private loginStatusSubject = new BehaviorSubject<boolean>(this.isLoggedIn());
  public loginStatus$ = this.loginStatusSubject.asObservable();

  constructor(
    private router: Router,
    private http: HttpClient
  ) {}

  // 🔐 LOGIN
  generateToken(loginData: any): Observable<any> {
    return this.http.post(`${this.backendUrl}/auth/generate-token`, loginData);
  }

  // 👤 USER
  getCurrentUser(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/auth/current-user`);
  }

  // 🔑 TOKEN
  get token(): string | null {
    return localStorage.getItem('jwt');
  }

  setToken(token: string): void {
    localStorage.setItem('jwt', token);
    this.loginStatusSubject.next(true);
  }

  // 👤 USER STORAGE
  saveUser(user: any): void {
    localStorage.setItem('user', JSON.stringify(user));
  }

  getUser(): any {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }

  getUserRole(): string {
    const user = this.getUser();
    return user?.roles?.[0]?.name || '';
  }

  // ✅ ESTADO
  isLoggedIn(): boolean {
    return !!this.token;
  }

  // 🚪 LOGOUT
  logout(): void {
    localStorage.removeItem('jwt');
    localStorage.removeItem('user');
    this.loginStatusSubject.next(false);
    this.router.navigate(['/auth/login']);
  }
}