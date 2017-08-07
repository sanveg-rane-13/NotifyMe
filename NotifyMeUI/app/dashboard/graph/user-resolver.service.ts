import { Injectable } from '@angular/core';
import {Resolve,ActivatedRoute,ActivatedRouteSnapshot} from '@angular/router';
import {GraphService} from './graph-service';
@Injectable()
export class UserResolverService  implements Resolve<any>{

  constructor(private route:ActivatedRoute,private getDetails:GraphService) { }
resolve(){
  console.log("graph data");
  return this.getDetails.getGraphData();
}

}
