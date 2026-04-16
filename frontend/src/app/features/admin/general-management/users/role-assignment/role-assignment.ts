import { Component } from '@angular/core';


import { Pagination } from "../../../../../shared/ui/pagination/pagination";
import { RoleService } from '../../../../../core/services/role.service';
import { Router } from '@angular/router';
import { debounceTime, Subject } from 'rxjs';
import { PageTitle } from "../../../../../shared/ui/page-title/page-title";
import { DataTable } from "../../../../../shared/ui/data-table/data-table";
import { FilterSearch } from '../../../../../shared/ui/filter-search/filter-search';

@Component({
  selector: 'app-role-assignment',
  imports: [FilterSearch, Pagination, PageTitle, DataTable],
  templateUrl: './role-assignment.html',
  styleUrl: './role-assignment.css',
})
export class RoleAssignment {

  constructor(private roleService: RoleService, private router: Router
  ) { }

  rolePaginated: any[] = [];
  pageActivos = 1;
  itemsPerPageActivo = 10;
  totalPages = 0;
  searchTerm: string = '';
  private searchSubject = new Subject<string>();

  columns = [
    { clave: 'name', etiqueta: 'Nombre' },
    { clave: 'description', etiqueta: 'Descripcion' },

  ];

  ngOnInit(): void {
    this.searchSubject.pipe(debounceTime(400)).subscribe(value => {
      this.searchTerm = value;
      this.pageActivos = 1;
      this.loadRoles();
    });

    this.loadRoles();
  }


  loadRoles(): void {
    this.roleService.getAll(
      this.pageActivos - 1,
      this.itemsPerPageActivo,
      this.searchTerm
    ).subscribe({
      next: (res: any) => {
        this.rolePaginated = res.content || [];
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
    this.loadRoles();
  }
}
