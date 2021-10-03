import { delay } from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';
import { Requirement, SOSRequest, SosService } from 'src/app/services/sos-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { FormArray, FormBuilder, FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';



@Component({
  selector: 'app-sos-request',
  templateUrl: './sos-request.component.html',
  styleUrls: ['./sos-request.component.css']
})

export class SosRequestComponent implements OnInit , ErrorStateMatcher{

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
  ]);

  nameFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[A-za-z]{3,20}')
  ]);

  requirementNameFormControl = new FormControl('', [
    Validators.required,
  ]);

  requirementQuantityFormControl = new FormControl('', [
    Validators.required,
  ]);

  unitOfMeasureFormControl = new FormControl('', [
    Validators.required,
  ]);

  cityFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[A-za-z]{4,20}'),
  ]);

  genderFormControl = new FormControl('', [
    Validators.required,
  ]);

  hospitalFormControl = new FormControl('', [
    Validators.required,
  ]);

  requestStatusFormControl = new FormControl('', [
    Validators.required,
  ]);

  telFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[0-9]{10}'),
  ]);

  genders: Gender[] = [
    {value: '1', viewValue: 'Male'},
    {value: '2', viewValue: 'Female'},
    {value: '3', viewValue: 'Others'}
  ];

  hospitaliseds: Hospitalised[] = [
    {value: '1', viewValue: 'Hospitalised'},
    {value: '2', viewValue: 'Not Hospitalised'},
  ];

  requestStatuses: RequestStatus[] = [
    {value: '1', viewValue: 'Open'},
    {value: '2', viewValue: 'Close'},
  ];

  user1: Requirement = new Requirement('', '', '');
  user: SOSRequest = new SOSRequest('', '', [this.user1], '', '', '', '', '');
  message = '';

  constructor(private sosService: SosService, private fb: FormBuilder, private snackBar: MatSnackBar, private route: Router) { }

  // Requirmentform = this.fb.group({
  //   requirment : this.fb.array([])
  // })

  // get requirment(){
  //   return this.Requirmentform.controls["requirment"] as FormArray;
  // }


  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }

  ngOnInit(): void {}
// ******************************************* */
  // addRequirment(){
  //   const requirmentForm = this.fb.group({
  //     requirementNameFormControl : ['', Validators.required],
  //     requirementQuantityFormControl : ['', Validators.required],
  //     unitOfMeasureFormControl : ['', Validators.required]
  //   });

  //   this.requirment.push(requirmentForm);
  // }

  // ******************************************* */

  showSnackbar(content: string, action: string) {
    const snack = this.snackBar.open(content, action, {
      duration: 3000,
      verticalPosition: 'top', // Allowed values are  'top' | 'bottom'
      horizontalPosition: 'center', // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
    });
    snack.afterDismissed().subscribe(() => {
      window.location.reload();
      console.log('This will be shown after snackbar disappeared');
    });
    snack.onAction().subscribe(() => {
      window.location.reload();
      console.log('This will be called when snackbar button clicked');
    });
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
      window.location.reload();
      console.log('This will be called when snackbar button clicked');
    });
  }

  Create(): void {
    if (this.user.patientName === '' || this.user.city === '' || this.user.email === '' || this.user.gender === '' || this.user.hospitalised === '' || this.user.phoneNo === '' || this.user.requestStatus === '' || this.user1.requirementName === '' || this.user1.requirementQuantity === '' || this.user1.unitOfMeasure === '' ) {
      this.showSnackbars('Please enter the required details.', 'x');
    }
    else if (this.nameFormControl.invalid || this.cityFormControl.invalid || this.emailFormControl.invalid || this.genderFormControl.invalid || this.hospitalFormControl.invalid || this.telFormControl.invalid || this.requestStatusFormControl.invalid) {
      this.showSnackbars('Your Form Value is invalid please provide valid information', 'x');
    }
    else{
      this.sosService.CreateSosRequest(this.user).subscribe( data =>
        {this.showSnackbar('SOS request added successfully.', 'x'); });

    }
  }
}

interface Gender {
  value: string;
  viewValue: string;
}

interface Hospitalised {
  value: string;
  viewValue: string;
}

interface RequestStatus {
  value: string;
  viewValue: string;
}
