import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-data-detail',
  imports: [CommonModule],
  templateUrl: './data-detail.html',
  styleUrl: './data-detail.css',
})
export class DataDetail {
  
  @Input() title!: string;
  @Input() data: any[] = [];

}
