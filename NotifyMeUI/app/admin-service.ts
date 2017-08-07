
import {Injectable} from '@angular/core';
import { Http,RequestOptions,Headers } from '@angular/http';
import  'rxjs/add/operator/map'

@Injectable()
export class AdminService{

  // errors:any=  [{"id":45,"errorType":1,"verdict":0,"productName":"red","newUrl":"","newXpath":"","oldUrl":"xyz"},{"id":46,"errorType":1,"verdict":0,"productName":"red","newUrl":"","newXpath":"","oldUrl":"qwe"},{"id":47,"errorType":1,"verdict":0,"productName":"laptopB","newUrl":"","newXpath":"","oldUrl":"kfs"},{"id":48,"errorType":1,"verdict":0,"productName":"sam","newUrl":"","newXpath":"","oldUrl":"yub"},{"id":49,"errorType":1,"verdict":0,"productName":"oneplus","newUrl":"","newXpath":"","oldUrl":"jsa"},{"id":50,"errorType":1,"verdict":0,"productName":"sam","newUrl":"","newXpath":"","oldUrl":"tvg"},{"id":51,"errorType":2,"verdict":0,"productName":"laptopA","newUrl":"","newXpath":"","oldUrl":"http://www.google.com"},{"id":52,"errorType":2,"verdict":0,"productName":"red","newUrl":"","newXpath":"","oldUrl":"https://www.facebook.com"}];
  errors:any; 
  options:any;
  headers1:Headers=new Headers();
  error:any={"id":0,"errorType":1,"verdict":0,"productName":" ","newUrl":"","newXpath":"","oldUrl":" "};
constructor(private http:Http){
          
            
}
setheaders(){
this.headers1.append('Content-Type', 'application/json');
            this.headers1.append('admin','true')
            this.headers1.append('id',sessionStorage.getItem('userId'));
            this.headers1.append('session',sessionStorage.getItem('token'));
             
     this.options = new RequestOptions({ headers: this.headers1 });
}
getErrorList(){
  this.setheaders();
    return this.http.get('http://10.1.18.104:8080/getErrors',this.options).map(resp=>resp.json());
}
  sendError(error){
    this.setheaders();
    return this.http.post('http://10.1.18.104:8080/resolveError',error,this.options).map(resp=>resp.json());

  }
saveError(error:any){
  this.error=error;
}
getError(){
  return this.error;
}
}