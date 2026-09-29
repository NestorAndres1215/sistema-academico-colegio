import { Component, inject, signal } from '@angular/core';
import { PageHeader } from '../../../../../shared/ui/page-header/page-header';
import { TeacherService } from '../../../../../core/modules/teacher/services/teacher.service';
import { TeacherResponse } from '../../../../../core/modules/teacher/models/teacher-response';
import { Router } from '@angular/router';
import { Search } from '../../../../../shared/ui/search/search';
import { SearchResult } from '../../../../../shared/ui/search-result/search-result';
import { Pagination } from '../../../../../shared/ui/pagination/pagination';
import { SearchResultItem } from '../../../../../shared/models/search-result-model';
import { SearchResultAction } from '../../../../../shared/ui/search-result/search-result.types';
import { firstValueFrom } from 'rxjs';
import { FileService } from '../../../../../core/services/file.service';

@Component({
  imports: [PageHeader, Search, SearchResult, Pagination],
  selector: 'app-teacher-observation-list',
  styleUrl: './teacher-observation-list.css',
  templateUrl: './teacher-observation-list.html',
})
export class TeacherObservationList {
  private readonly teacherService = inject(TeacherService);
  private readonly fileService = inject(FileService);
  private readonly router = inject(Router);
  readonly results = signal<SearchResultItem[]>([]);
  readonly totalItems = signal(0);
  readonly currentPage = signal(1);
  readonly pageSize = signal(5);
  readonly searchTerm = signal('');

  readonly statusFilter = signal('ACTIVE');
  readonly icon = 'rate_review';
  readonly title = 'Observaciones de profesores';
  readonly subtitle = 'Consulta las observaciones registradas de los profesores.';
  readonly sessionAction: SearchResultAction[] = ['viewObservation'];

  async ngOnInit(): Promise<void> {
    await this.loadTeachers();
  }

  async loadTeachers(): Promise<void> {
    const response = await firstValueFrom(
      this.teacherService.findByAllStatus(
        this.currentPage() - 1,
        this.pageSize(),
        this.searchTerm(),
        this.statusFilter(),
      ),
    );

    this.totalItems.set(response.totalElements);

    const searchItems: SearchResultItem[] = response.content.map((teacher) => ({
      id: String(teacher.id),
      name: `${teacher.name} ${teacher.lastName}`,
      title: `Profesor ${teacher.code}`,
      subtitle: teacher.dni,
      avatar: this.fileService.getFileUrl(teacher.photo),
    }));

    this.results.set(searchItems);
  }

  onSearch(term: string): void {
    this.searchTerm.set(term);
    this.currentPage.set(1);
    this.loadTeachers();
  }

  onPageChange(page: number): void {
    this.currentPage.set(page);
    this.loadTeachers();
  }

  onPageSizeChange(size: number): void {
    this.pageSize.set(size);
    this.currentPage.set(1);
    this.loadTeachers();
  }
}
