import { Routes } from "@angular/router";

export const ADMIN_ROUTES: Routes = [

    {
        path: 'admin',
        loadChildren: () =>
            import('../admin/admin-user.routes')
                .then(m => m.ADMIN_USER_ROUTES)
    },
];