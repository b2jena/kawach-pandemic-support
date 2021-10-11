import { Component, OnInit } from '@angular/core';
import { CloseSos, CloseSosService, Requirement } from 'src/app/services/close-sos.service';


@Component({
  selector: 'app-close-sos-equipment',
  templateUrl: './close-sos-equipment.component.html',
  styleUrls: ['./close-sos-equipment.component.css']
})
export class CloseSosEquipmentComponent implements OnInit {
  closeSos !: CloseSos[];
  constructor(private closeSosService: CloseSosService) { }

  ngOnInit(){
    this.closeSosService.getEquipSOS().subscribe(
      response => this.handleSuccessfulResponse(response),
    );
  }
  handleSuccessfulResponse(response: CloseSos[]){
    this.closeSos = response;
  }
}
