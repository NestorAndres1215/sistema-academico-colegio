import { Component } from '@angular/core';
import { PageHeader } from '../../../shared/ui/page-header/page-header';

@Component({
  imports: [PageHeader],
  selector: 'app-settings',
  styleUrl: './settings.css',
  templateUrl: './settings.html',
})
export class Settings {
  readonly icon = 'settings';
  readonly title = 'Configuración';
  readonly subtitle = 'Configura las opciones y preferencias del sistema';
}
