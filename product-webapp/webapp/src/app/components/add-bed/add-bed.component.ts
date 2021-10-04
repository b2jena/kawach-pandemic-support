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
  vStatus = [
    { id: 1, select: false, name: 'Click here only if verified'}
  ]
  user: Bed = new Bed( '', '', '', '', '', this.vStatus[0].select);

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
  onChange($event: any){
    const id = $event.target.value;
    const isChecked = $event.target.checked;
    console.log(id, isChecked);
    console.log(this.vStatus);
    this.vStatus = this.vStatus.map((d) => {
      if (d.id == id){
        d.select = isChecked;
        return d;
      }return d;
    });
    console.log(this.vStatus);
  }

    CreateBed(): void {
      if ( this.user.bedType === '' || this.user.address === '' || this.user.city === '' || this.user.contactPerson === '' || this.user.mobileNumber === ''){
        this.user.verificationStatus = this.vStatus[0].select;
        console.log(this.user.verificationStatus);
        console.log("Here")
        console.log(this.vStatus[0].select);
        this.showSnackbars('Please fill the empty field(s).', 'x');
      } else {
        this.user.verificationStatus = this.vStatus[0].select;
        // console.log(this.user.verificationStatus);
        console.log("Here")
        console.log(this.vStatus[0].select);
        this.equipmentService.CreateBed(this.user).subscribe( data => { this.showSnackbars('Bed added successfully.', 'x'); });
      }
      // this.user.verificationStatus = this.vStatus[0].select;
      console.log("Here")
      console.log(this.vStatus[0].select);
      console.log(this.user.verificationStatus);
    }
}
