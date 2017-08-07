import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators, AbstractControl, Validator } from '@angular/forms';
import { RegisterService } from '../register.service';
import { Http, Headers } from '@angular/http';
import { LoginService } from '../login-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userLoginForm: FormGroup;
  submitted = false;
  data: any;
  private headers = new Headers();
  result: any;
  constructor(private _fb: FormBuilder,
    private http: Http,
    private loginService: LoginService,
    private router: Router
  ) { }

  ngOnInit() {
    this.buildForm();

  }

  buildForm() {
    this.userLoginForm = this._fb.group({
      'email': ['', [Validators.required, Validators.email]],
      'password': ['', [Validators.required]],


    });
  }


  login() {
    let username = this.userLoginForm.controls.email.value;
    let password = this.userLoginForm.controls.password.value;
    if (username != null && password != null) {
      this.loginService.validate(username, password);

    }
    if (this.loginService.isloggedIn()) {
      console.log('Login successful');

      if (this.loginService.isAdmin()) this.router.navigate(['\admin']);
      if (this.loginService.isUser()) this.router.navigate(['user']);
    }
  }
  isloggedIn() {

    return !this.loginService.isloggedIn();
  }
  logout() {
    this.loginService.logout();
  }



}

























