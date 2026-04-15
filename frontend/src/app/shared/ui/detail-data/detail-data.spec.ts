import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailData } from './detail-data';

describe('DetailData', () => {
  let component: DetailData;
  let fixture: ComponentFixture<DetailData>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailData]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailData);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
