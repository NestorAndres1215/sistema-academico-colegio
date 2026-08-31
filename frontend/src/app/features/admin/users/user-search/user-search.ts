import { Component, inject, signal } from '@angular/core';
import { SearchResultItem } from '../../../../shared/models/search-result-model';
import { firstValueFrom } from 'rxjs';
import { UserService } from '../../../../core/modules/user/services/user.service';
import { Router } from '@angular/router';
import { BreadcrumbItem } from '../../../../shared/models/breadcrumb.model';
import { SearchResultAction } from '../../../../shared/ui/search-result/search-result.types';
import { BreadCrumb } from "../../../../shared/ui/bread-crumb/bread-crumb";
import { PageHeader } from "../../../../shared/ui/page-header/page-header";
import { SearchResult } from "../../../../shared/ui/search-result/search-result";
import { Search } from "../../../../shared/ui/search/search";

@Component({
  imports: [BreadCrumb, PageHeader, SearchResult, Search],
  selector: 'app-user-search',
  styleUrl: './user-search.css',
  templateUrl: './user-search.html',
})
export class UserSearch {

  private readonly userService = inject(UserService);
  private readonly router = inject(Router);
  readonly breadcrumbs = signal<BreadcrumbItem[]>([]);
  readonly results = signal<SearchResultItem[]>([]);
  readonly currentQuery = signal('');
  readonly icon = 'person_search';
  readonly title = 'Búsqueda avanzada de usuarios';
  readonly subtitle = 'Encuentra usuarios utilizando múltiples criterios de búsqueda.';
  readonly sessionAction: SearchResultAction[] = ['message', 'viewProfile'];

  async ngOnInit(): Promise<void> {
    await this.initUser();
  }

  private async initUser(): Promise<void> {
    this.breadcrumbs.set([
      { label: 'Inicio', href: '/admin' },
      { label: 'Usuarios' },
      { label: 'Búsqueda Avanzada de Usuarios' },
    ]);
  }

  async loadUsers(): Promise<void> {
    const query = this.currentQuery().trim();

    if (!query) {
      this.results.set([]);
      return;
    }

    const admin = await firstValueFrom(this.userService.search(query));

    const searchItems: SearchResultItem[] = admin.map((user) => ({
      id: String(user.id),
      name: `${user.username}`,
      title: `${user.username}`,
      subtitle: user.email,
      description: user.status === 'ACTIVE' ? 'Activo' : 'Inactivo',
    }));

    this.results.set(searchItems);
  }

  onSearchChange(term: string): void {
    this.currentQuery.set(term);
    this.loadUsers();
  }

  onMessage(item: SearchResultItem): void {
    this.router.navigate(['/mensajes', item.id]);
  }

  onViewProfile(item: SearchResultItem): void {
    this.router.navigate(['/usuarios', item.id]);
  }

}
