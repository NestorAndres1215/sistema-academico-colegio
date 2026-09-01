import { Routes } from '@angular/router';
import { Login } from '../auth/login/login';
import { noAuthGuard } from '../../core/auth/guards/no-auth.guard';

export const AUTHENTICATION_ROUTES: Routes = [
  {
    path: 'login',
    canActivate: [noAuthGuard],
    component: Login,
  },
  {
    path: 'olvidar-contrasena',
    loadComponent: () => import('./forgot-password/forgot-password')
      .then(m => m.ForgotPassword)
  },

  {
    path: 'codigo-verificacion',
    loadComponent: () => import('./verification-code/verification-code')
      .then(m => m.VerificationCode)
  },

  {
    path: 'cambiar-contrasenia',
    loadComponent: () => import('./change-password/change-password')
      .then(m => m.ChangePassword)
  },
];
