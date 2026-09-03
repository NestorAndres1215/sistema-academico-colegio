import { Component, signal } from '@angular/core';
import { BreadCrumb } from '../../../../shared/ui/bread-crumb/bread-crumb';
import { PageHeader } from '../../../../shared/ui/page-header/page-header';
import { BreadcrumbItem } from '../../../../shared/models/breadcrumb.model';

@Component({
  imports: [BreadCrumb, PageHeader],
  selector: 'app-teacher-list',
  styleUrl: './teacher-list.css',
  templateUrl: './teacher-list.html',
})
export class TeacherList {
  readonly breadcrumbs = signal<BreadcrumbItem[]>([]);

  readonly icon = 'school';
  readonly title = 'Gestión de profesores';
  readonly subtitle = 'Búsqueda, filtros y administración de profesores del sistema';

  async ngOnInit(): Promise<void> {
    await this.initUser();
  }

  private async initUser(): Promise<void> {
    this.breadcrumbs.set([
      { label: 'Inicio', href: '/admin' },
      { label: 'Profesores' },
      { label: 'Listado de Profesores' },
    ]);
  }
}
