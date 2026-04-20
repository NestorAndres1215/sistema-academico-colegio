import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObservationTeacher } from './observation-teacher';

describe('ObservationTeacher', () => {
  let component: ObservationTeacher;
  let fixture: ComponentFixture<ObservationTeacher>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ObservationTeacher]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ObservationTeacher);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
