import { Routes } from '@angular/router';

export const TEACHERS_ROUTES: Routes = [
  {
    path: 'listar',
    loadComponent: () => import('./teacher-list/teacher-list').then((m) => m.TeacherList),
  },
  {
    path: 'registro',
    loadComponent: () => import('./teacher-create/teacher-create').then((m) => m.TeacherCreate),
  },

  {
    path: ':id/edit',
    loadComponent: () => import('./teacher-edit/teacher-edit').then((m) => m.TeacherEdit),
  },
  {
    path: ':id',
    loadComponent: () => import('./teacher-detail/teacher-detail').then((m) => m.TeacherDetail),
  },
];
