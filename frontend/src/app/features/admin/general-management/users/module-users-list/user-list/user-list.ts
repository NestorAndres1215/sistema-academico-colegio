import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../../../../core/services/admin.service';
import { Pagination } from "../../../../../../shared/pagination/pagination";
import { CommonModule } from '@angular/common';
import { Table } from '../../../../../../shared/table/table';
import { Tittle } from "../../../../../../shared/tittle/tittle";
import { Search } from "../../../../../../shared/search/search";
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AlertService } from '../../../../../../core/services/alert.service';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [Pagination, CommonModule, Table, Tittle, Search],
  templateUrl: './user-list.html',
  styleUrl: './user-list.css',
})
export class UserList implements OnInit {

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
    deactivate: true,
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
      true,
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

  desactivate(fila: any) {
    this.alertService.confirm(
      `¿Desactivar a ${fila.id}?`,
      'Esta acción no se puede revertir'
    ).then(confirmed => {
      if (confirmed) {
        // Acción si confirma
        fila.status = false; // ejemplo local
        this.alertService.success('Usuario desactivado', `${fila.label} ha sido desactivado.`);
      } else {
        // Mensaje de cancelación
        this.alertService.info('Acción cancelada', `No se desactivó a ${fila.id}.`);
      }
    });
  }

  detail(fila: any): void {
    this.router.navigate(['/usuarios/detalle-usuario/', fila.id]);
  }

  update(fila: any) {
    this.router.navigate(['/usuarios/update-usuario/', fila.id]);
  }
}