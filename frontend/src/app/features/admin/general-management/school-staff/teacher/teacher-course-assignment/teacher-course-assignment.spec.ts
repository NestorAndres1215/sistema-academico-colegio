import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherCourseAssignment } from './teacher-course-assignment';

describe('TeacherCourseAssignment', () => {
  let component: TeacherCourseAssignment;
  let fixture: ComponentFixture<TeacherCourseAssignment>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeacherCourseAssignment]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeacherCourseAssignment);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
