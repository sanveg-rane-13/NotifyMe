import { Injectable } from '@angular/core';
import { Http, Response,RequestOptions,Headers } from '@angular/http';
import 'rxjs/add/operator/map'
import { Product } from './product';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class ProductService {
      product: any;
       headers1:Headers=new Headers() ;
      tokens={
            id:'',
            session:''
      }
      options:RequestOptions;
       constructor(private http: Http) {
      
            
      }
       setheaders(){
             this.headers1.append('Content-Type', 'application/json');      
            this.headers1.append('id',sessionStorage.getItem('email'));
            this.headers1.append('session',sessionStorage.getItem('token'));
             
     this.options = new RequestOptions({ headers: this.headers1 });
       }
      getProduct() {
            return this.product;
      }
      addProduct(product) {
            this.product = product;
            this.setheaders();
            console.log(this.product);
            return this.http.post('http://10.1.16.69:8080/addCriteria', this.product,this.options).map(resp => resp.json());
      }
      searchProductStarting(text):
            Observable<Product[]> {
                  this.setheaders();
            return this.http
                  .get('http://10.1.16.69:8080/getProductsLike?query='+text,this.options)
                  .map((r: Response) => r.json());
      }


}
    