import { Injectable } from '@angular/core';
import { Http, HttpModule, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/map';

@Injectable()
export class GraphService {

  constructor(private _http: Http) { }

  graphDataUrl: string = "http://10.1.16.35:8080/productStats?userId=srsanrocks1@gmail.com";

  getGraphData() {
    return this._http.get(this.graphDataUrl)
      .map((response: Response) => response.json());
    // .do(data=>console.log(JSON.stringify(data)));
  }

}
