import { Routes } from '@angular/router';

export const CONFIGURATION_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () => import('./settings/settings').then((m) => m.Settings),
  },
  {
    path: 'cambiar-tema',
    loadComponent: () => import('./theme/theme').then((m) => m.Theme),
  },
  {
    path: 'company',
    loadComponent: () => import('./company/company').then((m) => m.Company),
  },
  {
    path: 'ayuda',
    loadComponent: () => import('./help-page/help-page').then((m) => m.HelpPage),
  },
];
