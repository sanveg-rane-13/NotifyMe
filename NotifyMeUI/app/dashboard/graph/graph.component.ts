import { UserResolverService } from './user-resolver.service';
import { Component, OnInit } from '@angular/core';
import { GraphService } from './graph-service';
@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css'],
  providers: [GraphService,UserResolverService]

})
export class GraphComponent {

  public lineChartData: Array<any> = [

    { data1: ['6767','878787','8989','9898'], label: 'Price(in ₹)' }
  ];

  public lineChartLabels: Array<any> = [];
  public lineChartOptions: any = {
    responsive: true
  };
  public lineChartColors: Array<any> = [
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];
  public lineChartLegend: boolean = true;
  public lineChartType: string = 'line';



  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }
  constructor(private _graphService: GraphService, public userResolver:UserResolverService) {

  }
  ngOnInit() {
    this.userResolver.data.subscribe(
      data1 => {

        data1.forEach(element => {


          this.lineChartData[0].data1.push(element.price);
          this.lineChartLabels.push(element.time);
        }

        );
        console.log(this.lineChartData[0]);
        console.log(this.lineChartLabels);
      });
    // this.lineChartData[0].data=data;
  }
}


