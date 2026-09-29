import { Component, computed, inject, signal } from '@angular/core';
import { TeacherService } from '../../../../core/modules/teacher/services/teacher.service';
import { AlertService } from '../../../../core/services/alert.service';
import { TeacherListResponse } from '../../../../core/modules/teacher/models/teacher-list-response';
import { TeacherResponse } from '../../../../core/modules/teacher/models/teacher-response';
import { SelectFilterOption } from '../../../../core/models/select-filter-option';
import { TableColumn } from '../../../../shared/models/table.model';
import { TableAction } from '../../../../shared/ui/data-table/data-table.types';
import { firstValueFrom } from 'rxjs';
import { DataTable } from '../../../../shared/ui/data-table/data-table';
import { Pagination } from '../../../../shared/ui/pagination/pagination';
import { Button } from '../../../../shared/ui/button/button';
import { SelectFilter } from '../../../../shared/ui/select-filter/select-filter';
import { PageHeader } from '../../../../shared/ui/page-header/page-header';
import { MatIconModule } from '@angular/material/icon';

@Component({
  imports: [DataTable, Pagination, Button, SelectFilter, PageHeader,MatIconModule],
  selector: 'app-teacher-status-mass',
  styleUrl: './teacher-status-mass.css',
  templateUrl: './teacher-status-mass.html',
})
export class TeacherStatusMass {
  private readonly teacherService = inject(TeacherService);
  private readonly alertService = inject(AlertService);
  readonly icon = 'fact_check';
  readonly title = 'Cambio masivo de estado';
  readonly subtitle = 'Selecciona varios profesores para activarlos o desactivarlos a la vez';

  readonly users = signal<TeacherResponse[]>([]);
  readonly totalItems = signal(0);
  readonly currentPage = signal(1);
  readonly pageSize = signal(5);
  readonly searchTerm = signal('');
  readonly statusFilter = signal('active');
  readonly selectedIds = signal<Set<any>>(new Set());
  readonly submitting = signal(false);

  readonly statusOptions: SelectFilterOption[] = [
    { value: 'active', label: 'Activo' },
    { value: 'inactive', label: 'Inactivo' },
  ];

  readonly columns: TableColumn[] = [
    { key: 'name', label: 'Nombre' },
    { key: 'lastName', label: 'Apellido' },
    { key: 'dni', label: 'Dni', width: '120px' },
  ];

  readonly tableActions = computed<TableAction[]>(() => {
    switch (this.statusFilter()) {
      case 'active':
        return ['deactivate'];
      case 'inactive':
        return ['activate'];
      default:
        return ['activate', 'deactivate'];
    }
  });


    readonly selectedCount = computed(() => this.selectedIds().size);
  
    readonly bulkActionLabel = computed(() =>
      this.statusFilter() === 'active' ? 'Desactivar seleccionados' : 'Activar seleccionados',
    );
  
    readonly bulkActionIcon = computed(() =>
      this.statusFilter() === 'active' ? 'block' : 'check_circle',
    );
  
    readonly bulkActionVariant = computed<'danger' | 'success'>(() =>
      this.statusFilter() === 'active' ? 'danger' : 'success',
    );
  
    readonly selectAllMatching = signal(false);
    readonly loadingAllIds = signal(false);
    readonly hasMoreThanPage = computed(() => this.totalItems() > this.users().length);
  
    readonly canSelectAllMatching = computed(
      () =>
        this.selectedCount() > 0 &&
        !this.selectAllMatching() &&
        this.hasMoreThanPage() &&
        this.selectedCount() < this.totalItems(),
    );
  
    async ngOnInit(): Promise<void> {
      await this.loadUsers();
    }
  
    async loadUsers(): Promise<void> {
      const status = this.statusFilter() ? this.statusFilter().toUpperCase() : '';
  
      const response = await firstValueFrom(
        this.teacherService.findByAllStatus(
         
          this.currentPage() - 1,
          this.pageSize(),
          this.searchTerm(), status,
        ),
      );
  
      this.users.set(response.content);
      this.totalItems.set(response.totalElements);
    }
  
    async onStatusFilterChange(status: string): Promise<void> {
      this.statusFilter.set(status);
      this.currentPage.set(1);
      this.clearSelection();
      await this.loadUsers();
    }
  
