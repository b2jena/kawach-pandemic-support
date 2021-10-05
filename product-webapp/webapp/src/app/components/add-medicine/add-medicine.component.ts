import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Router } from '@angular/router';
import { Medicine, SMedicineService } from 'src/app/services/s-medicine.service';

@Component({
  selector: 'app-add-medicine',
  templateUrl: './add-medicine.component.html',
  styleUrls: ['./add-medicine.component.css']
})
export class AddMedicineComponent implements OnInit {
  mobFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[0-9]{10}'),
  ]);
  isActive = false;
  user: Medicine = new Medicine( '', '', '', '', '', '', this.isActive);

  constructor(private medicineService: SMedicineService, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }
  showSnackbars(content: string, action: string) {
    const snack = this.snackBar.open(content, action, {
      duration: 2000,
      verticalPosition: 'top', // Allowed values are  'top' | 'bottom'
      horizontalPosition: 'center', // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
    });
    snack.afterDismissed().subscribe(() => {
      console.log('This will be shown after snackbar disappeared');
    });
    snack.onAction().subscribe(() => {
      // window.location.reload();
      console.log('This will be called when snackbar button clicked');
    });
  }
    CreateMedicine(): void {
      if ( this.user.medicineName === '' || this.user.address === '' || this.user.city === '' || this.user.contactPerson === '' || this.user.mobileNumber === '' || this.user.pharmacy === ''){
        this.showSnackbars('Please fill the empty field(s).', 'x');
      } else {
        this.user.verificationStatus = this.isActive;
        this.medicineService.CreateMedicine(this.user).subscribe( data => { this.showSnackbars('Medicine added successfully.', 'x'); });
      }
    }
    check() {
      this.isActive = !this.isActive;
    }
}
