import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarroomAddEquipmentComponent } from './warroom-add-equipment.component';

describe('WarroomAddEquipmentComponent', () => {
  let component: WarroomAddEquipmentComponent;
  let fixture: ComponentFixture<WarroomAddEquipmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarroomAddEquipmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WarroomAddEquipmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
