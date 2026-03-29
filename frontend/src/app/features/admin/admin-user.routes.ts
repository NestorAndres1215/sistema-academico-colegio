import { Routes } from '@angular/router';

export const ADMIN_USER_ROUTES: Routes = [

  {
    path: '',
    loadComponent: () => import('../admin/admin-home/admin-home')
      .then(m => m.AdminHome)
  },

];