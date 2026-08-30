import { Component, signal } from '@angular/core';
import { BreadcrumbItem } from '../../../shared/models/breadcrumb.model';
import { PageHeader } from '../../../shared/ui/page-header/page-header';

@Component({
  imports: [PageHeader],
  selector: 'app-admin-dashboard',
  styleUrl: './admin-dashboard.css',
  templateUrl: './admin-dashboard.html',
})
export class AdminDashboard {
  readonly icon = 'dashboard';
  readonly title = 'Dashboard';
  readonly subtitle = 'Resumen general del sistema';
  readonly breadcrumbs = signal<BreadcrumbItem[]>([]);
  readonly userName = signal('');
}
