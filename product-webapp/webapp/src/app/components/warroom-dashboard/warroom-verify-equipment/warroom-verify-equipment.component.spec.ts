import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarroomVerifyEquipmentComponent } from './warroom-verify-equipment.component';

describe('WarroomVerifyEquipmentComponent', () => {
  let component: WarroomVerifyEquipmentComponent;
  let fixture: ComponentFixture<WarroomVerifyEquipmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarroomVerifyEquipmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WarroomVerifyEquipmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
