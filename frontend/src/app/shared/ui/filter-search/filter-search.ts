import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-filter-search',
  imports: [CommonModule, FormsModule],
  templateUrl: './filter-search.html',
  styleUrl: './filter-search.css',
})
export class FilterSearch {
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