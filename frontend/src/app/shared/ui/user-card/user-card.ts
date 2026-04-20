import { Component, Input } from '@angular/core';
import { EmptyState } from '../empty-state/empty-state';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-card',
    imports: [CommonModule, FormsModule, EmptyState],
  templateUrl: './user-card.html',
  styleUrl: './user-card.css',
})
export class UserCard {

  @Input() columns: { etiqueta: string; clave: string }[] = [];
  @Input() data: any[] = [];
  @Input() buttonsConfig: any = {};
  @Input() imageField: string = 'profile';
  @Input() firstNameField: string = 'firstName';
  @Input() lastNameField: string = 'paternalLastName';

}
