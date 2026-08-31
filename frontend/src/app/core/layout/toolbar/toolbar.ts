import { Component, input, output } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatBadgeModule } from '@angular/material/badge';
import { MatMenuModule, MatMenuTrigger } from '@angular/material/menu';
import { Menu } from '../menu/menu';

@Component({
  selector: 'app-toolbar',
  standalone: true,
  imports: [MatToolbarModule, MatBadgeModule, MatMenuModule, MatMenuTrigger, Menu],
  templateUrl: './toolbar.html',
  styleUrl: './toolbar.css',
})
export class Toolbar {
  readonly nameSchool = input('');
  readonly username = input('');
  readonly userRoleName = input('');
  readonly isAdmin = input(false);
  readonly user = input<any | null>(null);
  readonly sidenavOpened = input(false);
  readonly toggleSidenav = output<void>();
}