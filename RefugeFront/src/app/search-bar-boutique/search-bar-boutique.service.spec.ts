import { TestBed } from '@angular/core/testing';

import { SearchBarBoutiqueService } from './search-bar-boutique.service';

describe('SearchBarBoutiqueService', () => {
  let service: SearchBarBoutiqueService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchBarBoutiqueService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
