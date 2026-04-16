import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Button } from "../button/button";
import { FormsModule } from '@angular/forms';
import { EmptyState } from "../empty-state/empty-state";

@Component({
  selector: 'app-data-card',
  imports: [CommonModule, FormsModule, EmptyState],
  templateUrl: './data-card.html',
  styleUrl: './data-card.css',
})
export class DataCard {

  @Input() columns: { etiqueta: string; clave: string }[] = [];
  @Input() data: any[] = [];
  @Input() buttonsConfig: any = {};
  @Input() imageField: string = 'profile';
  @Input() firstNameField: string = 'firstName';
  @Input() lastNameField: string = 'paternalLastName';

}
