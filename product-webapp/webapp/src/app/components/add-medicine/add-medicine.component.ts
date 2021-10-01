import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Router } from '@angular/router';
import { Medicine, SMedicineService } from 'src/app/services/s-medicine.service';

@Component({
  selector: 'app-add-medicine',
  templateUrl: './add-medicine.component.html',
  styleUrls: ['./add-medicine.component.css']
})
export class AddMedicineComponent implements OnInit {

  user: Medicine = new Medicine( '', '', '', '', '', '' );

  constructor(private medicineService: SMedicineService, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }
    CreateMedicine(): void {
      if ( this.user.medicineName === '' || this.user.address === '' || this.user.city === '' || this.user.contactPerson === '' || this.user.mobileNumber === '' || this.user.pharmacy === ''){
        this.snackBar.open('Please fill the empty field(s).');
      } else {
        this.medicineService.CreateMedicine(this.user).subscribe( data => { this.snackBar.open('Medicine added successfully.'); });
      }
    }
}
