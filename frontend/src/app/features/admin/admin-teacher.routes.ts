import { Routes } from "@angular/router";

export const ADMIN_TEACHER_ROUTES: Routes = [
    {
        path: 'datos',
        loadComponent: () => import('./general-management/teacher-management/teacher-list/teacher-list')
            .then(m => m.TeacherList)
    },
    {
        path: 'registro',
        loadComponent: () => import('./general-management/teacher-management/teacher-create/teacher-create')
            .then(m => m.TeacherCreate)
    },
    {
        path: 'detalle-profesor/:id',
        loadComponent: () => import('./general-management/teacher-management/teacher-detail/teacher-detail')
            .then(m => m.TeacherDetail)
    },
    {
        path: 'actualizar-profesor/:id',
        loadComponent: () => import('./general-management/teacher-management/teacher-update/teacher-update')
            .then(m => m.TeacherUpdate)
    },
    {
        path: 'observaciones',
        loadComponent: () => import('./general-management/teacher-management/teacher-observation/teacher-observation')
            .then(m => m.TeacherObservation)
    },
    

];