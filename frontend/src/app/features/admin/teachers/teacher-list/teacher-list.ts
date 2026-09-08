import { Component, inject, signal } from '@angular/core';

import { PageHeader } from '../../../../shared/ui/page-header/page-header';
import { TeacherResponse } from '../../../../core/modules/teacher/models/teacher-response';
import { Router } from '@angular/router';
import { TeacherService } from '../../../../core/modules/teacher/services/teacher.service';
import { SelectFilterOption } from '../../../../core/models/select-filter-option';
import { TableColumn } from '../../../../shared/models/table.model';
import { firstValueFrom } from 'rxjs';
import { TableAction } from '../../../../shared/ui/data-table/data-table.types';
import { Search } from '../../../../shared/ui/search/search';
import { DataTable } from '../../../../shared/ui/data-table/data-table';
import { Pagination } from '../../../../shared/ui/pagination/pagination';

@Component({
  imports: [ PageHeader, Search, DataTable, Pagination],
  selector: 'app-teacher-list',
  styleUrl: './teacher-list.css',
  templateUrl: './teacher-list.html',
})
export class TeacherList {
  private readonly teacherService = inject(TeacherService);
  private readonly router = inject(Router);
  readonly teachers = signal<TeacherResponse[]>([]);
  readonly totalItems = signal(0);
  readonly currentPage = signal(1);
  readonly pageSize = signal(5);
  readonly searchTerm = signal('');
  readonly statusFilter = signal('ACTIVE');
  readonly icon = 'school';
  readonly title = 'Gestión de profesores';
  readonly subtitle = 'Búsqueda, filtros y administración de profesores del sistema';
  readonly tableActions: TableAction[] = ['detail', 'edit'];
  
  async ngOnInit(): Promise<void> {
    await this.loadTeachers();
  }


  readonly statusOptions: SelectFilterOption[] = [
    { value: '', label: 'Todos los estados' },
    { value: 'active', label: 'Activo' },
    { value: 'inactive', label: 'Inactivo' },
  ];

  readonly columns: TableColumn[] = [
    { key: 'code', label: 'Código', sortable: true },
    { key: 'name', label: 'Nombres', sortable: true },
    { key: 'lastName', label: 'Apellidos', sortable: true },
    { key: 'dni', label: 'DNI', sortable: true },
  ];

  async loadTeachers(): Promise<void> {
    const response = await firstValueFrom(
      this.teacherService.findByAllStatus(
        this.currentPage() - 1,
        this.pageSize(),
        this.searchTerm(),
        this.statusFilter(),
      ),
    );

    this.teachers.set(response.content);

    this.totalItems.set(response.totalElements);
  }

  onSearch(term: string): void {
    this.searchTerm.set(term);
    this.currentPage.set(1);
    this.loadTeachers();
  }

  onStatusFilterChange(status: string): void {
    this.statusFilter.set(status);
    this.currentPage.set(1);
    this.loadTeachers();
  }

  onPageChange(page: number): void {
    this.currentPage.set(page);
    this.loadTeachers();
  }

  onPageSizeChange(size: number): void {
    this.pageSize.set(size);
    this.currentPage.set(1);
    this.loadTeachers();
  }


  onDetail(userResponse: TeacherResponse): void {
    this.router.navigate(['/admin/usuarios', userResponse.id]);
  }

  onEdit(userResponse: TeacherResponse): void {
    this.router.navigate(['/admin/usuarios', userResponse.id, 'edit']);
  }
}
