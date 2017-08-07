import { Component, OnInit } from '@angular/core';
import {HistoryService} from './history.service';
@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css'],
  providers:[HistoryService]
})
export class HistoryComponent implements OnInit {
products=[{}];
  constructor(private _historyService:HistoryService) { }
selectedIndex:number=-1;
ngOnInit(){
this._historyService.getHistoryData()
.subscribe(products=>this.products=products)
}

showDiv(index){
  if (this.selectedIndex === index)
      this.selectedIndex = -1;
  else
  this.selectedIndex=index;
console.log(index);
}
}
