import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { ROLES } from '../constants/roles';
import { AuthService } from '../services/auth.service';

@Injectable({
    providedIn: 'root'
})
export class NoAuthGuard implements CanActivate {

    constructor(
        private authService: AuthService,
        private router: Router
    ) { }

    canActivate(): boolean | UrlTree {

        if (!this.authService.isLoggedIn()) {
            return true;
        }

        const role = this.authService.getUserRole();

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