import { Injectable } from '@angular/core';
import {Http,HttpModule} from '@angular/http';
@Injectable()
export class StatisticsService {
urlIndex=1;

statisticsUrl="http://10.1.16.12:8080/productPrevStats?productId=";


  constructor(private _http:Http) { }

  getStatisticsData(index):any{
    console.log((this.urlIndex=index));
    return this._http.get(this.statisticsUrl+index)
    .map((response)=>response.json());
  }
  

}
