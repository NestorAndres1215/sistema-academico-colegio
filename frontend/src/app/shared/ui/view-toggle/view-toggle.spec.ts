import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewToggle } from './view-toggle';

describe('ViewToggle', () => {
  let component: ViewToggle;
  let fixture: ComponentFixture<ViewToggle>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewToggle]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewToggle);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
