import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObservationDetail } from './observation-detail';

describe('ObservationDetail', () => {
  let component: ObservationDetail;
  let fixture: ComponentFixture<ObservationDetail>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ObservationDetail]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ObservationDetail);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
