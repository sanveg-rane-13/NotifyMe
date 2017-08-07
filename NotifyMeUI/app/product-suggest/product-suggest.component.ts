import { Component, OnInit,Output,EventEmitter } from '@angular/core';
import { Observable }        from 'rxjs/Observable';
import { Subject }        from 'rxjs/Subject';
import { ProductService } from '../product-service';
import { Product } from '../product';
import "rxjs/Rx";

@Component({
  selector: 'app-product-suggest',
  templateUrl: './product-suggest.component.html',
  styleUrls: ['./product-suggest.component.css']
})
export class ProductSuggestComponent implements OnInit {

  @Output()
   onProductPicked: EventEmitter<any> = new EventEmitter<any>();
  products:Observable<Product[]>;
  private searchTerms = new Subject<string>();
  constructor(private productService:ProductService) { }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.products = this.searchTerms
      .distinctUntilChanged()   // ignore if next search term is same as previous
      .switchMap(term => term   // switch to new observable each time
        // return the http search observable
        ? this.productService.searchProductStarting(term)
        // or the observable of empty heroes if no search term
        : Observable.of<Product[]>([]))
      .catch(error => {
        
        console.log(error);
        return Observable.of<Product[]>([]);
      });
  }
    gotoDetail(product: Product,event): void {
    event.preventDefault();
    this.products=null;
    this.onProductPicked.emit(product);
    //console.log(product);
  }
}


