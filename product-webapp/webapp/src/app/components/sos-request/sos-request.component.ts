// import { delay } from 'rxjs/operators';
// import { Component, OnInit } from '@angular/core';
// import { Requirement, SOSRequest, SosService } from 'src/app/services/sos-service';
// import { MatSnackBar } from '@angular/material/snack-bar';
// import { Router } from '@angular/router';
// import { FormArray, FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
// import { ErrorStateMatcher } from '@angular/material/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';



// @Component({
//   selector: 'app-sos-request',
//   templateUrl: './sos-request.component.html',
//   styleUrls: ['./sos-request.component.css']
// })

// export class SosRequestComponent implements OnInit {

//   emailFormControl = new FormControl('', [
//     Validators.required,
//     Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
//   ]);

//   nameFormControl = new FormControl('', [
//     Validators.required,
//     Validators.pattern('^[A-za-z]{3,20}')
//   ]);

//   cityFormControl = new FormControl('', [
//     Validators.required,
//     Validators.pattern('^[A-za-z]{4,20}'),
//   ]);

//   genderFormControl = new FormControl('', [
//     Validators.required,
//   ]);

//   hospitalFormControl = new FormControl('', [
//     Validators.required,
//   ]);

//   requestStatusFormControl = new FormControl('', [
//     Validators.required,
//   ]);

//   telFormControl = new FormControl('', [
//     Validators.required,
//     Validators.pattern('^[0-9]{10}'),
//   ]);

//   genders: Gender[] = [
//     {value: '1', viewValue: 'Male'},
//     {value: '2', viewValue: 'Female'},
//     {value: '3', viewValue: 'Others'}
//   ];

//   hospitaliseds: Hospitalised[] = [
//     {value: '1', viewValue: 'Hospitalised'},
//     {value: '2', viewValue: 'Not Hospitalised'},
//   ];

//   requestStatuses: RequestStatus[] = [
//     {value: '1', viewValue: 'Open'},
//     {value: '2', viewValue: 'Close'},
//   ];


//   patientForm = this.fb.group({
//     requirement: this.fb.array([this.createContact()])
//   })

//   public RequestList!: FormArray;
//   user1: Requirement = new Requirement('', '', '');
//   list: Array<Requirement> = [];
//   user: SOSRequest = new SOSRequest('', '', this.list, '', '', '', '', '');

//   constructor(private sosService: SosService, private fb: FormBuilder, private snackBar: MatSnackBar, private route: Router) { }


//   get requirmentControl(){
//     return (<FormArray>this.patientForm.get('requirments')).controls
//   }


//   isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
//     const isSubmitted = form && form.submitted;
//     return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
//   }

//   ngOnInit(): void {
//     this.RequestList = this.patientForm.get('requirement') as FormArray;
//   }

//   get contactFormGroup() {
//     return this.patientForm.get('requirement') as FormArray;
//   }

//   createContact(): FormGroup {
//     return this.fb.group({
//       requirementName: [null, Validators.compose([Validators.required])],
//       requirementQuantity: [null, Validators.compose([Validators.required])],
//       unitOfMeasure: [null, Validators.required]
//     });
//   }

//   AddRequirment(){
//     this.RequestList.push(this.createContact());
//   }

//   removeRequest() {

//     this.RequestList.removeAt(this.RequestList.length-1);
//   }

//   getContactsFormGroup(index: any): FormGroup {
//     const formGroup = this.RequestList.controls[index] as FormGroup;
//     return formGroup;
//   }

//   showSnackbar(content: string, action: string) {
//     const snack = this.snackBar.open(content, action, {
//       duration: 3000,
//       verticalPosition: 'top', // Allowed values are  'top' | 'bottom'
//       horizontalPosition: 'center', // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
//     });
//     snack.afterDismissed().subscribe(() => {
//       // window.location.reload();
//       console.log('This will be shown after snackbar disappeared');
//     });
//     snack.onAction().subscribe(() => {
//       // window.location.reload();
//       console.log('This will be called when snackbar button clicked');
//     });
//   }

//   showSnackbars(content: string, action: string) {
//     const snack = this.snackBar.open(content, action, {
//       duration: 2000,
//       verticalPosition: 'top', // Allowed values are  'top' | 'bottom'
//       horizontalPosition: 'center', // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
//     });
//     snack.afterDismissed().subscribe(() => {
//       console.log('This will be shown after snackbar disappeared');
//     });
//     snack.onAction().subscribe(() => {
//       window.location.reload();
//       console.log('This will be called when snackbar button clicked');
//     });
//   }

//   Create(): void {
//     if (this.user.patientName === '' || this.user.city === '' || this.user.email === '' || this.user.gender === '' || this.user.hospitalised === '' || this.user.phoneNo === '' || this.user.requestStatus === '') {

//       this.showSnackbars('Please enter the required details.', 'x');
//     }
//     else if (this.nameFormControl.invalid || this.cityFormControl.invalid || this.emailFormControl.invalid || this.genderFormControl.invalid || this.hospitalFormControl.invalid || this.telFormControl.invalid || this.requestStatusFormControl.invalid) {
//       this.showSnackbars('Your Form Value is invalid please provide valid information', 'x');
//     }
//     else{
//       (<FormArray>this.patientForm.get('requirement')).value.forEach((i: any) => {
//         this.list.push(i);
//         console.log(JSON.stringify(i));
//       });
//       console.log("here ^");
//       console.log(this.list);
//       this.sosService.CreateSosRequest(this.user).subscribe( data =>
//         {this.showSnackbar('SOS request added successfully.', 'x'); });
//         console.log((<FormArray>this.patientForm.get('requirement')).value[0]);
//         console.log(JSON.stringify((<FormArray>this.patientForm.get('requirement')).value[0]));
//     }
//   }
// }

