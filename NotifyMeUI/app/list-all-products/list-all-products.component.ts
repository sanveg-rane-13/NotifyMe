import { Component, OnInit } from '@angular/core';
import { MyProducts } from './MyProducts';
import { ListAllProductsService } from './list-all-products.service';
@Component({
  selector: 'app-list-all-products',
  templateUrl: './list-all-products.component.html',
  styleUrls: ['./list-all-products.component.css'],
  providers: [ListAllProductsService]
})
export class ListAllProductsComponent implements OnInit {
  title = "List of your Products";
  selectedIndex = -1;
  products: any 
  = [
    {
      productName:'Lenovo',
      criteria:'13000 to 15000',
      price:25000

    }
    
  ];
  currentProduct:any;


  showDiv(currentProduct) {
    // console.log(this.products[index-1].productName);
    
  
      // this.selectedIndex = index;
      // this.currentProduct=this.products[this.selectedIndex-1];
      this.currentProduct=currentProduct;
      console.log(this.currentProduct);
  }

  statusChange(status: string, index: number) {
    console.log("before" + status)
    if (status === 'Un-Notified') {
      console.log(this.products[index].status = 'Notified');
    }
    else if (status === 'Notified') {
      console.log(this.products[index].status = 'Un-Notified');
    }
  }

  constructor(private _productService: ListAllProductsService) { }

  ngOnInit() {
     this._productService.getAllProducts()
      .subscribe(products => {console.log(products);this.products = products})
    
  }

}
