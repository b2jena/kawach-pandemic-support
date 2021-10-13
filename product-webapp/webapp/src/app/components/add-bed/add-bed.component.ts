import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Bed, BedService } from 'src/app/services/bed.service';

@Component({
  selector: 'app-add-bed',
  templateUrl: './add-bed.component.html',
  styleUrls: ['./add-bed.component.css']
})
export class AddBedComponent implements OnInit {
  mobFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[0-9]{10}'),
  ]);
  // strValue: String = "N";
  isActive = false;
  user: Bed = new Bed( '', '', '', '', '', this.isActive);

  constructor(private bedService: BedService, private snackBar: MatSnackBar, private formBuilder: FormBuilder) { }

  ngOnInit() {
  }

  bedForm = this.formBuilder.group({
    bedType: new FormControl('', [Validators.required, Validators.minLength(2)]),
    city: new FormControl('', Validators.required),
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
    CreateBed(): void {
      this.bedForm.get('verificationStatus').setValue(this.isActive);
      console.log("data:", this.bedForm.value);
      this.bedService.CreateBed(this.bedForm.value).subscribe( data => {
         this.showSnackbars('Bed added successfully.', 'x');
         this.bedForm.reset();
        });
    }

    check() {
      this.isActive = !this.isActive;
    }
}

