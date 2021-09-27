import { Component, OnInit } from '@angular/core';
import { InformationService, LocationStats } from 'src/app/services/information.service';

@Component({
  selector: 'app-latest-information',
  templateUrl: './latest-information.component.html',
  styleUrls: ['./latest-information.component.css']
})
export class LatestInformationComponent implements OnInit {

  locationStats!: LocationStats[];
  constructor(private InfoService: InformationService) { }

  ngOnInit(): void {
    this.InfoService.getInformation().subscribe(data => {
      console.log(this.locationStats = data);
    });
  }

}