    async onPageChange(page: number): Promise<void> {
      this.currentPage.set(page);
      await this.loadUsers();
    }
  
    async onPageSizeChange(size: number): Promise<void> {
      this.pageSize.set(size);
      this.currentPage.set(1);
      await this.loadUsers();
    }
  
    onSelectionChange(ids: Set<any>): void {
      this.selectedIds.set(ids);
      if (ids.size !== this.totalItems()) {
        this.selectAllMatching.set(false);
      }
    }
  
    clearSelection(): void {
      this.selectedIds.set(new Set());
      this.selectAllMatching.set(false);
    }
  
    backToPageSelection(): void {
      const pageIds = new Set(this.users().map((u) => u.id));
      this.selectedIds.set(pageIds);
      this.selectAllMatching.set(false);
    }
  
    async selectAllAcrossPages(): Promise<void> {
      this.loadingAllIds.set(true);
  
      try {
        const status = this.statusFilter() ? this.statusFilter().toUpperCase() : '';
  
        const res = await firstValueFrom(
          this.teacherService.findByAllStatus( 0, this.totalItems(), this.searchTerm(),status),
        );
  
        const allIds = res.content.map((admin: any) => admin.id);
        this.selectedIds.set(new Set(allIds));
        this.selectAllMatching.set(true);
      } catch {
        this.alertService.error('Error', 'No se pudo seleccionar a todos los usuarios.');
      } finally {
        this.loadingAllIds.set(false);
      }
    }
  
    async onDeactivate(fila: any): Promise<void> {
      const confirmed = await this.alertService.confirm(
        `¿Desactivar a ${fila.fullName}?`,
        'El usuario ya no podrá acceder al sistema.',
      );
  
      if (!confirmed) {
        this.alertService.info('Acción cancelada', `No se desactivó a ${fila.fullName}.`);
        return;
      }
  
      try {
        await firstValueFrom(this.teacherService.deactivate(fila.id));
        this.alertService.success(
          'Usuario desactivado',
          `${fila.fullName} ha sido desactivado correctamente.`,
        );
        await this.loadUsers();
      } catch {
        this.alertService.error('Error', 'No se pudo desactivar el usuario.');
      }
    }
  
    async onActivate(fila: any): Promise<void> {
      const confirmed = await this.alertService.confirm(
        `¿Activar a ${fila.fullName}?`,
        'El usuario volverá a tener acceso al sistema.',
      );
  
      if (!confirmed) {
        this.alertService.info('Acción cancelada', `No se activó a ${fila.fullName}.`);
        return;
      }
  
      try {
        await firstValueFrom(this.teacherService.activate(fila.id));
        this.alertService.success(
          'Usuario activado',
          `${fila.fullName} ha sido activado correctamente.`,
        );
        await this.loadUsers();
      } catch {
        this.alertService.error('Error', 'No se pudo activar el usuario.');
      }
    }
  
    async operar(): Promise<void> {
      const count = this.selectedCount();
      if (count === 0) return;
  
      const willActivate = this.statusFilter() !== 'active';
      const verb = willActivate ? 'activar' : 'desactivar';
  
      const confirmed = await this.alertService.confirm(
        `¿${willActivate ? 'Activar' : 'Desactivar'} ${count} usuario(s)?`,
        willActivate
          ? 'Los usuarios seleccionados volverán a tener acceso al sistema.'
          : 'Los usuarios seleccionados ya no podrán acceder al sistema.',
      );
  
      if (!confirmed) {
        this.alertService.info(
          'Acción cancelada',
          `No se pudo ${verb} a los usuarios seleccionados.`,
        );
        return;
      }
  
      this.submitting.set(true);
  
      try {
        const ids = Array.from(this.selectedIds());
  
        const requests = ids.map((id) =>
          firstValueFrom(
            willActivate ? this.teacherService.activate(id) : this.teacherService.deactivate(id),
          ),
        );
  
        await Promise.all(requests);
  
        this.alertService.success(
          `Profores ${willActivate ? 'activados' : 'desactivados'}`,
          `${ids.length} usuario(s) ${willActivate ? 'activados' : 'desactivados'} correctamente.`,
        );
  
        this.clearSelection();
        await this.loadUsers();
      } catch {
        this.alertService.error('Error', `No se pudo ${verb} a los usuarios seleccionados.`);
      } finally {
        this.submitting.set(false);
      }
    }
}
