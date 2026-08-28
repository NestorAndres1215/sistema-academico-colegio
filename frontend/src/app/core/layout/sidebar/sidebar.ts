import { Component, inject, input } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { Router } from '@angular/router';
import { MatSidenav } from '@angular/material/sidenav';
import { Menu } from '../../models/menu.model';


@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [MatIconModule, MatListModule],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css',
})
export class Sidebar {
  readonly mainMenus = input<Menu[]>([]);
  readonly footerText = input('');
  readonly isMobile = input(false);
  readonly sidenavRef = input<MatSidenav | null>(null);

  private readonly router = inject(Router);

  toggleSubMenu(menu: Menu): void {
    menu.mostrarSubMenu = !menu.mostrarSubMenu;
  }

  handleClick(menu: Menu): void {
    if (menu.children?.length) {
      this.toggleSubMenu(menu);
      return;
    }

    if (menu.route) {
      this.router.navigateByUrl(menu.route);
      if (this.isMobile()) this.sidenavRef()?.close();
      return;
    }
  }

  navigateTo(route?: string): void {
    if (!route) return;
    this.router.navigateByUrl(route);
    if (this.isMobile()) this.sidenavRef()?.close();
  }

  hasChildren(menu: Menu): boolean {
    return !!menu.children?.length;
  }
}