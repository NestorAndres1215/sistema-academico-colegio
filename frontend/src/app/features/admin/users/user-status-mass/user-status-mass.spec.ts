import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UserStatusMass } from './user-status-mass';

describe('UserStatusMass', () => {
  let component: UserStatusMass;
  let fixture: ComponentFixture<UserStatusMass>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserStatusMass],
    }).compileComponents();

    fixture = TestBed.createComponent(UserStatusMass);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
