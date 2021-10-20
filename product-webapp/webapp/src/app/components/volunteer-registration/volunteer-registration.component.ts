import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import {UserRegistrationService, Volunteer } from 'src/app/services/user-registration.service';

interface NumberCode{
  value: string;
}

@Component({
  selector: 'app-volunteer-registration',
  templateUrl: './volunteer-registration.component.html',
  styleUrls: ['./volunteer-registration.component.css']
})
export class VolunteerRegistrationComponent implements OnInit {

  user: Volunteer = new Volunteer('', '', 0, '');
  message: any;
  hide = true;
  action = '';

  code: NumberCode[] = [
    {value: '+91'},
    {value: '+92'},
    {value: '+93'},
    {value: '+94'},
    {value: '+95'},
    {value: '+1'},
    {value: '+31'},
    {value: '+32'},
    {value: '+33'},
  ];


  constructor(private userService: UserRegistrationService, private snackBar: MatSnackBar, private formBuilder: FormBuilder) {
  }

  profileForm = this.formBuilder.group({
    emailId: new FormControl('', [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]),
    fullName: new FormControl('', [Validators.required, Validators.minLength(2)]),
    phoneNumber: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{10}')]),
    password: new FormControl('', [Validators.required, Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,15}$')])
  });

  ngOnInit(): void {
  }

   /* This method is responsible for sending the volunteer registration details to the backend and is triggered on clicking the register button*/
  createVolunteer(): void{
    this.userService.createVolunteer(this.profileForm.value).subscribe(
      (data) => {this.snackBar.open((this.message = data).toString(), this.action, {
        duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'top'
    });
    if (data.toString() === 'You have been successfully registered! Kindly Login now'){
    setTimeout(() => {
      //  window.location.replace('http://localhost:4200/#/login'); 
       window.location.replace('https://kawach.stackroute.io/#/login');
      },
    3000);
  }
  }
    );
}}
