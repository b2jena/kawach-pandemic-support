import { TestBed } from '@angular/core/testing';

import { CloseSosService } from './close-sos.service';

describe('CloseSosService', () => {
  let service: CloseSosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CloseSosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
