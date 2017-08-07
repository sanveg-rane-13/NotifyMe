import { Http, RequestOptions, Headers } from '@angular/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class LoginService {

    result:any;
    constructor(private http: Http,
                private router:Router) {}

    validate(username:string, password:string) {
    
    let headers1:Headers=new Headers() ;
    headers1.append('Content-Type', 'application/json');
    headers1.append('auth', 'true');

     let options = new RequestOptions({ headers: headers1 });
        this.http.get('http://10.1.16.69:8080/login?email=' + username + '&password=' + password,
        options)
             .map(resp => resp.json())
            .subscribe(resp => {
            this.result = resp;
                this.setSession(resp);
                console.log(this.result["token"]);
               console.log('Login successful');
        
         if (this.isAdmin()) this.router.navigate(['\admin']);
         if (this.isUser()) this.router.navigate(['user']);
             });
    }
    setSession(result:any) {
        if (this.result.email != 'Invalid Email') {
            sessionStorage.setItem('token', this.result.token.token); 
            sessionStorage.setItem('role', this.result.role);
            sessionStorage.setItem('email', this.result.email)
        }
    }
    isloggedIn() {
        if (sessionStorage.getItem('token') != null) return true;
        return false;
    }
    logout() {

        sessionStorage.removeItem('token');
        sessionStorage.removeItem('role');
        sessionStorage.removeItem('email');
        console.log("Logout");
    }
    isAdmin() {
        if (sessionStorage.getItem('role') === 'admin') {
            return true;
        }
        return false;
    }
    isUser() {
        if (sessionStorage.getItem('role') === 'user') {
            return true;
        }
        return false;
    }
    navigateToPage(){
        if(this.isUser())this.router.navigate(['/user']);
        if(this.isAdmin())this.router.navigate(['/admin']);
    }


}
