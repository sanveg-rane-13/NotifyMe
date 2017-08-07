import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductSuggestComponent } from './product-suggest.component';

describe('ProductSuggestComponent', () => {
  let component: ProductSuggestComponent;
  let fixture: ComponentFixture<ProductSuggestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductSuggestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductSuggestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
