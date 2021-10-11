import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarroomVerifyBedComponent } from './warroom-verify-bed.component';

describe('WarroomVerifyBedComponent', () => {
  let component: WarroomVerifyBedComponent;
  let fixture: ComponentFixture<WarroomVerifyBedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarroomVerifyBedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WarroomVerifyBedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
