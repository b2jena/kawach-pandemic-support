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
  isActive = false;
  count = 0;
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

  close(): void{
      this.closeSos.formStatus = "CLOSE";
      this.closeSosService.closeSOS(this.closeSos).subscribe(data1 => {
        console.log(this.closeSos = data1);
        this.closeSosService.getSOSMed().subscribe(data2 => {
          console.log(this.closeSos = data2);
        });
      }); }

  pass(): void{
    this.count += 1;
    this.closeSosService.getSOSMed().subscribe(data => {
      console.log(this.closeSos = data);
    });
  }

  check() {
    this.isActive = !this.isActive;
  }

  search(){
    
  }

  // handleSuccessfulResponse(response: CloseSos){
  //   this.closeSos = response;
  // }
}
  // closeSos : any[];
  // user1: Requirement = new Requirement('', '', '');
  // list: Array<Requirement> = [];
  // closeSos : CloseSos = new CloseSos('', '', '', this.list, '', '', '', '', '');
