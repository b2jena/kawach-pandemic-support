import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Bed, BedService } from 'src/app/services/bed.service';

@Component({
  selector: 'app-list-bed',
  templateUrl: './list-bed.component.html',
  styleUrls: ['./list-bed.component.css']
})
export class ListBedComponent implements OnInit {

  beds!: Bed[];
    constructor(private bedService: BedService, private router: Router) { }
    ngOnInit() {
      this.bedService.getBeds().subscribe(
        response => this.handleSuccessfulResponse(response),
       );
    }
    handleSuccessfulResponse(response: Bed[]){
      this.beds = response;
    }
    deleteBed(bed: Bed): void {
      this.bedService.deleteBed(bed)
        .subscribe( data => {
          this.beds = this.beds.filter(u => u !== bed); } );
    }
}
