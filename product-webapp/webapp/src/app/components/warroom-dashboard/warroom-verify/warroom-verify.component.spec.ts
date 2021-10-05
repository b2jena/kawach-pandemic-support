import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarroomVerifyComponent } from './warroom-verify.component';

describe('WarroomVerifyComponent', () => {
  let component: WarroomVerifyComponent;
  let fixture: ComponentFixture<WarroomVerifyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarroomVerifyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WarroomVerifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
