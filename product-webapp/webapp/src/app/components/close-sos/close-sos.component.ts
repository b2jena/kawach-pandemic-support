import { Component, OnInit } from '@angular/core';
import { CloseSos, CloseSosService } from 'src/app/services/close-sos.service';
//import { Router } from ' @angular/router';

@Component({
  selector: 'app-close-sos',
  templateUrl: './close-sos.component.html',
  styleUrls: ['./close-sos.component.css']
})
export class CloseSosComponent implements OnInit {
  closeSos!:CloseSos;
  constructor(private closeSosService: CloseSosService) { }
  ngOnInit(){
    this.closeSosService.getSos().subscribe(
      response=>this.handleSuccessfulResponse(response),
    );
  }
  handleSuccessfulResponse(response : CloseSos){
    this.closeSos=response;
  }
}

