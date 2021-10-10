import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarroomSosBedComponent } from './warroom-sos-bed.component';

describe('WarroomSosBedComponent', () => {
  let component: WarroomSosBedComponent;
  let fixture: ComponentFixture<WarroomSosBedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarroomSosBedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WarroomSosBedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
