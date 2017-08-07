import { UserResolverService } from './dashboard/graph/user-resolver.service';
import { AdminService } from './admin-service';
import { AdminGuardService } from './AdminGuardService';
import { UserGuardService } from './UserGuardService';
import { LoginService } from './login-service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Router } from '@angular/router'
import { HttpModule } from '@angular/http';
import { ChartsModule } from 'ng2-charts';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GraphService } from './dashboard/graph/graph-service';
import { AppComponent } from './app.component';
import { SideNavComponent } from './side-nav/side-nav.component';
import { MdSidenavModule } from '@angular/material';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ListAllProductsComponent } from './list-all-products/list-all-products.component';
import { AddProductComponent } from './add-product/add-product.component';
import { GraphComponent } from './dashboard/graph/graph.component';
import { MostViewedProductComponent } from './dashboard/most-viewed-product/most-viewed-product.component';
import { FormsComponent } from './forms/forms.component';
import { HistoryComponent } from './history/history.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { LoginComponent } from './login/login.component';

import { RegisterComponent } from './register/register.component';
import { RegisterService } from './register.service';
import { AdminListComponent } from './admin-list/admin-list.component';
import { AdminVerdictComponent } from './admin-verdict/admin-verdict.component';
import { ProductSuggestComponent } from './product-suggest/product-suggest.component';
import { LandingPageComponent } from './landing-page/landing-page.component';


@NgModule({
  declarations: [
    AppComponent,
    SideNavComponent,
    DashboardComponent,
    ListAllProductsComponent,
    AddProductComponent,
    GraphComponent,
    MostViewedProductComponent,
    FormsComponent,
    HistoryComponent,
    StatisticsComponent,
    LoginComponent,
    RegisterComponent,
    AdminListComponent,
    AdminVerdictComponent,
    ProductSuggestComponent,
    LandingPageComponent

  ],
  imports: [
    BrowserModule,
    MdSidenavModule,
    ChartsModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    RouterModule.forRoot([


      { path: 'dashboard', component: DashboardComponent ,resolve:{res:UserResolverService}},
      { path: 'addProduct', component: AddProductComponent },
      { path: 'myProducts', component:ListAllProductsComponent },
      { path: 'login', component: LoginComponent },
      { path: 'statistics', component: StatisticsComponent },
      { path: 'history', component: HistoryComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'adminList', component: AdminListComponent},
      {path:'',component:LandingPageComponent}
      
    ])

  ],
  providers: [GraphService, RegisterService, LoginService, UserGuardService, AdminGuardService, AdminService],
  bootstrap: [AppComponent]
})
export class AppModule { }
