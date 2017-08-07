
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from './login-service';

@Injectable()
export class UserGuardService implements CanActivate {

  constructor(private loginService:LoginService,
              private router:Router  ) {}

  canActivate() {
    if( this.loginService.isUser()){
        return true;
    }
     this.router.navigate(['/login']);
     return false;
  }
  }
