import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VolunteerRevertComponent } from './volunteer-revert.component';

describe('VolunteerRevertComponent', () => {
  let component: VolunteerRevertComponent;
  let fixture: ComponentFixture<VolunteerRevertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VolunteerRevertComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VolunteerRevertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
