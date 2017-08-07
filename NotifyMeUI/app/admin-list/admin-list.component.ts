import { Component, OnInit } from '@angular/core';
import {Http} from '@angular/http';
import { AdminService } from '../admin-service';
import {Router} from '@angular/router'

@Component({
  selector: 'app-admin-list',
  templateUrl: './admin-list.component.html',
  styleUrls: ['./admin-list.component.css']
})
export class AdminListComponent implements OnInit {

  errors:any=[];
  fixError(error:any){
    this.adminService.saveError(error);
    this.router.navigate(['/adminVerdict']);
  }
  constructor(private adminService:AdminService,
               private router:Router                               ) {

   }

  ngOnInit() {
    this.adminService.getErrorList().subscribe(error=>this.errors=error);
    //this.errors=this.adminService.getErrorList();
    console.log(this.errors);
  }

}