// interface Gender {
//   value: string;
//   viewValue: string;
// }
// interface Hospitalised {
//   value: string;
//   viewValue: string;
// }

// interface RequestStatus {
//   value:string;
//   viewValue:String;

// }


import { Component, OnInit } from '@angular/core';
import {
  Requirement,
  SOSRequest,
  SosService,
} from 'src/app/services/sos-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  FormGroupDirective,
  NgForm,
  Validators,
} from '@angular/forms';


@Component({
  selector: 'app-sos-request',
  templateUrl: './sos-request.component.html',
  styleUrls: ['./sos-request.component.css'],
})
export class SosRequestComponent implements OnInit {
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
  ]);

  nameFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[A-za-z]{3,20}'),
  ]);

  cityFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[A-za-z]{4,20}'),
  ]);

  genderFormControl = new FormControl('', [Validators.required]);

  hospitalFormControl = new FormControl('', [Validators.required]);

  requestStatusFormControl = new FormControl('', [Validators.required]);

  telFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[0-9]{10}'),
  ]);

  genders: Gender[] = [
    { value: '1', viewValue: 'Male' },
    { value: '2', viewValue: 'Female' },
    { value: '3', viewValue: 'Others' },
  ];

  hospitaliseds: Hospitalised[] = [
    { value: '1', viewValue: 'Hospitalised' },
    { value: '2', viewValue: 'Not Hospitalised' },
  ];

  requestStatuses: RequestStatus[] = [
    { value: '1', viewValue: 'Open'},
    { value: '2', viewValue: 'Close' },
  ];

  patientForm = this.fb.group({
    requirement: this.fb.array([this.createContact()]),
  });

  public RequestList!: FormArray;
  user1: Requirement = new Requirement('', '', '');
  list: Array<Requirement> = [];
  user: SOSRequest = new SOSRequest('', '', this.list, '', '', '', '', '');

  constructor(
    private sosService: SosService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private route: Router
  ) {}

  get requirmentControl() {
    return (this.patientForm.get('requirments') as FormArray).controls;
  }

  isErrorState(
    control: FormControl | null,
    form: FormGroupDirective | NgForm | null
  ): boolean {
    const isSubmitted = form && form.submitted;
    return !!(
      control &&
      control.invalid &&
      (control.dirty || control.touched || isSubmitted)
    );
  }

  ngOnInit(): void {
    this.RequestList = this.patientForm.get('requirement') as FormArray;
  }

  get contactFormGroup() {
    return this.patientForm.get('requirement') as FormArray;
  }

  createContact(): FormGroup {
    return this.fb.group({
      requirementName: [null, Validators.compose([Validators.required])],
      requirementQuantity: [null, Validators.compose([Validators.required])],
      unitOfMeasure: [null, Validators.required],
    });
  }

  AddRequirment() {
    this.RequestList.push(this.createContact());
  }

  removeRequest() {
    this.RequestList.removeAt(this.RequestList.length - 1);
  }

  getContactsFormGroup(index: any): FormGroup {
    const formGroup = this.RequestList.controls[index] as FormGroup;
    return formGroup;
  }

  showSnackbar(content: string, action: string) {
    const snack = this.snackBar.open(content, action, {
      duration: 3000,
      verticalPosition: 'top', // Allowed values are  'top' | 'bottom'
      horizontalPosition: 'center', // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
    });
    snack.afterDismissed().subscribe(() => {
      // window.location.reload();
      console.log('This will be shown after snackbar disappeared');
    });
    snack.onAction().subscribe(() => {
      // window.location.reload();
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
    if (
      this.user.patientName === '' ||
      this.user.city === '' ||
      this.user.email === '' ||
      this.user.gender === '' ||
      this.user.hospitalised === '' ||
      this.user.phoneNo === '' ||
      this.user.requestStatus === ''
    ) {
      this.showSnackbars('Please enter the required details.', 'x');
    } else if (
      this.nameFormControl.invalid ||
      this.cityFormControl.invalid ||
      this.emailFormControl.invalid ||
      this.genderFormControl.invalid ||
      this.hospitalFormControl.invalid ||
      this.telFormControl.invalid ||
      this.requestStatusFormControl.invalid
    ) {
      this.showSnackbars(
        'Your Form Value is invalid please provide valid information',
        'x'
      );
    } else {
      (this.patientForm.get('requirement') as FormArray).value.forEach(
        (i: any) => {
          this.list.push(i);
          console.log(JSON.stringify(i));
        }
      );
      console.log(this.list);
      this.sosService.CreateSosRequest(this.user).subscribe((data) => {
        this.showSnackbar('SOS request added successfully.', 'x');
      });
      console.log((this.patientForm.get('requirement') as FormArray).value[0]);
      console.log(
        JSON.stringify(
          (this.patientForm.get('requirement') as FormArray).value[0]
        )
      );
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
