import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router, UrlTree } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { ROLES } from '../constants/roles';

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
      [ROLES.ROLE_ADMINISTRATOR]: '/admin',
      [ROLES.ROLE_STUDENT]: '/inicio',
      [ROLES.ROLE_STAFF]: '/staff',
      [ROLES.ROLE_TEACHER]: '/teacher',
      [ROLES.ROLE_GUARDIAN]: '/guardian'
    };

    return this.router.parseUrl(rutasPorRol[role] || '/auth/login');
  }
}