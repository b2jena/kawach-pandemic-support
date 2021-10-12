import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CloseSos, CloseSosService, Requirement } from 'src/app/services/close-sos.service';
import { SearchDialogComponent } from '../search-dialog/search-dialog.component';
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
  constructor(private closeSosService: CloseSosService, private dialog: MatDialog){ }
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

  close(message: string): void{
    this.closeSos.formStatus = message;
    this.closeSosService.closeSOS(this.closeSos).subscribe(data1 => {
      console.log(this.closeSos = data1);
      this.closeSosService.getSOSMed().subscribe(data2 => {
        console.log(this.closeSos = data2);
      });
    }); 
  }

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
    const messageDialog = this.dialog.open(SearchDialogComponent, {
      disableClose: true,
      width: '700px',
      height: '420px',
      data:
      {
        requirement: this.closeSos.requirement,
        type: 'medicine',
        city: this.closeSos.city,
        close: false,
        message: ''
      }
    });
    messageDialog.afterClosed().subscribe((result) => {
      if (result.close) {
        this.close(result.message);
      }
    })
  }

  // handleSuccessfulResponse(response: CloseSos){
  //   this.closeSos = response;
  // }
}
  // closeSos : any[];
  // user1: Requirement = new Requirement('', '', '');
  // list: Array<Requirement> = [];
  // closeSos : CloseSos = new CloseSos('', '', '', this.list, '', '', '', '', '');
