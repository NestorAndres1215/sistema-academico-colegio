import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject, debounceTime } from 'rxjs';
import { AlertService } from '../../../../../core/services/alert.service';
import { TeacherService } from '../../../../../core/services/teacher.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DataCard } from '../../../../../shared/ui/data-card/data-card';
import { DataTable } from '../../../../../shared/ui/data-table/data-table';
import { FilterSearch } from '../../../../../shared/ui/filter-search/filter-search';
import { PageTitle } from '../../../../../shared/ui/page-title/page-title';
import { Pagination } from '../../../../../shared/ui/pagination/pagination';
import { ViewToggle } from '../../../../../shared/ui/view-toggle/view-toggle';
import { UserCard } from "../../../../../shared/ui/user-card/user-card";

@Component({
  selector: 'app-teacher-list',
  standalone: true,
  imports: [CommonModule, FormsModule, ViewToggle, PageTitle, FilterSearch, DataTable, Pagination, UserCard],
  templateUrl: './teacher-list.html',
  styleUrl: './teacher-list.css',
})
export class TeacherList implements OnInit {


  selectedView = 'table';

  viewOptions = [
    { label: 'Lista', value: 'table', icon: 'fas fa-list' },
    { label: 'Tarjeta', value: 'card', icon: 'fas fa-th-large' }
  ];

  constructor(private router: Router, private alertService: AlertService, private teacherService: TeacherService) { }

  teacherPaginated: any[] = [];

  columnsTable = [
    { clave: 'firstName', etiqueta: 'Nombre' },
    { clave: 'paternalLastName', etiqueta: 'Apellido' },
    { clave: 'dni', etiqueta: 'DNI' },
    { clave: 'phone', etiqueta: 'Teléfono' }
  ];

  columnsCard = [
    { clave: 'firstName', etiqueta: 'Nombre' },
    { clave: 'paternalLastName', etiqueta: 'Apellido' },
    { clave: 'dni', etiqueta: 'DNI' },
    { clave: 'phone', etiqueta: 'Teléfono' },
    { clave: 'profile', etiqueta: 'Perfil' }
  ];

  buttonsConfig = {
    deactivate: true,
    detail: true,
    print: true,
    update: true,
  };

  pageActivos = 1;
  itemsPerPageActivo = 8;
  totalPages = 0;
  searchTerm: string = '';
  private searchSubject = new Subject<string>();

  ngOnInit(): void {
    this.searchSubject.pipe(debounceTime(400)).subscribe(value => {
      this.searchTerm = value;
      this.pageActivos = 1;
      this.loadTeachers();
    });

    this.loadTeachers();
  }

  loadTeachers(): void {
    this.teacherService.getByStatus(
      "ACTIVE",
      this.pageActivos - 1,
      this.itemsPerPageActivo,
      this.searchTerm
    ).subscribe({
      next: (res: any) => {
        this.teacherPaginated = res.content || [];
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
    this.loadTeachers();
  }


  desactivate(fila: any) {
    this.alertService.confirm(
      `¿Desactivar a ${fila.id}?`,
      'Esta acción no se puede revertir'
    ).then(confirmed => {
      if (confirmed) {

        this.teacherService.deactivate(fila.id).subscribe({
          next: () => {
            fila.status = false;
            this.alertService.success(
              'Profesor desactivado',
              `${fila.id} ha sido desactivado.`
            );

            this.loadTeachers();
          },
          error: () => {
            this.alertService.error(
              'Error',
              'No se pudo desactivar el profesor'
            );
          }
        });

      } else {
        this.alertService.info(
          'Acción cancelada',
          `No se desactivó a ${fila.id}.`
        );
      }
    });
  }

  detail(fila: any) {
    this.router.navigate(['/profesores/detalle-profesor', fila.id]);
  }


  update(fila: any) {
    this.router.navigate(['/profesores/actualizar-profesor', fila.id]);
  }

  print(fila: any) {
    console.log(fila)
  }
}
