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

    const token = localStorage.getItem('jwt');

    // 🔐 1. No hay token → login
    if (!token) {
      console.log("No token → login");
      return this.router.parseUrl('/auth/login');
    }

    const userRole = this.authService.getUserRole();

    // 🔥 2. Validar roles de la ruta
    const allowedRoles: string[] = route.data?.['roles'] || [];

    console.log("USER ROLE:", userRole);
    console.log("ALLOWED ROLES:", allowedRoles);


    if (allowedRoles.map(r => r.trim()).includes(userRole)) {
      console.log("Acceso permitido");
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

    const ruta = rutasPorRol[role] || '/auth/login';

    console.log('Redirigiendo a:', rutasPorRol[role]);

    return this.router.parseUrl(ruta);
  }
}