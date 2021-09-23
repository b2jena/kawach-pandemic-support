import { TestBed } from '@angular/core/testing';

import { PatientOtpServiceService } from './services/patient-otp-service.service';

describe('PatientOtpServiceService', () => {
  let service: PatientOtpServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientOtpServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
