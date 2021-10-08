import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarroomAddBedComponent } from './warroom-add-bed.component';

describe('WarroomAddBedComponent', () => {
  let component: WarroomAddBedComponent;
  let fixture: ComponentFixture<WarroomAddBedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarroomAddBedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WarroomAddBedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
