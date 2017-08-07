import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
@Injectable()
export class HistoryService {
products:any=[{}];
 

    constructor(private _http:Http) { }
historyUrl:string="http://10.1.18.61:8080/getNotifiedProducts?email=sonia.martis@wissen.com";
  getHistoryData() {

    return this._http.get(this.historyUrl)
    .map((response)=>response.json());
    
  }
}
