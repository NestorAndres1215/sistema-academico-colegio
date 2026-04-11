import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherRegister } from './teacher-register';

describe('TeacherRegister', () => {
  let component: TeacherRegister;
  let fixture: ComponentFixture<TeacherRegister>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeacherRegister]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeacherRegister);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
