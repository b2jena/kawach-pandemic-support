import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Bed, BedService } from 'src/app/services/bed.service';

@Component({
  selector: 'app-add-bed',
  templateUrl: './add-bed.component.html',
  styleUrls: ['./add-bed.component.css']
})
export class AddBedComponent implements OnInit {

  user: Bed = new Bed( '', '', '', '', '');

  constructor(private equipmentService: BedService, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }
    CreateBed(): void {
      if ( this.user.bedType === '' || this.user.address === '' || this.user.city === '' || this.user.contactPerson === '' || this.user.mobileNumber === ''){
        this.snackBar.open('Please fill the empty field(s).');
      } else {
        this.equipmentService.CreateBed(this.user).subscribe( data => { this.snackBar.open('Bed added successfully.'); });
      }
    }
}
