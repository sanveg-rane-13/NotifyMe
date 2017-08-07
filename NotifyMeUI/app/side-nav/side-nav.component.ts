import { Component } from '@angular/core';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.css']
})
export class SideNavComponent  {

  constructor(
  
  ){

  }
// showMenu:boolean=true;
sideNav:boolean=true;
// toggleMenu(){
//   console.log(this.showMenu);
//   this.showMenu=!this.showMenu
// }
 
openNav(){
  console.log("n");
this.sideNav=!this.sideNav;
}



}
