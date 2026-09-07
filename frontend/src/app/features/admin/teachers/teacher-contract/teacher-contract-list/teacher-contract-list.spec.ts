import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TeacherContract } from './teacher-contract-list';

describe('TeacherContract', () => {
  let component: TeacherContract;
  let fixture: ComponentFixture<TeacherContract>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeacherContract],
    }).compileComponents();

    fixture = TestBed.createComponent(TeacherContract);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
