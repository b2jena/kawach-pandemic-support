import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
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

  constructor(private medicineService: SMedicineService, private snackBar: MatSnackBar, private formBuilder: FormBuilder) { }

  ngOnInit() {
  }

  medForm = this.formBuilder.group({
    medicineName: new FormControl('', [Validators.required, Validators.minLength(2)]),
    city: new FormControl('', Validators.required),
    pharmacy: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    contactPerson: new FormControl('', [Validators.required, Validators.minLength(3)]),
    mobileNumber: new FormControl('', [Validators.required, Validators.pattern('[0-9]{10}')]),
    verificationStatus: new FormControl(''),
  });
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

  /* This button functionality is to make the 
   * post request and to store the medicine in mongoDB database
   */
    CreateMedicine(): void {
      this.medForm.get('verificationStatus').setValue(this.isActive);
      console.log("data:", this.medForm.value);
      this.medicineService.CreateMedicine(this.medForm.value).subscribe( data => {
         this.showSnackbars('Medicine added successfully.', 'x');
         this.medForm.reset();
        });
    }
    check() {
      this.isActive = !this.isActive;
    }
}
