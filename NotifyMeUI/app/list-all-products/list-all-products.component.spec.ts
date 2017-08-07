import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAllProductsComponent } from './list-all-products.component';

describe('ListAllProductsComponent', () => {
  let component: ListAllProductsComponent;
  let fixture: ComponentFixture<ListAllProductsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListAllProductsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListAllProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
