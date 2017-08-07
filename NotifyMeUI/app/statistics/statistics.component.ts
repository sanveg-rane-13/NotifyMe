import { Component, OnInit } from '@angular/core';
import { ListAllProductsService } from '../list-all-products/list-all-products.service';
import {StatisticsService} from './statistics.service';
@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css'],
  providers: [ListAllProductsService,StatisticsService]
})
export class StatisticsComponent implements OnInit {


  public barChartOptions: any = {
    scaleShowVerticalLines: false,
    responsive: false
  };
  public barChartLabels: string[] = [];
  public barChartType: string = 'bar';
  public barChartLegend: boolean = true;

  public barChartData: any[] = [
    { data: [], label: 'Series A' },
    // {data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B'}
  ];

  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }

  title = "Statistics of all your Products";
  selectedIndex = -1;
  products: any = [    {
      productName:'Lenovo',
      criteria:'13000 to 15000',
      price:25000

    }];


  


  showDiv(index: number) {
    if (this.selectedIndex === index)
      this.selectedIndex = -1
    else{
      this.selectedIndex = index;
      console.log(this.selectedIndex);
      this.barChartData[0].data=[]
      this.barChartLabels=[]

      this._statisticsDataService.getStatisticsData(this.selectedIndex)
      .subscribe(
        data=>{
          data.forEach(element=>{
          console.log("selected"+this.selectedIndex);
          this.barChartData[0].data.push(element.price);
          this.barChartLabels.push(element.date)
          console.log(this.barChartData[0].data.push(element.price));
          console.log (this.barChartLabels.push(element.date));
          
          });
        }
      );
  
    }
  }
constructor(private _serviceStatistics: ListAllProductsService,private _statisticsDataService:StatisticsService ){ }
  ngOnInit() {
    this._serviceStatistics.getAllProducts()

     
        .subscribe(products => this.products = products);


  
    
  }
}

