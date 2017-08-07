import { Injectable, OnInit } from '@angular/core';
import { Http, HttpModule, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
@Injectable()
export class MostViewedProductService {


  mostViewedProductUrl = "http://10.1.18.61:8080/mostSearched";



  constructor(private _http: Http) { }

  getMostViewedProduct(): Observable<any> {
    return this._http.get(this.mostViewedProductUrl)
      .map((response: Response) => response.json())
      .do(data => console.log(JSON.stringify(data)));
  }


  ngOnInit() {

  }
}
