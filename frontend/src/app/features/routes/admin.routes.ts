import { Routes } from "@angular/router";

export const ADMIN_ROUTES: Routes = [

        {
        path: 'admin',
        loadComponent: () => import('../admin/admin-main/admin-main')
            .then(m => m.AdminMain)
    },

    {
        path: 'usuarios',
        loadChildren: () =>
            import('../admin/admin-user.routes')
                .then(m => m.ADMIN_USER_ROUTES)
    },
];