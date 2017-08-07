import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MostViewedProductComponent } from './most-viewed-product.component';

describe('MostViewedProductComponent', () => {
  let component: MostViewedProductComponent;
  let fixture: ComponentFixture<MostViewedProductComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MostViewedProductComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MostViewedProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
