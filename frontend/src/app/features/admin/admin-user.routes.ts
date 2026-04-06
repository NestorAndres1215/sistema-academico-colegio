import { Routes } from '@angular/router';

export const ADMIN_USER_ROUTES: Routes = [


  {
    path: 'listado-usuario',
    loadComponent: () => import('./general-management/users/module-users-list/user-list/user-list')
      .then(m => m.UserList)
  },
  {
    path: 'detalle-usuario/:id',
    loadComponent: () => import('./general-management/users/module-users-list/user-detail/user-detail')
      .then(m => m.UserDetail)
  },

  {
    path: 'registro-usuario',
    loadComponent: () => import('../admin/general-management/users/user-registration/user-registration')
      .then(m => m.UserRegistration)
  },

  {
    path: 'asignacion-rol',
    loadComponent: () => import('../admin/general-management/users/role-assignment/role-assignment')
      .then(m => m.RoleAssignment)
  },

  {
    path: 'estado-cuenta',
    loadComponent: () => import('../admin/general-management/users/account-status/account-status')
      .then(m => m.AccountStatus)
  },

  {
    path: 'restablecer-contrasena',
    loadComponent: () => import('../admin/general-management/users/password-reset/password-reset')
      .then(m => m.PasswordReset)
  },
];