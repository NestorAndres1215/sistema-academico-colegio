import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-detail-data',
  imports: [CommonModule],
  templateUrl: './detail-data.html',
  styleUrl: './detail-data.css',
})
export class DetailData {
  @Input() title!: string;
  @Input() data: any[] = [];
}
