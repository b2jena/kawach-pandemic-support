import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { InformationService, LocationStats } from 'src/app/services/information.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-latest-information',
  templateUrl: './latest-information.component.html',
  styleUrls: ['./latest-information.component.css']
})
export class LatestInformationComponent implements OnInit, AfterViewInit {
  /**This is used for using angular material paginator to display table in
   * compact form
  */
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  displayedColumns: string[] = ['Location', 'Confirmed Cases (Indian)', 'Confirmed Cases (Foreign)', 'Discharged', 'Deaths', 'Total Confirmed'];
  public locationStat = new MatTableDataSource<any>([]);

  constructor(private InfoService: InformationService) { }

  /**This method is called when page loads which gets information about current 
   * cases from backend which in turn calls an external api
  */
  ngOnInit(): void {
    this.InfoService.getInformation().subscribe(data => {
      console.log(this.locationStat.data = data);
    });
  }
  /**After the page loads the angular material is rendered */
  ngAfterViewInit() {
    this.locationStat.paginator = this.paginator;
  }
}
