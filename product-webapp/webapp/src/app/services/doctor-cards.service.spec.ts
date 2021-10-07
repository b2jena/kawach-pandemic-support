import { TestBed } from '@angular/core/testing';

import { DoctorCardsService } from './doctor-cards.service';

describe('DoctorCardsService', () => {
  let service: DoctorCardsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DoctorCardsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
