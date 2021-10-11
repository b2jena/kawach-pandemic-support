import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CloseSosEquipmentComponent } from './close-sos-equipment.component';

describe('CloseSosEquipmentComponent', () => {
  let component: CloseSosEquipmentComponent;
  let fixture: ComponentFixture<CloseSosEquipmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CloseSosEquipmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CloseSosEquipmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
