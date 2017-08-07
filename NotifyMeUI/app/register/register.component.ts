import { RegisterService } from './../register.service';
import { Component, NgModule, VERSION,OnInit } from '@angular/core';
import {FormGroup, FormControl, FormBuilder, Validators, AbstractControl, Validator} from '@angular/forms';
import {passwordValidator} from './../PasswordValidation';
import {User} from './../User'


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  
})
export class RegisterComponent implements OnInit {

  userSignUpForm: FormGroup;
  errors: string[];
  userNameMessage: string;
  submitted=false;
  user:User=new User();
  serverRegErrorMessage:string;                                                            
  registrationMessage:string;


  constructor(private _fb:FormBuilder,private registerService: RegisterService) { }

  ngOnInit() {
    this.buildForm();
    
  }



  buildForm(){
     this.userSignUpForm=this._fb.group({
      'email':['',[Validators.required,Validators.email], (control: FormControl) => {
      return new Promise((resolve, reject) => {
        // also add here is premium logic
        let requiredValidationResult = Validators.required(control);
        let emailValidationResult=Validators.email(control);
        if (requiredValidationResult ) {
          resolve(requiredValidationResult); // this will be {required: true}
          return;
        }
        if (emailValidationResult ) {
          resolve(emailValidationResult); // this will be {required: true}
          return;
        }

        this.registerService.getEmail(this.userSignUpForm.controls.email.value)
            .subscribe(response=> {
                
                //let data=response;
                //console.log(response);
                //let mail = response._body;
                if (response.email == this.userSignUpForm.controls.email.value ) {
                    console.log("executed");
                    resolve({emailExists:true});
                    this.serverRegErrorMessage=response.message;
                    return;
                } else {
                    resolve(null);
                    return;
                }
                
            },
            (err)=>{
              console.log(err);
            });
           


        // and here call your server and call
        //   resolve(null) in case it's fine
        //   resolve({ emailExists: true }) in case it's existing
      });
    }],


      'passWordGroup':this._fb.group({
         'password':['',[Validators.required,Validators.minLength(5)]],
         'confirmPassword':['',[Validators.required,Validators.minLength(5)]]
       }
        ,{validator: passwordValidator}
      ),


      'name':['',Validators.required],

      'phoneNumber':['',[Validators.required,Validators.pattern('^[879][0-9]{9}$')]]

      
    });

   

  }



 

  saveUser(){
    //event.preventDefault();
    
    console.log(this.userSignUpForm.value);
    console.log("user added");
    this.buildForm();
  }

  onSubmit(userForm:any,image:any){
  
     this.submitted=true;
     console.log(JSON.stringify(userForm.value));
     this.user.email=this.userSignUpForm.controls.email.value;
     this.user.name=this.userSignUpForm.controls.name.value;
     this.user.password=this.userSignUpForm.get('passWordGroup').get('password').value;
     this.user.phoneNumber=this.userSignUpForm.controls.phoneNumber.value;
     
     this.registerService.addUser(this.user).subscribe(resp=>
                                                            {
                                                              this.registrationMessage=resp.message;
                                                              console.log(this.registrationMessage);
                                                            });
     
   }


}