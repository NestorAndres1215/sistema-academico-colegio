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
    path: 'busqueda-avanzada',
    loadComponent: () => import('./teacher-search/teacher-search').then((m) => m.TeacherSearch),
  },
  {
    path: 'cambio-masivo-estado',
    loadComponent: () =>
      import('./teacher-status-mass/teacher-status-mass').then((m) => m.TeacherStatusMass),
  },
  {
    path: 'contracto/:code',
    loadComponent: () =>
      import('./teacher-contract/teacher-contract-list/teacher-contract-list').then((m) => m.TeacherContract),
  },

  /*
  {
    path: ':id/edit',
    loadComponent: () => import('./teacher-edit/teacher-edit').then((m) => m.TeacherEdit),
  },
  {
    path: ':id',
    loadComponent: () => import('./teacher-detail/teacher-detail').then((m) => m.TeacherDetail),
  },*/
];
