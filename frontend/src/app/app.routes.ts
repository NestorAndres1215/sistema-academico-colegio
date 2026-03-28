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

    /*
         {
        path: 'admin',
        canActivate: [RoleGuard],
        data: { roles: [ROLES.ROLE_ADMINISTRATOR] },
        loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule)
      },
    
      {
        path: 'inicio',
        canActivate: [RoleGuard],
        data: { roles: [ROLES.ROLE_STUDENT] },
        loadChildren: () => import('./modules/student/student.module').then(m => m.StudentModule)
      },
    
      {
        path: 'teacher',
        canActivate: [RoleGuard],
        data: { roles: [ROLES.ROLE_TEACHER] },
        loadChildren: () => import('./modules/teacher/teacher.module').then(m => m.TeacherModule)
      },
    
      {
        path: 'staff',
        canActivate: [RoleGuard],
        data: { roles: [ROLES.ROLE_STAFF] },
        loadChildren: () => import('./modules/staff/staff.module').then(m => m.StaffModule)
      },
    
      {
        path: 'guardian',
        canActivate: [RoleGuard],
        data: { roles: [ROLES.ROLE_GUARDIAN] },
        loadChildren: () => import('./modules/guardian/guardian.module').then(m => m.GuardianModule)
      }*/
];
