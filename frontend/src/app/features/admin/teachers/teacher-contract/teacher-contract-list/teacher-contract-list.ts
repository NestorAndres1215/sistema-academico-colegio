import { Component, inject, signal } from '@angular/core';
import { BreadcrumbItem } from '../../../../../shared/models/breadcrumb.model';
import { BreadCrumb } from '../../../../../shared/ui/bread-crumb/bread-crumb';
import { PageHeader } from '../../../../../shared/ui/page-header/page-header';
import { ActivatedRoute } from '@angular/router';
import { TeacherContractFilter } from '../../../../../core/modules/teacher/models/teacher-contract-filter';
import { firstValueFrom } from 'rxjs';
import { TeacherContractService } from '../../../../../core/modules/teacher/services/teacher-contract.service';
import { DataTable } from '../../../../../shared/ui/data-table/data-table';
import { Pagination } from '../../../../../shared/ui/pagination/pagination';
import { TableColumn } from '../../../../../shared/models/table.model';
import { TeacherContractResponse } from '../../../../../core/modules/teacher/models/teacher-contract-response';
import { TableAction } from '../../../../../shared/ui/data-table/data-table.types';
import { Button } from "../../../../../shared/ui/button/button";

@Component({
  imports: [BreadCrumb, PageHeader, DataTable, Pagination, Button],
  selector: 'app-teacher-contract-list',
  styleUrl: './teacher-contract-list.css',
  templateUrl: './teacher-contract-list.html',
})
export class TeacherContract {
  readonly breadcrumbs = signal<BreadcrumbItem[]>([]);
  private readonly teacherContractService = inject(TeacherContractService);

  readonly icon = 'description';
  readonly title = 'Contratos de profesores';
  readonly subtitle = 'Consulta y gestiona los contratos de los profesores.';
  private readonly route = inject(ActivatedRoute);
  readonly code = signal<string>('');
  readonly teacherContract = signal<TeacherContractResponse[]>([]);
  readonly totalItems = signal(0);
  readonly startDate = signal<Date | null>(null);
  readonly endDate = signal<Date | null>(null);
  readonly tableActions: TableAction[] = ['download', 'detail'];
  readonly currentPage = signal(1);
  readonly pageSize = signal(10);
  readonly sort = signal<'asc' | 'desc'>('desc');

  async ngOnInit(): Promise<void> {
    this.code.set(this.route.snapshot.paramMap.get('code') ?? '');
    await this.initUser();
    await this.loadTeacherContract();
  }

  private async initUser(): Promise<void> {
    this.breadcrumbs.set([
      { label: 'Inicio', href: '/admin' },
      { label: 'Profesores' },
      { label: 'Búsqueda Profesores', href: '/admin/profesores/busqueda-avanzada' },
      { label: 'Contrato de Profesor' },
    ]);
  }

  async loadTeacherContract(): Promise<void> {
    const filters: TeacherContractFilter = {
      teacherCode: this.code(),
      startDate: this.startDate(),
      endDate: this.endDate(),
      page: this.currentPage() - 1, // conversión a 0-indexado solo aquí, para el backend
      size: this.pageSize(),
      sort: this.sort(),
    };

    const response = await firstValueFrom(this.teacherContractService.findWithFilters(filters));
    this.teacherContract.set(response.content);
    this.totalItems.set(response.totalElements);
  }

  readonly columns: TableColumn[] = [
    { key: 'contractType', label: 'Tipo Contrato' },
    { key: 'startDate', label: 'Fecha Inicio' },
    { key: 'endDate', label: 'Fecha Fin' },
    { key: 'position', label: 'Posicion' },
  ];

  clearDateFilters() {
    this.endDate.set(null);
    this.startDate.set(null);
    this.currentPage.set(1);
    this.loadTeacherContract();
  }

  toggleSortByDate() {
    this.sort.set(this.sort() === 'asc' ? 'desc' : 'asc');
    this.loadTeacherContract();
  }

  onPageChange(page: number) {
    this.currentPage.set(page);
    this.loadTeacherContract();
  }

  onPageSizeChange(size: number) {
    this.pageSize.set(size);
    this.currentPage.set(1);
    this.loadTeacherContract();
  }

  onDateStartChange(date: Date) {
    this.startDate.set(date);
    this.currentPage.set(1);
    this.loadTeacherContract();
  }

  onDateEndChange(date: Date) {
    this.endDate.set(date);
    this.currentPage.set(1);
    this.loadTeacherContract();
  }
}