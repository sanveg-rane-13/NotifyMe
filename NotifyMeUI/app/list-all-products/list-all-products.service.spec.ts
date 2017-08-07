import { TestBed, inject } from '@angular/core/testing';

import { ListAllProductsService } from './list-all-products.service';

describe('ListAllProductsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ListAllProductsService]
    });
  });

  it('should be created', inject([ListAllProductsService], (service: ListAllProductsService) => {
    expect(service).toBeTruthy();
  }));
});
