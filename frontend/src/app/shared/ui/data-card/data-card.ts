import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Button } from "../button/button";
import { FormsModule } from '@angular/forms';
import { EmptyState } from "../empty-state/empty-state";

@Component({
  selector: 'app-data-card',
  imports: [CommonModule, FormsModule, EmptyState, Button],
  templateUrl: './data-card.html',
  styleUrl: './data-card.css',
})
export class DataCard {

  @Input() users: any[] = [];
  @Input() titleField: string = '';
  @Input() subtitleField: string = '';
  @Input() detailField: string = '';
  @Output() viewDetail = new EventEmitter<any>();

  openDetail(user: any) {
    this.viewDetail.emit(user);
  }

}
