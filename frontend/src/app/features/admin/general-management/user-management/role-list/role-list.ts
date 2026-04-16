import { Component, OnInit } from '@angular/core';
import { DataTable } from '../../../../../shared/ui/data-table/data-table';
import { Router } from '@angular/router';
import { Subject, debounceTime } from 'rxjs';
import { RoleService } from '../../../../../core/services/role.service';
import { FilterSearch } from '../../../../../shared/ui/filter-search/filter-search';
import { PageTitle } from '../../../../../shared/ui/page-title/page-title';
import { Pagination } from '../../../../../shared/ui/pagination/pagination';

@Component({
  selector: 'app-role-list',
 imports: [FilterSearch, Pagination, PageTitle, DataTable],
  templateUrl: './role-list.html',
  styleUrl: './role-list.css',
})
export class RoleList implements OnInit {

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
