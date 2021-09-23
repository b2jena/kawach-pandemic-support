import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientOtpComponent } from './patient-otp.component';

describe('PatientOtpComponent', () => {
  let component: PatientOtpComponent;
  let fixture: ComponentFixture<PatientOtpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientOtpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientOtpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
