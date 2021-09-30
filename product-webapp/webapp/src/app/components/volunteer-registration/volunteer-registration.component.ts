import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import {UserRegistrationService, Volunteer } from 'src/app/services/user-registration.service';

@Component({
  selector: 'app-volunteer-registration',
  templateUrl: './volunteer-registration.component.html',
  styleUrls: ['./volunteer-registration.component.css']
})
export class VolunteerRegistrationComponent implements OnInit {

  user: Volunteer = new Volunteer('', '', 0, '');
  message: any;
  hide = true;
  action = 'OK';
  email = new FormControl('', [Validators.required, Validators.email]);
  name = new FormControl('', [Validators.required]);
  ph = new FormControl('', [Validators.required]);
  pass = new FormControl('', [Validators.required]);

  constructor(private userService: UserRegistrationService, private snackBar: MatSnackBar, private formBuilder: FormBuilder) {
  }

  profileForm = this.formBuilder.group({
    emailId: [''],
    fullName: [''],
    phoneNumber: [''],
    password: ['']
  });

  ngOnInit(): void {
  }

  createVolunteer(): void{
    this.userService.createVolunteer(this.user).subscribe( (data) => this.snackBar.open((this.message = data).toString(), this.action, {
      duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'top'
    }));
    // this._snackBar.open(this.message.toString());
  }

  // openSnackBar(message: string) {
  //   this._snackBar.open(this.message);
  // }

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'Mandatory field';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }
  getErrorName(){
    return this.name.hasError('required') ? 'Mandatory field' : '';
  }
  getErrorPh(){
    return this.ph.hasError('required') ? 'Mandatory field' : '';
  }
  getErrorPass(){
    return this.pass.hasError('required') ? 'Mandatory field' : '';
  }



}
