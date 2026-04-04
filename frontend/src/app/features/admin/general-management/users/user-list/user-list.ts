import { Component } from '@angular/core';
import { AdminService } from '../../../../../core/services/admin.service';
import { Pagination } from "../../../../../shared/pagination/pagination";
import { CommonModule } from '@angular/common';
import { Table } from '../../../../../shared/table/table';

@Component({
  selector: 'app-user-list',
  imports: [Pagination, CommonModule, Table],
  templateUrl: './user-list.html',
  styleUrl: './user-list.css',
})
export class UserList {

  constructor(private adminService: AdminService) { }

  adminPaginado: any[] = [];

  columnas = [
    { clave: 'firstName', etiqueta: 'Nombre' },
    { clave: 'paternalLastName', etiqueta: 'Apellido' },
    { clave: 'dni', etiqueta: 'DNI' },
    { clave: 'phone', etiqueta: 'Teléfono' },
    { clave: 'profile', etiqueta: 'Perfil' }
  ];

  pageActivos = 1;
  itemsPerPageActivo = 1;
  totalPages = 0;

  ngOnInit(): void {
    this.loadAdmins();
  }

  loadAdmins() {
    this.adminService
      .getByStatus(true, this.pageActivos - 1, this.itemsPerPageActivo)
      .subscribe(res => {
        this.adminPaginado = res.content;
        this.totalPages = res.totalPages;
      });
  }

  onPageChange() {
    this.loadAdmins();
  }
}
