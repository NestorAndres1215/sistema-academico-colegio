import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { debounceTime, Subject } from 'rxjs';
import { TeacherService } from '../../../../../core/services/teacher.service';
import { FilterSearch } from "../../../../../shared/ui/filter-search/filter-search";
import { DataCard } from "../../../../../shared/ui/data-card/data-card";
import { PageTitle } from "../../../../../shared/ui/page-title/page-title";
import { Pagination } from "../../../../../shared/ui/pagination/pagination";

@Component({
  selector: 'app-observation-teacher',
  imports: [FilterSearch, DataCard, PageTitle, Pagination],
  templateUrl: './observation-teacher.html',
  styleUrl: './observation-teacher.css',
})
export class ObservationTeacher {

  constructor(private router: Router, private teacherService: TeacherService) { }

  teacherPaginated: any[] = [];
  pageActivos = 1;
  itemsPerPageActivo = 20;
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
        this.teacherPaginated = (res.content || []).map((t: any) => ({
          ...t,
          fullName: `${t.firstName} ${t.paternalLastName}`
        }));
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

  viewDetail(event: any) {
    this.router.navigate(['/profesores/observaciones/lista/', event.id]);
  }
}
