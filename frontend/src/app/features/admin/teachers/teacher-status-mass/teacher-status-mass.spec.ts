import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TeacherStatusMass } from './teacher-status-mass';

describe('TeacherStatusMass', () => {
  let component: TeacherStatusMass;
  let fixture: ComponentFixture<TeacherStatusMass>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeacherStatusMass],
    }).compileComponents();

    fixture = TestBed.createComponent(TeacherStatusMass);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
