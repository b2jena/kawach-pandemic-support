import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarroomVerifyMedicineComponent } from './warroom-verify-medicine.component';

describe('WarroomVerifyMedicineComponent', () => {
  let component: WarroomVerifyMedicineComponent;
  let fixture: ComponentFixture<WarroomVerifyMedicineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarroomVerifyMedicineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WarroomVerifyMedicineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
