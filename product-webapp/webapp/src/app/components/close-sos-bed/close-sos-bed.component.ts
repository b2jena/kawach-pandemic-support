import { Component, OnInit } from '@angular/core';
import { CloseSos, CloseSosService, Requirement } from 'src/app/services/close-sos.service';

@Component({
  selector: 'app-close-sos-bed',
  templateUrl: './close-sos-bed.component.html',
  styleUrls: ['./close-sos-bed.component.css']
})
export class CloseSosBedComponent implements OnInit {
  closeSos !: CloseSos[];
  constructor(private closeSosService: CloseSosService) { }

  ngOnInit(){
    this.closeSosService.getBedSOS().subscribe(
      response => this.handleSuccessfulResponse(response),
    );
  }
  handleSuccessfulResponse(response: CloseSos[]){
    this.closeSos = response;
  }
}
