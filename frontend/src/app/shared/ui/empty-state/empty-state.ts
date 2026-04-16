import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-empty-state',
  imports: [CommonModule],
  templateUrl: './empty-state.html',
  styleUrl: './empty-state.css',
})
export class EmptyState {
  @Input() icon: string = 'fas fa-info-circle';
  @Input() title: string = 'No hay datos para mostrar';
  @Input() subtitle?: string;
}
