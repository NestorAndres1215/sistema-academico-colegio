import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataDetail } from './data-detail';

describe('DataDetail', () => {
  let component: DataDetail;
  let fixture: ComponentFixture<DataDetail>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DataDetail]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DataDetail);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
