import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifyMedicineComponent } from './verify-medicine.component';

describe('VerifyMedicineComponent', () => {
  let component: VerifyMedicineComponent;
  let fixture: ComponentFixture<VerifyMedicineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerifyMedicineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VerifyMedicineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
