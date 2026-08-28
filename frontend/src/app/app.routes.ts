import { Routes } from '@angular/router';
import { roleGuard } from './core/auth/guards/role.guard';
import { ROLES } from './core/auth/constants/roles';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'auth/login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    redirectTo: 'auth/login',
    pathMatch: 'full',
  },
  {
    path: 'auth',
    loadChildren: () => import('./features/auth/auth.routes').then((m) => m.AUTHENTICATION_ROUTES),
  },

  {
    path: '',
    loadComponent: () => import('./core/layout/layout').then((m) => m.Layout),

    children: [
      {
        path: 'admin',
        canActivate: [roleGuard],
        data: { roles: [ROLES.ROLE_ADMIN] },
        loadChildren: () => import('./features/admin/admin.routes').then((m) => m.ADMIN_ROUTES),
      },
    ],
  },
];
