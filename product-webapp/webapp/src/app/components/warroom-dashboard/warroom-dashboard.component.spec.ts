import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarroomDashboardComponent } from './warroom-dashboard.component';

describe('WarroomDashboardComponent', () => {
  let component: WarroomDashboardComponent;
  let fixture: ComponentFixture<WarroomDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarroomDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WarroomDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
