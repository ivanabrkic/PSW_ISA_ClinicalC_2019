import { TestBed } from '@angular/core/testing';

import { AdminKlinikeServiceService } from './admin-klinike-service.service';

describe('AdminKlinikeServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdminKlinikeServiceService = TestBed.get(AdminKlinikeServiceService);
    expect(service).toBeTruthy();
  });
});
