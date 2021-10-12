import { Component, OnInit } from '@angular/core';
import { CloseSos, CloseSosService, Requirement } from 'src/app/services/close-sos.service';
// import { Router } from ' @angular/router';

@Component({
  selector: 'app-close-sos',
  templateUrl: './close-sos.component.html',
  styleUrls: ['./close-sos.component.css']
})
export class CloseSosComponent implements OnInit {
  public closeSos !: CloseSos;
  constructor(private closeSosService: CloseSosService){ }
  // ngOnInit() : void{
  //   this.closeSosService.getSOSMed().subscribe(
  //     response => this.handleSuccessfulResponse(response),
  //   );
  // }
  ngOnInit(): void {
    this.closeSosService.getSOSMed().subscribe(data => {
      console.log(this.closeSos = data);
    });
  }
  // handleSuccessfulResponse(response: CloseSos){
  //   this.closeSos = response;
  // }
}
  // closeSos : any[];
  // user1: Requirement = new Requirement('', '', '');
  // list: Array<Requirement> = [];
  // closeSos : CloseSos = new CloseSos('', '', '', this.list, '', '', '', '', '');
