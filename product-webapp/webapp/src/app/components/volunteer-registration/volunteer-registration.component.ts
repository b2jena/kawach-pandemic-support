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
  action = '';


  constructor(private userService: UserRegistrationService, private snackBar: MatSnackBar, private formBuilder: FormBuilder) {
  }

  profileForm = this.formBuilder.group({
    emailId: new FormControl('', [Validators.required, Validators.email]),
    fullName: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [Validators.minLength(10)]),
    password: new FormControl('', [Validators.required])
  });

  ngOnInit(): void {
  }

  createVolunteer(): void{
    this.userService.createVolunteer(this.user).subscribe(
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
