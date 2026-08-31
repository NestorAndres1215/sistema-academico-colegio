import { Component, inject, input } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { Router } from '@angular/router';
import { MatSidenav } from '@angular/material/sidenav';
import { MenuResponse } from '../../models/menu-response';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [MatIconModule, MatListModule],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css',
})
export class Sidebar {
  readonly mainMenus = input<MenuResponse[]>([]);
  readonly footerText = input('');
  readonly isMobile = input(false);
  readonly sidenavRef = input<MatSidenav | null>(null);

  private readonly router = inject(Router);

  toggleSubMenu(menuResponse: MenuResponse): void {
    menuResponse.mostrarSubMenu = !menuResponse.mostrarSubMenu;
  }

  handleClick(menuResponse: MenuResponse): void {
    if (menuResponse.children?.length) {
      this.toggleSubMenu(menuResponse);
      return;
    }

    if (menuResponse.route) {
      this.router.navigateByUrl(menuResponse.route);
      if (this.isMobile()) this.sidenavRef()?.close();
      return;
    }
  }

  navigateTo(route?: string): void {
    if (!route) return;
    this.router.navigateByUrl(route);
    if (this.isMobile()) this.sidenavRef()?.close();
  }

  hasChildren(menuResponse: MenuResponse): boolean {
    return !!menuResponse.children?.length;
  }
}
