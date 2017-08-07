import { TestBed, inject } from '@angular/core/testing';

import { MostViewedProductService } from './most-viewed-product.service';

describe('MostViewedProductService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MostViewedProductService]
    });
  });

  it('should be created', inject([MostViewedProductService], (service: MostViewedProductService) => {
    expect(service).toBeTruthy();
  }));
});
