import { Component, OnInit } from '@angular/core';
import { CloseSos, CloseSosService, Requirement } from 'src/app/services/close-sos.service';

@Component({
  selector: 'app-close-sos-bed',
  templateUrl: './close-sos-bed.component.html',
  styleUrls: ['./close-sos-bed.component.css']
})
export class CloseSosBedComponent implements OnInit {
  public closeSos !: CloseSos;
  isActive = false;
  count = 0;
  constructor(private closeSosService: CloseSosService) { }

  ngOnInit(): void {
    this.closeSosService.getSOSBed().subscribe(data => {
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
    this.closeSosService.getSOSBed().subscribe(data => {
      console.log(this.closeSos = data);
    });
  }

  check() {
    this.isActive = !this.isActive;
  }

  search(){
    
  }

  // ngOnInit(){
  //   this.closeSosService.getSOSBed().subscribe(
  //     response => this.handleSuccessfulResponse(response),
  //   );
  // }
  // handleSuccessfulResponse(response: CloseSos){
  //   this.closeSos = response;
  // }
}
