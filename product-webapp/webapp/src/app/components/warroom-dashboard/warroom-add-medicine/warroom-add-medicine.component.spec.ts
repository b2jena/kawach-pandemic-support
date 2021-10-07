import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarroomAddMedicineComponent } from './warroom-add-medicine.component';

describe('WarroomAddMedicineComponent', () => {
  let component: WarroomAddMedicineComponent;
  let fixture: ComponentFixture<WarroomAddMedicineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarroomAddMedicineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WarroomAddMedicineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
