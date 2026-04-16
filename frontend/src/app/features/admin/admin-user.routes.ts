import { Routes } from '@angular/router';

export const ADMIN_USER_ROUTES: Routes = [


  {
    path: 'listado-usuario',
    loadComponent: () => import('./general-management/user-management/user-list/user-list')
      .then(m => m.UserList)
  },
  {
    path: 'detalle-usuario/:id',
    loadComponent: () => import('./general-management/user-management/user-detail/user-detail')
      .then(m => m.UserDetail)
  },

  {
    path: 'registro-usuario',
    loadComponent: () => import('./general-management/user-management/user-create/user-create')
      .then(m => m.UserCreate)
  },

  {
    path: 'asignacion-rol',
    loadComponent: () => import('../admin/general-management/user-management/role-list/role-list')
      .then(m => m.RoleList)
  },

  {
    path: 'estado-cuenta',
    loadComponent: () => import('../admin/general-management/user-management/user-inactive/user-inactive')
      .then(m => m.UserInactive)
  },

  {
    path: 'restablecer-contrasena',
    loadComponent: () => import('../admin/general-management/user-management/reset-password/reset-password')
      .then(m => m.ResetPassword)
  },
];