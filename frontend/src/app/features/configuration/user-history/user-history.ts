import { Component, inject, signal } from '@angular/core';
import { PageHeader } from '../../../shared/ui/page-header/page-header';
import { BreadCrumb } from '../../../shared/ui/bread-crumb/bread-crumb';
import { BreadcrumbItem } from '../../../shared/models/breadcrumb.model';
import { UserHistoryService } from '../../../core/modules/user-history/services/user-history.service';
import { AuthService } from '../../../core/auth/service/auth.service';
import { TableColumn } from '../../../shared/models/table.model';
import { firstValueFrom } from 'rxjs';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { Button } from '../../../shared/ui/button/button';
import { DataTable } from '../../../shared/ui/data-table/data-table';
import { Pagination } from '../../../shared/ui/pagination/pagination';
import { Search } from '../../../shared/ui/search/search';
import { UserHistoryFilter } from '../../../core/modules/user-history/models/user-history-request';

@Component({
  imports: [
    CommonModule,
    FormsModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    MatNativeDateModule,
    BreadCrumb,
    PageHeader,
    Button,
    DataTable,
    Pagination,
    Search,
  ],
  selector: 'app-user-history',
  styleUrl: './user-history.css',
  templateUrl: './user-history.html',
})
export class UserHistory {
  private readonly authService = inject(AuthService);
  private readonly userHistoryService = inject(UserHistoryService);
  readonly icon = 'history';
  readonly title = 'Historial de actividad';
  readonly subtitle = 'Registro de acciones realizadas en el sistema';
  readonly breadcrumbs = signal<BreadcrumbItem[]>([]);
  readonly userName = signal('');
  readonly logs = signal<any[]>([]);
  readonly totalItems = signal(0);
  readonly searchTerm = signal('');
  readonly dateFrom = signal<Date | null>(null);
  readonly dateTo = signal<Date | null>(null);
  readonly currentPage = signal(1);
  readonly pageSize = signal(10);
  readonly sort = signal<'asc' | 'desc'>('desc');

  readonly columns: TableColumn[] = [
    { key: 'action', label: 'Acción', sortable: true },
    { key: 'module', label: 'Módulo', sortable: true, width: '160px' },
    { key: 'detail', label: 'Descripción' },
    { key: 'date', label: 'Fecha', sortable: true, width: '180px' },
    { key: 'time', label: 'Hora', sortable: true, width: '180px' },
  ];

  async ngOnInit(): Promise<void> {
    await this.initUser();
    this.loadHistory();
  }

  private async initUser(): Promise<void> {
    const user = await firstValueFrom(this.authService.getCurrentUser());

    if (!user) {
      return;
    }

    this.userName.set(user.email);

    const homeRoute = this.authService.getHomeByRole(user.role);

    this.breadcrumbs.set([
      { label: 'Inicio', href: homeRoute },
      { label: 'Usuarios' },
      { label: 'Historial de actividad' },
    ]);
  }

  async loadHistory(): Promise<void> {
    const filters: UserHistoryFilter = {
      email: this.userName(),
      page: this.currentPage() - 1,
      size: this.pageSize(),
      sort: this.sort(),
      action: this.searchTerm() || null,
      status: null,
      dateFrom: this.dateFrom(),
      dateTo: this.dateTo(),
    };

    const response = await firstValueFrom(this.userHistoryService.findWithFilters(filters));

    this.logs.set(response.content);
    this.totalItems.set(response.totalElements);
  }

  clearDateFilters() {
    this.dateFrom.set(null);
    this.dateTo.set(null);
    this.loadHistory();
  }

  toggleSortByDate() {
    this.sort.set(this.sort() === 'asc' ? 'desc' : 'asc');
    this.loadHistory();
  }

  onPageChange(page: number) {
    this.currentPage.set(page - 1);
    this.loadHistory();
  }

  onPageSizeChange(size: number) {
    this.pageSize.set(size);
    this.currentPage.set(0);
    this.loadHistory();
  }

  onSearch(term: string) {
    this.searchTerm.set(term);
    this.currentPage.set(0);
    this.loadHistory();
  }

  onDateFromChange(date: Date) {
    this.dateFrom.set(date);
    this.currentPage.set(0);
    this.loadHistory();
  }

  onDateToChange(date: Date) {
    this.dateTo.set(date);
    this.currentPage.set(0);
    this.loadHistory();
  }
}
