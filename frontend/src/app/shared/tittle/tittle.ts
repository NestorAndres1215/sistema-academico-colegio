import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-tittle',
  imports: [],
  templateUrl: './tittle.html',
  styleUrl: './tittle.css',
})
export class Tittle {
 @Input() title: string = 'Título por defecto';
  @Input() icon: string = 'fas fa-crown';
}
