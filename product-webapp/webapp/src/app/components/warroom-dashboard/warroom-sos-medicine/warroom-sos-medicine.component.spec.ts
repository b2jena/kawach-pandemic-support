import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarroomSosMedicineComponent } from './warroom-sos-medicine.component';

describe('WarroomSosMedicineComponent', () => {
  let component: WarroomSosMedicineComponent;
  let fixture: ComponentFixture<WarroomSosMedicineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarroomSosMedicineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WarroomSosMedicineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
