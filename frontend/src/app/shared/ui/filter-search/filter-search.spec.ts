import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterSearch } from './filter-search';

describe('FilterSearch', () => {
  let component: FilterSearch;
  let fixture: ComponentFixture<FilterSearch>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FilterSearch]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FilterSearch);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
