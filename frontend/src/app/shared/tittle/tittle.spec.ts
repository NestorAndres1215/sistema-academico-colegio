import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Tittle } from './tittle';

describe('Tittle', () => {
  let component: Tittle;
  let fixture: ComponentFixture<Tittle>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Tittle]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Tittle);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
