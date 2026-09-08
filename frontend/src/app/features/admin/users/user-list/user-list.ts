import { Component, inject, signal } from '@angular/core';
import { UserResponse } from '../../../../core/modules/user/models/user-response';
import { TableAction } from '../../../../shared/ui/data-table/data-table.types';
import { firstValueFrom } from 'rxjs';
import { TableColumn } from '../../../../shared/models/table.model';
import { Router } from '@angular/router';
import { UserService } from '../../../../core/modules/user/services/user.service';
import { DataTable } from '../../../../shared/ui/data-table/data-table';
import { Search } from '../../../../shared/ui/search/search';
import { Pagination } from '../../../../shared/ui/pagination/pagination';
import { PageHeader } from '../../../../shared/ui/page-header/page-header';

@Component({
  imports: [DataTable, Search, Pagination, PageHeader],
  selector: 'app-user-list',
  styleUrl: './user-list.css',
  templateUrl: './user-list.html',
})
export class UserList {
  private readonly userService = inject(UserService);
  private readonly router = inject(Router);
 
  readonly users = signal<UserResponse[]>([]);
  readonly totalItems = signal(0);
  readonly currentPage = signal(1);
  readonly pageSize = signal(5);
  readonly searchTerm = signal('');
  readonly statusFilter = signal('ACTIVE');
  readonly icon = 'manage_accounts';
  readonly title = 'Gestión de usuarios';
  readonly subtitle = 'Búsqueda, filtros y administración de usuarios del sistema';


  readonly columns: TableColumn[] = [
    { key: 'username', label: 'Usuario', sortable: true },
    { key: 'email', label: 'Correo', sortable: true },
    { key: 'role', label: 'Rol', sortable: true },
    { key: 'status', label: 'Estado', width: '120px' },
  ];

  async ngOnInit(): Promise<void> {
    this.loadUsers();
  }



  async loadUsers(): Promise<void> {
    const response = await firstValueFrom(
      this.userService.getByStatus(
        this.statusFilter(),
        this.currentPage() - 1,
        this.pageSize(),
        this.searchTerm(),
      ),
    );

    this.users.set(response.content);

    this.totalItems.set(response.totalElements);
  }

  onSearch(term: string): void {
    this.searchTerm.set(term);
    this.currentPage.set(1);
    this.loadUsers();
  }

  onStatusFilterChange(status: string): void {
    this.statusFilter.set(status);
    this.currentPage.set(1);
    this.loadUsers();
  }

  onPageChange(page: number): void {
    this.currentPage.set(page);
    this.loadUsers();
  }

  onPageSizeChange(size: number): void {
    this.pageSize.set(size);
    this.currentPage.set(1);
    this.loadUsers();
  }

  readonly tableActions: TableAction[] = ['detail', 'edit'];

  onDetail(userResponse: UserResponse): void {
    this.router.navigate(['/admin/usuarios', userResponse.id]);
  }

  onEdit(userResponse: UserResponse): void {
    this.router.navigate(['/admin/usuarios', userResponse.id, 'edit']);
  }
}
