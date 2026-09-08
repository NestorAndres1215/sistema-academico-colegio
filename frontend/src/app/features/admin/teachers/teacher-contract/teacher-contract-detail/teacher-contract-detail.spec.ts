import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TeacherContractDetail } from './teacher-contract-detail';

describe('TeacherContractDetail', () => {
  let component: TeacherContractDetail;
  let fixture: ComponentFixture<TeacherContractDetail>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeacherContractDetail],
    }).compileComponents();

    fixture = TestBed.createComponent(TeacherContractDetail);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
