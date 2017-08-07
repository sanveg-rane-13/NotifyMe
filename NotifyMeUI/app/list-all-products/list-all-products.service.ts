import { Injectable } from '@angular/core';
import { Http, HttpModule, Response ,RequestOptions,Headers} from '@angular/http';
@Injectable()
export class ListAllProductsService {

headers1:Headers=new Headers() ;
options:RequestOptions;
  constructor(private _http: Http) { }

  productsUrl: string = "http://10.1.18.61:8080/getNotifiedProducts?email=sonia.martis@wissen.com"
  getAllProducts() {
    return this._http.get(this.productsUrl)
      .map((response: Response) => response.json())
      
  }
}
