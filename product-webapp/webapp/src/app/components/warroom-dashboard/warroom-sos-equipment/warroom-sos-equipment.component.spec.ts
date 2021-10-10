import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarroomSosEquipmentComponent } from './warroom-sos-equipment.component';

describe('WarroomSosEquipmentComponent', () => {
  let component: WarroomSosEquipmentComponent;
  let fixture: ComponentFixture<WarroomSosEquipmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarroomSosEquipmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WarroomSosEquipmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
