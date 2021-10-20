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
  
  ngOnInit(): void {
    this.closeSosService.getSOSMed().subscribe(data => {
      console.log(this.closeSos = data);
    });
  }
  /**
   * This function is used to close a medical sos request
   */
  close(message: string): void{
    this.closeSos.formStatus = message;
    this.closeSosService.closeSOS(this.closeSos).subscribe(data1 => {
      console.log(this.closeSos = data1);
      this.closeSosService.getSOSMed().subscribe(data2 => {
        console.log(this.closeSos = data2);
      });
    }); 
  }
  /**
  * This function is used to pass a medical sos request
  */

  pass(): void{
    this.count += 1;
    this.closeSosService.getSOSMed().subscribe(data => {
      console.log(this.closeSos = data);
    });
  }

  check() {
    this.isActive = !this.isActive;
  }
  /**
   * This function is used to search details of a particular resource
   */
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
}
