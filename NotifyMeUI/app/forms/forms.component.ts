import { Component, OnInit } from '@angular/core';
import{FormBuilder,FormGroup,Validators} from '@angular/forms';
@Component({
  selector: 'app-forms',
  templateUrl: './forms.component.html',
  styleUrls: ['./forms.component.css']
})
export class FormsComponent  {

  inputForm:FormGroup;
  post:any;
  password:string;
  name:string;


  constructor(private fb:FormBuilder){
    this.inputForm=fb.group({
      'name':[null,Validators.required],
      'password':[null,Validators.compose([Validators.minLength(8)])]
    });
  }

  checkUser(post){
    this.name=post.name;
    this.password=post.password;
  }
 

}
