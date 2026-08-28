import { Routes } from '@angular/router';

export const CONFIGURATION_ROUTES: Routes = [
  {
    path: 'company',
    loadComponent: () => import('./company/company').then((m) => m.Company),
  },
];
