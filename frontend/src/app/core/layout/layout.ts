import {
  ChangeDetectorRef,
  Component,
  computed,
  inject,
  OnDestroy,
  OnInit,
  signal,
  ViewChild,
} from '@angular/core';
import { MatSidenav, MatSidenavModule } from '@angular/material/sidenav';
import { RouterModule } from '@angular/router';

import { MenuService } from '../../core/services/menu.service';
import { BreakpointObserver } from '@angular/cdk/layout';
import { firstValueFrom, Subscription } from 'rxjs';

import { Toolbar } from './toolbar/toolbar';
import { Sidebar } from './sidebar/sidebar';
import { Menu } from '../models/menu.model';
import { AuthService } from '../auth/service/auth.service';

import { ROLES } from '../auth/constants/roles';
import { CompanyService } from '../modules/company/services/company.service';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [MatSidenavModule, RouterModule, Toolbar, Sidebar],
  templateUrl: './layout.html',
  styleUrl: './layout.css',
})
export class Layout implements OnInit, OnDestroy {
  @ViewChild('sidenav') sidenav!: MatSidenav;

  readonly isMobile = signal(false);
  readonly user = signal<any | null>(null);
  readonly userRoleName = signal('');
  readonly username = signal('');
  readonly codigo = signal(0);
  readonly mainMenus = signal<Menu[]>([]);
  readonly nameSchool = signal('');
  readonly footerText = signal('Sistema seguro · v1.0.0');
  private readonly menuService = inject(MenuService);
  private readonly bp = inject(BreakpointObserver);
  private readonly authService = inject(AuthService);
  private readonly cdr = inject(ChangeDetectorRef);
  private readonly companyService = inject(CompanyService);
  private interval!: any;

  private bpSub?: Subscription;

  async ngOnInit(): Promise<void> {
    await this.getUsername();
    await this.getCompanies();
    this.loadMenus();
    this.initBreakpointObserver();
  }

  private initBreakpointObserver(): void {
    this.bpSub = this.bp.observe(['(max-width: 768px)']).subscribe((result) => {
      const isMobile = result.matches;

      this.isMobile.set(isMobile);

      if (isMobile && this.sidenav?.opened) {
        this.sidenav.close();
      }
    });
  }

  ngOnDestroy(): void {
    clearInterval(this.interval);
    this.bpSub?.unsubscribe();
  }

  async getUsername(): Promise<void> {
    try {
      const user = await firstValueFrom(this.authService.getCurrentUser());

      if (!user) {
        this.username.set('');
        this.userRoleName.set('');
        return;
      }
      //   this.authService.checkSessionStatus(user.id);
      this.user.set(user);
      this.username.set(user.username);
      this.userRoleName.set(user.role);
    } catch (error) {
      console.error('Error al obtener el usuario actual:', error);
    }
  }

  async getCompanies(): Promise<void> {
    const company = await firstValueFrom(this.companyService.findByCode('COMSANANDRES'));
    this.nameSchool.set(company.name);
  }

  async loadMenus(): Promise<void> {
    const menus = await firstValueFrom(this.menuService.getAll());

    const valid = menus.filter((m: Menu) => !!m);

    this.mainMenus.set(
      valid
        .filter((m: Menu) => m.roles?.some((r) => r.name === this.userRoleName()))
        .sort((a: Menu, b: Menu) => Number(a.menuOrder) - Number(b.menuOrder))
        .map((m: Menu) => ({
          ...m,
          children: (m.children ?? [])
            .sort((a: Menu, b: Menu) => Number(a.menuOrder) - Number(b.menuOrder))
            .map((child: Menu) => ({
              ...child,
              children: (child.children ?? []).sort(
                (a: Menu, b: Menu) => Number(a.menuOrder) - Number(b.menuOrder),
              ),
            })),
          mostrarSubMenu: false,
        })),
    );

    this.cdr.markForCheck();
  }

  isAdmin = computed(() => this.userRoleName() === ROLES.ROLE_ADMIN);

  isTeacher = computed(() => this.userRoleName() === ROLES.ROLE_TEACHER);

  isStudent = computed(() => this.userRoleName() === ROLES.ROLE_STUDENT);
}
