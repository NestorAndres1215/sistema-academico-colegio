import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

import { firstValueFrom } from 'rxjs';
import { AuthService } from '../service/auth.service';


export const roleGuard: CanActivateFn = async (route) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  try {
    const user = await firstValueFrom(authService.getCurrentUser());

    if (!user) {
      return router.parseUrl('/auth/login');
    }

    const role = user.role;

    if (!role) {
      return router.parseUrl('/auth/login');
    }

    const allowedRoles: string[] = route.data?.['roles'] ?? [];

    if (!allowedRoles.includes(role)) {
      return router.parseUrl('/auth/login');
    }

    return true;
  } catch (error) {
    return router.parseUrl('/auth/login');
  }
};
