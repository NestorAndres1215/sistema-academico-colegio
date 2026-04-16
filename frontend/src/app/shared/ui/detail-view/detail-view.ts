import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-detail-view',
  imports: [CommonModule],
  templateUrl: './detail-view.html',
  styleUrl: './detail-view.css',
})
export class DetailView {
  @Input() title!: string;
  @Input() data: any[] = [];
}
