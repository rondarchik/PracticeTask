import { TestBed } from '@angular/core/testing';

import { CreditRequestService } from './credit-request.service';

describe('CreditRequestService', () => {
  let service: CreditRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreditRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
