import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserInactive } from './user-inactive';

describe('UserInactive', () => {
  let component: UserInactive;
  let fixture: ComponentFixture<UserInactive>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserInactive]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserInactive);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
