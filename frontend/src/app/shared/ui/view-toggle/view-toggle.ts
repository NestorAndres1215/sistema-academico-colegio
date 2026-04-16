import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ViewOption } from '../../../core/models/view-option';

@Component({
  selector: 'app-view-toggle',
  imports: [CommonModule, FormsModule],
  templateUrl: './view-toggle.html',
  styleUrl: './view-toggle.css',
})
export class ViewToggle {

  @Input() value: string = '';
  @Output() valueChange = new EventEmitter<string>();

  @Input() options: ViewOption[] = [];

  setView(view: string) {
    this.value = view;
    this.valueChange.emit(view);
  }

}
