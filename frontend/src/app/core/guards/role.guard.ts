import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router, UrlTree } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot): boolean | UrlTree {

    if (!this.authService.isLoggedIn()) {
      return this.router.parseUrl('/auth/login');
    }

    const userRole = this.authService.getUserRole();
    const allowedRoles = route.data['roles'] as string[];

    if (allowedRoles.includes(userRole)) {
      return true;
    }

    return this.redirectByRole(userRole);
  }

  private redirectByRole(role: string): UrlTree {

    const rutasPorRol: Record<string, string> = {
      'ROLE_ADMINISTRATOR': '/admin',
      'ROLE_STUDENT': '/inicio',
      'ROLE_STAFF': '/staff',
      'ROLE_TEACHER': '/teacher',
      'ROLE_GUARDIAN': '/guardian'
    };

    return this.router.parseUrl(rutasPorRol[role] || '/auth/login');
  }
}