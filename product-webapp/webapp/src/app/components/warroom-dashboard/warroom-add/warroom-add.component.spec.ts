import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarroomAddComponent } from './warroom-add.component';

describe('WarroomAddComponent', () => {
  let component: WarroomAddComponent;
  let fixture: ComponentFixture<WarroomAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarroomAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WarroomAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
