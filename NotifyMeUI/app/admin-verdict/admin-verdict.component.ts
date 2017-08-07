import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin-service';
import { Router } from '@angular/router';
import {FormBuilder,Validators,FormGroup} from '@angular/forms'

@Component({
  selector: 'app-admin-verdict',
  templateUrl: './admin-verdict.component.html',
  styleUrls: ['./admin-verdict.component.css']
})
export class AdminVerdictComponent implements OnInit {
  error:any;
  adminForm:FormGroup;
  constructor(private adminService:AdminService,
              private router:Router,
              private fb:FormBuilder      ) { }

  ngOnInit() {
     window.addEventListener( "message",
          function (e) {
              console.log(e.origin);
              
               console.log(e.data);
               localStorage.setItem("xpath",  e.data[0]);
        localStorage.setItem("xpath_value",e.data[1]);
          },
          false);
   this.error=this.adminService.getError();
   console.log(this.error);
   this.adminForm=this.fb.group({
        URL : [this.error.oldUrl],
        name: [this.error.productName],
        price: [''],
        xpath: [''],
        verdict : ['1']
        
  });
  }
  setForm(){
      if(this.adminForm.valid){
         this.error.newUrl=(<HTMLInputElement>document.getElementById('URL')).value;
         this.error.productName=(<HTMLInputElement>document.getElementById('name')).value;
          this.error.verdict=this.adminForm.value.verdict;
      }
  }
 sendVerdict(){
     console.log(this.adminForm.value);
     this.setForm();
     
     console.log(this.error);
     this.adminService.sendError(this.error).subscribe(res=>{
         console.log(res.message);
        // this.router.navigate(['adminList']);
     });
 }
 setVal(id:any) {
            var val = localStorage.xpath_value;
            if(id=='xpath')val=localStorage.xpath;
            if(id=='price')val=this.getNumber(val);
            
            (<HTMLInputElement>document.getElementById(id)).value = val;
            console.log(localStorage.xpath + '    ' + localStorage.xpath_value);
            if(id=='xpath')this.error.newXpath=val;
            
        }
getNumber(id:any){
    id=id.trim();
    id= parseFloat(id.replace(",",""));
    return id;
}
         UserAction(url) {
           
            var testIframe = document.getElementById('test');
            var iframe = <HTMLIFrameElement>testIframe;
            iframe.src="http://10.1.16.69:8080/searchProduct?url=" + url;
           
            
        
         }
         load_home() {

            var y = (<HTMLInputElement>document.getElementById('URL')).value;
            console.log(y);
            var response = "could not load :(";
            if (y) {
                
                this.UserAction(y);
            }
        }
 

}
