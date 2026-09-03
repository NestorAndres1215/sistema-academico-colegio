import { Component, inject, signal } from '@angular/core';
import { UserResponse } from '../../../../core/modules/user/models/user-response';
import { UserService } from '../../../../core/modules/user/services/user.service';
import { firstValueFrom } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { BreadcrumbItem } from '../../../../shared/models/breadcrumb.model';
import { BreadCrumb } from '../../../../shared/ui/bread-crumb/bread-crumb';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { PageHeader } from '../../../../shared/ui/page-header/page-header';

@Component({
  imports: [BreadCrumb, MatIconModule, CommonModule, PageHeader],
  selector: 'app-user-detail',
  styleUrl: './user-detail.css',
  templateUrl: './user-detail.html',
})
export class UserDetail {
  private readonly route = inject(ActivatedRoute);
  private readonly userService = inject(UserService);
  readonly user = signal<UserResponse | null>(null);
  readonly logoPreview = signal<string | null>(null);
  readonly breadcrumbs = signal<BreadcrumbItem[]>([]);
  readonly icon = 'contact_page';
  readonly title = 'Detalle del usuario';
  readonly subtitle = 'Consulta la información personal y los datos de la cuenta del usuario.';

  readonly editMode = signal(false);
  readonly userId = signal<number>(0);

  async ngOnInit(): Promise<void> {
    this.userId.set(
      Number(this.route.snapshot.paramMap.get('id'))
    );

    await this.initUser();
    await this.loadUsers();
  }

  async loadUsers(): Promise<void> {
    const admin = await firstValueFrom(this.userService.findById(this.userId()));
    this.user.set(admin);
  }

  private async initUser(): Promise<void> {
    this.breadcrumbs.set([
      { label: 'Inicio', href: '/admin' },
      { label: 'Usuarios' },
      { label: 'Listado de Usuarios', href: '/admin/usuarios/listar' },
      { label: 'Detalle de Usuarios' },
    ]);
  }

  get inicial(): string {
    return this.user()?.username?.charAt(0).toUpperCase() ?? '';
  }

  toggleEdit(): void {
    this.editMode.set(true);
  }

  cancelar(): void {
    this.editMode.set(false);
  }
}
