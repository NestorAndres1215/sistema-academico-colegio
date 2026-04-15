import { Routes } from "@angular/router";

export const ADMIN_TEACHER_ROUTES: Routes = [
    {
        path: 'datos',
        loadComponent: () => import('./general-management/school-staff/teacher/personal-information/personal-information')
            .then(m => m.PersonalInformation)
    },
    {
        path: 'cursos',
        loadComponent: () => import('./general-management/school-staff/teacher/teacher-course-assignment/teacher-course-assignment')
            .then(m => m.TeacherCourseAssignment)
    },
    {
        path: 'registro',
        loadComponent: () => import('./general-management/school-staff/teacher/teacher-register/teacher-register')
            .then(m => m.TeacherRegister)
    },
    {
        path: 'detalle-profesor/:id',
        loadComponent: () => import('./general-management/school-staff/teacher/teacher-detail/teacher-detail')
            .then(m => m.TeacherDetail)
    },


];