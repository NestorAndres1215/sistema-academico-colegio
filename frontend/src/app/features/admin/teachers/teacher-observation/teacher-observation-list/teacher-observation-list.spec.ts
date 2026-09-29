import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TeacherObservationList } from './teacher-observation-list';

describe('TeacherObservationList', () => {
  let component: TeacherObservationList;
  let fixture: ComponentFixture<TeacherObservationList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeacherObservationList],
    }).compileComponents();

    fixture = TestBed.createComponent(TeacherObservationList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
