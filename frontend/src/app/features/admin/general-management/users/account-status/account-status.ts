import { Component } from '@angular/core';
import { Pagination } from "../../../../../shared/ui/pagination/pagination";
import { debounceTime, Subject } from 'rxjs';
import { AdminService } from '../../../../../core/services/admin.service';
import { Router } from '@angular/router';
import { AlertService } from '../../../../../core/services/alert.service';
import { PageTitle } from "../../../../../shared/ui/page-title/page-title";
import { DataTable } from "../../../../../shared/ui/data-table/data-table";
import { FilterSearch } from '../../../../../shared/ui/filter-search/filter-search';


@Component({
  selector: 'app-account-status',
  imports: [PageTitle, FilterSearch, Pagination, DataTable],
  templateUrl: './account-status.html',
  styleUrl: './account-status.css',
})
export class AccountStatus {

  constructor(private adminService: AdminService, private router: Router, private alertService: AlertService
  ) { }

  adminPaginated: any[] = [];

  columns = [
    { clave: 'firstName', etiqueta: 'Nombre' },
    { clave: 'paternalLastName', etiqueta: 'Apellido' },
    { clave: 'dni', etiqueta: 'DNI' },
    { clave: 'phone', etiqueta: 'Teléfono' },
    { clave: 'profile', etiqueta: 'Perfil' }
  ];

  buttonsConfig = {
    activate: true,
    detail: true,
    print: false,
    update: false,
  };

  pageActivos = 1;
  itemsPerPageActivo = 10;
  totalPages = 0;

  searchTerm: string = '';
  private searchSubject = new Subject<string>();

  ngOnInit(): void {
    this.searchSubject.pipe(debounceTime(400)).subscribe(value => {
      this.searchTerm = value;
      this.pageActivos = 1;
      this.loadAdmins();
    });

    this.loadAdmins();
  }

  loadAdmins(): void {
    this.adminService.getByStatus(
      false,
      this.pageActivos - 1,
      this.itemsPerPageActivo,
      this.searchTerm
    ).subscribe({
      next: (res: any) => {
        this.adminPaginated = res.content || [];
        this.totalPages = res.totalPages || 0;

        if (this.totalPages === 0) this.pageActivos = 1;
      },
    });
  }

  filter(text: string): void {
    this.searchSubject.next(text);
  }

  onPageChange(page: number): void {
    this.pageActivos = page;
    this.loadAdmins();
  }

  activate(fila: any) {
    this.alertService.confirm(
      `¿Activar a ${fila.id}?`,
      'El usuario volverá a estar disponible'
    ).then(confirmed => {
      if (confirmed) {

        this.adminService.activate(fila.id).subscribe({
          next: () => {
            fila.status = true;
            this.alertService.success(
              'Usuario activado',
              `${fila.id} ha sido activado.`
            );

            this.loadAdmins();
          },
          error: () => {
            this.alertService.error(
              'Error',
              'No se pudo activar el usuario'
            );
          }
        });

      } else {
        this.alertService.info(
          'Acción cancelada',
          `No se activó a ${fila.id}.`
        );
      }
    });
  }

  detail(fila: any): void {
    this.router.navigate(['/usuarios/detalle-usuario/', fila.id]);
  }


}
