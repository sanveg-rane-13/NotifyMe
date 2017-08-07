import { Component, OnInit } from '@angular/core';
import { MostViewedProductService } from './most-viewed-product.service';
@Component({
  selector: 'app-most-viewed-product',
  templateUrl: './most-viewed-product.component.html',
  styleUrls: ['./most-viewed-product.component.css'],
  providers: [MostViewedProductService]
})
export class MostViewedProductComponent implements OnInit {
  products: any = [
{productName:'Lenovo',productId:1},
{productName:'One Plus One',productId:2},
{productName:'Laptop',productId:3}
  ];
selectedProduct:any;
  constructor(private _service: MostViewedProductService) { }

  ngOnInit() {
    console.log(this.products);
    this._service.getMostViewedProduct()
      .subscribe(products => { this.products = products; console.log(this.products) });
  }
    showDiv(currentProduct){
      // console.log(index);
      this.selectedProduct=currentProduct;
      console.log(this.selectedProduct);
    }

}
