import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-search',
  imports: [FormsModule, CommonModule],
  templateUrl: './search.html',
  styleUrl: './search.css',
})
export class Search {
 searchText = '';
  isFocused = false;

  @Output() searchChange = new EventEmitter<string>();

  onInputChange(value: string) {
    this.searchText = value;
    this.searchChange.emit(this.searchText);  
  }
  clearSearch() {
    this.searchText = '';
    this.onInputChange('');
  }
}
