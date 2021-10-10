import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
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

  constructor(private equipmentService: BedService, private snackBar: MatSnackBar) { }

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
    CreateBed(): void {
      if ( this.user.bedType === '' || this.user.address === '' || this.user.city === '' || this.user.contactPerson === '' || this.user.mobileNumber === ''){
        this.showSnackbars('Please fill the empty field(s).', 'x');
      } else {
        this.user.verificationStatus = this.isActive;
        console.log('data:', this.user);
        this.equipmentService.CreateBed(this.user).subscribe( data => { this.showSnackbars('Bed added successfully.', 'x'); });
        window.setTimeout(function(){location.reload()}, 2000);
      }
      console.log(this.user.verificationStatus);
    }

    check() {
      this.isActive = !this.isActive;
    }
}

