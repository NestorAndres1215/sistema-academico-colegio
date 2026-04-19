import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherObservation } from './teacher-observation';

describe('TeacherObservation', () => {
  let component: TeacherObservation;
  let fixture: ComponentFixture<TeacherObservation>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeacherObservation]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeacherObservation);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
