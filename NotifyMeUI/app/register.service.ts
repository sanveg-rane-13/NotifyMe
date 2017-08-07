import { Injectable } from '@angular/core';
import {Http,Response,RequestOptions,Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable' ;
// import {FileUploader} from 'ng2-file-upload';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
@Injectable()
export class RegisterService {

registrationUrl="http://localhost:8080/SignUpUser";
loginUrl="http://localhost:8080/login?";
getUserUrl="http://localhost:8080/getUser?email=";
  constructor(private _http:Http) { }

  addUser(user:any){
    let headers1:Headers=new Headers() ;
    headers1.append('Content-Type', 'application/json');
    headers1.append('auth', 'true');

     let options = new RequestOptions({ headers: headers1 });
     return this._http.post(this.registrationUrl,user)
                      .map((res:Response) => res.json()) // ...and calling .json() on the response to return data
                         .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
     
  }

  getUser(email:string,password:string){
    console.log(this.loginUrl+"email="+email+"&password="+password);
     return this._http.get(this.loginUrl+"email="+email+"&password="+password)
     .map(data=>{data.json()})
     .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getEmail(email:any){
     return this._http.get(this.getUserUrl+email).map((res:Response) => res.json());
     
  }

}
