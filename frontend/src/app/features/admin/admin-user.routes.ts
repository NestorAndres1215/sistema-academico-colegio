import { Routes } from '@angular/router';

export const ADMIN_USER_ROUTES: Routes = [


  {
    path: 'listado-usuario',
    loadComponent: () => import('./general-management/users/user-list/user-list')
      .then(m => m.UserList)
  },
  {
    path: 'detalle-usuario/:id',
    loadComponent: () => import('./general-management/users/user-detail/user-detail')
      .then(m => m.UserDetail)
  },

  {
    path: 'registro-usuario',
    loadComponent: () => import('./general-management/users/user-create/user-create')
      .then(m => m.UserCreate)
  },

  {
    path: 'asignacion-rol',
    loadComponent: () => import('./general-management/roles/role-list/role-list')
      .then(m => m.RoleList)
  },

  {
    path: 'estado-cuenta',
    loadComponent: () => import('./general-management/users/user-inactive/user-inactive')
      .then(m => m.UserInactive)
  },

  {
    path: 'restablecer-contrasena',
    loadComponent: () => import('./general-management/security/reset-password/reset-password')
      .then(m => m.ResetPassword)
  },
];