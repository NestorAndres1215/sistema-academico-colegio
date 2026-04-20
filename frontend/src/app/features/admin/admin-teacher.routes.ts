import { Routes } from "@angular/router";

export const ADMIN_TEACHER_ROUTES: Routes = [
    {
        path: 'datos',
        loadComponent: () => import('./general-management/teachers/teacher-list/teacher-list')
            .then(m => m.TeacherList)
    },
    {
        path: 'registro',
        loadComponent: () => import('./general-management/teachers/teacher-create/teacher-create')
            .then(m => m.TeacherCreate)
    },
    {
        path: 'detalle-profesor/:id',
        loadComponent: () => import('./general-management/teachers/teacher-detail/teacher-detail')
            .then(m => m.TeacherDetail)
    },
    {
        path: 'actualizar-profesor/:id',
        loadComponent: () => import('./general-management/teachers/teacher-update/teacher-update')
            .then(m => m.TeacherUpdate)
    },
    {
        path: 'observaciones',
        loadComponent: () => import('./general-management/observation/observation-teacher/observation-teacher')
            .then(m => m.ObservationTeacher)
    },
    {
        path: 'observaciones/lista/:id',
        loadComponent: () => import('./general-management/observation/observation-list/observation-list')
            .then(m => m.ObservationList)
    },

];