import { Routes } from '@angular/router';

export const ADMIN_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () => import('./admin-dashboard/admin-dashboard').then((m) => m.AdminDashboard),
  },
  {
    path: 'usuarios',
    loadChildren: () => import('./users/user.routes').then((m) => m.USERS_ROUTES),
  },
];
