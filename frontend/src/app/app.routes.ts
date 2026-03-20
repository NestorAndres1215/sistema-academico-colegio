import { Routes } from '@angular/router';
import { Principal } from './features/principal/principal';

export const routes: Routes = [

    {
        path: '',
        component: Principal,
        pathMatch: 'full'
    },
    {
        path: 'auth',
        loadChildren: () => import('./features/auth/auth.routes').then(m => m.AUTHENTICATION_ROUTES),
        //  canActivate: [NoAuthGuard]
    },
];
