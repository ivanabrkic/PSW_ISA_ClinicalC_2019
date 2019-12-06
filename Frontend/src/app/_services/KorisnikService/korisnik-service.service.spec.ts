import { TestBed } from '@angular/core/testing';

import { KorisnikServiceService } from './korisnik-service.service';

describe('KorisnikServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: KorisnikServiceService = TestBed.get(KorisnikServiceService);
    expect(service).toBeTruthy();
  });
});
