import { Component, inject, signal } from '@angular/core';
import { TeacherService } from '../../../../core/modules/teacher/services/teacher.service';
import { Router } from '@angular/router';
import { SearchResultItem } from '../../../../shared/models/search-result-model';
import { SearchResultAction } from '../../../../shared/ui/search-result/search-result.types';
import { firstValueFrom } from 'rxjs';
import { SearchResult } from '../../../../shared/ui/search-result/search-result';
import { Search } from '../../../../shared/ui/search/search';
import { PageHeader } from '../../../../shared/ui/page-header/page-header';
import { FileService } from '../../../../core/services/file.service';

@Component({
  imports: [SearchResult, Search, PageHeader],
  selector: 'app-teacher-search',
  styleUrl: './teacher-search.css',
  templateUrl: './teacher-search.html',
})
export class TeacherSearch {
  private readonly teacherService = inject(TeacherService);
  private readonly router = inject(Router);
  private readonly fileService = inject(FileService);
 
  readonly results = signal<SearchResultItem[]>([]);
  readonly currentQuery = signal('');
  readonly icon = 'person_search';
  readonly title = 'Búsqueda avanzada de profesores';
  readonly subtitle = 'Encuentra profesores utilizando múltiples criterios de búsqueda.';
  readonly sessionAction: SearchResultAction[] = ['download', 'message', 'viewContract'];


  async loadTeachers(): Promise<void> {
    const query = this.currentQuery().trim();

    if (!query) {
      this.results.set([]);
      return;
    }

    const admin = await firstValueFrom(this.teacherService.search(query));

    const searchItems: SearchResultItem[] = admin.map((user) => ({
      id: String(user.id),
      name: `${user.name}`,
      title: `${user.name}`,
      subtitle: user.code,
      avatar: this.fileService.getFileUrl(user.photo),
    }));

    this.results.set(searchItems);
  }

  onSearchChange(term: string): void {
    this.currentQuery.set(term);
    this.loadTeachers();
  }

  async onDownload(item: SearchResultItem): Promise<void> {
    const blob = await firstValueFrom(this.teacherService.downloadCurriculum(Number(item.id)));

    const url = URL.createObjectURL(blob);

    const link = document.createElement('a');
    link.href = url;
    link.download = 'curriculum.pdf';
    link.click();

    URL.revokeObjectURL(url);
  }

  onMessage(item: SearchResultItem): void {
    this.router.navigate(['/mensajes', item.id]);
  }

  onViewContract(item: SearchResultItem): void {
    this.router.navigate(['/admin/profesores/contracto', item.subtitle]);
  }
}
