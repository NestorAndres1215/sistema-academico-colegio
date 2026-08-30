import { Routes } from '@angular/router';

export const USERS_ROUTES: Routes = [
    {
        path: 'listado-usuario',
        loadComponent: () => import('./user-list/user-list').then((m) => m.UserList),
    },
    {
        path: 'registro-usuario',
        loadComponent: () => import('./user-create/user-create').then((m) => m.UserCreate),
    },
    {
        path: 'busqueda-avanzada',
        loadComponent: () => import('./user-search/user-search').then((m) => m.UserSearch),
    },
    {
        path: 'cambio-masivo-estado',
        loadComponent: () =>
            import('./user-status-mass/user-status-mass').then((m) => m.UserStatusMass),
    },
    {
        path: ':id/edit',
        loadComponent: () => import('./user-edit/user-edit').then((m) => m.UserEdit),
    },
    {
        path: ':id',
        loadComponent: () => import('./user-detail/user-detail').then((m) => m.UserDetail),
    },
];
