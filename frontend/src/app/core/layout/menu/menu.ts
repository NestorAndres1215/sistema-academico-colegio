import { Component, inject, input, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { MatMenu, MatMenuModule } from '@angular/material/menu';
import { AuthService } from '../../auth/service/auth.service';
import { MatDividerModule } from '@angular/material/divider';


@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, MatMenuModule,MatDividerModule],
  templateUrl: './menu.html',
  styleUrl: './menu.css',
})
export class Menu {
  @ViewChild('mobileActionsMenu') mobileActionsMenu!: MatMenu;
  @ViewChild('messageMenu') messageMenu!: MatMenu;
  @ViewChild('notificationMenu') notificationMenu!: MatMenu;
  @ViewChild('profileMenu') profileMenu!: MatMenu;
  @ViewChild('menu') menu!: MatMenu;

  readonly username = input('');
  readonly userRoleName = input('');
  readonly isAdmin = input(false);
  readonly user = input<any | null>(null);

  private readonly router = inject(Router);
  private readonly authService = inject(AuthService);

  toggleTheme(): void {
    this.router.navigate(['/configuracion/cambiar-tema']);
  }

  historial(): void {
    this.router.navigate(['/configuracion/historial-usuarios']);
  }

  settings(): void {
    this.router.navigate(['/configuracion']);
  }

  contrana(): void {
    this.router.navigate(['/configuracion/cambiar-contrasena']);
  }

  cuenta(): void {
    this.router.navigate(['/mi-cuenta']);
  }

  perfil(): void {
    this.router.navigate(['/mi-perfil']);
  }

  company(): void {
    this.router.navigate(['/configuracion/company']);
  }

  help(): void {
    this.router.navigate(['/configuracion/ayuda']);
  }

  async logout(): Promise<void> {
   /* await firstValueFrom(this.authService.logoutSession(this.user()?.id));
    await this.authService.logout();
    await this.authService.logoutSession(this.user().id);
    this.router.navigate(['/auth/login']);*/
  }
}