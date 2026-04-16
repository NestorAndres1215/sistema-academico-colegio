import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ViewToggle } from "../../../../../../shared/ui/view-toggle/view-toggle";
import { PageTitle } from "../../../../../../shared/ui/page-title/page-title";
import { Router } from '@angular/router';
import { AlertService } from '../../../../../../core/services/alert.service';
import { TeacherService } from '../../../../../../core/services/teacher.service';
import { debounceTime, Subject } from 'rxjs';
import { FilterSearch } from "../../../../../../shared/ui/filter-search/filter-search";
import { DataTable } from "../../../../../../shared/ui/data-table/data-table";
import { Pagination } from "../../../../../../shared/ui/pagination/pagination";
import { DataCard } from "../../../../../../shared/ui/data-card/data-card";


@Component({
  selector: 'app-personal-information',
  imports: [CommonModule, FormsModule, ViewToggle, PageTitle, FilterSearch, DataTable, Pagination, DataCard],
  templateUrl: './personal-information.html',
  styleUrl: './personal-information.css',
})
export class PersonalInformation {

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

  desactivate(fila: any) { }
  detail(fila: any) {

  }
}
