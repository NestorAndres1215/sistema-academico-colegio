import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';

import { AuthService } from '../service/auth.service';

export const noAuthGuard: CanActivateFn = async (route) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const user = await firstValueFrom(authService.getCurrentUser()).catch(() => null);

  if (!user) {
    return true;
  }

  if (!user.role) {
    return true;
  }

  const home = authService.getHomeByRole(user.role);

  return router.parseUrl(home);
};
