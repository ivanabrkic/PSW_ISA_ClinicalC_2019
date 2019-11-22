import { TestBed } from '@angular/core/testing';

import { KlinikaServiceService } from './klinika-service.service';

describe('KlinikaServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: KlinikaServiceService = TestBed.get(KlinikaServiceService);
    expect(service).toBeTruthy();
  });
});
