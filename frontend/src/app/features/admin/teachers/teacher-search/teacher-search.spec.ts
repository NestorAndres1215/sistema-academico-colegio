import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TeacherSearch } from './teacher-search';

describe('TeacherSearch', () => {
  let component: TeacherSearch;
  let fixture: ComponentFixture<TeacherSearch>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeacherSearch],
    }).compileComponents();

    fixture = TestBed.createComponent(TeacherSearch);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
