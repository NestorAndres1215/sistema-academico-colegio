import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherUpdate } from './teacher-update';

describe('TeacherUpdate', () => {
  let component: TeacherUpdate;
  let fixture: ComponentFixture<TeacherUpdate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeacherUpdate]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeacherUpdate);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
