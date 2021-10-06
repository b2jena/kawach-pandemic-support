import { Component, OnInit } from '@angular/core';
import { CloseSos, CloseSosService, Requirement } from 'src/app/services/close-sos.service';
// import { Router } from ' @angular/router';

@Component({
  selector: 'app-close-sos',
  templateUrl: './close-sos.component.html',
  styleUrls: ['./close-sos.component.css']
})
export class CloseSosComponent implements OnInit {
  closeSos !: CloseSos[];
  //closeSos : any[];
  // user1: Requirement = new Requirement('', '', '');
  // list: Array<Requirement> = [];
  // closeSos : CloseSos = new CloseSos('', '', '', this.list, '', '', '', '', '');
  
  constructor(private closeSosService: CloseSosService) { }
  ngOnInit(){
    this.closeSosService.getSos().subscribe(
      response => this.handleSuccessfulResponse(response),
    );
  }
  handleSuccessfulResponse(response: CloseSos[]){
    this.closeSos = response;
  }
}

