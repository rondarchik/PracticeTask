import { TestBed } from '@angular/core/testing';

import { CreditTypeService } from './credit-type.service';

describe('CreditTypeService', () => {
  let service: CreditTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreditTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
