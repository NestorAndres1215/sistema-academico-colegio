import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DataTable } from './data-table';

describe('DataTable', () => {
  let component: DataTable<any>;
  let fixture: ComponentFixture<DataTable<any>>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DataTable],
    }).compileComponents();

    fixture = TestBed.createComponent(DataTable);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
