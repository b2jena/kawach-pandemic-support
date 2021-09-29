import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import {UserRegistrationService, Doctor} from 'src/app/services/user-registration.service';

interface Specialization{
  value: string;
}

@Component({
  selector: 'app-doctor-registration',
  templateUrl: './doctor-registration.component.html',
  styleUrls: ['./doctor-registration.component.css']
})
export class DoctorRegistrationComponent implements OnInit {

  user: Doctor = new Doctor('', '', '', 0, '', '');

  specialisation: Specialization[] = [
    {value: 'Allergist'},
    {value: 'Cardiologist'},
    {value: 'Dermatologist'},
    {value: 'Endocrinologist'},
    {value: 'Gastroenterologist'},
    {value: 'Hematologist'},
    {value: 'Infectious Disease Specialist'},
    {value: 'Neurologist'},
    {value: 'Pediatrician'},
    {value: 'Psychiatrist'}
  ];

  message: any;
  hide = true;
  action = 'OK';
  email = new FormControl('', [Validators.required, Validators.email]);
  name = new FormControl('', [Validators.required]);
  ph = new FormControl('', [Validators.required]);
  pass = new FormControl('', [Validators.required]);
  medId = new FormControl('', [Validators.required]);

  constructor(private userService: UserRegistrationService, private formBuilder: FormBuilder, private snackBar: MatSnackBar) {}

  profileForm = this.formBuilder.group({
    emailId: [''],
    fullName: [''],
    medicalRegistrationId: [''],
    phoneNumber: [''],
    specialization: [''],
    password: ['']
  });

  ngOnInit(): void {
  }
  createDoctor(): void{
    this.userService.createDoctor(this.user).subscribe( (data) => this.snackBar.open((this.message = data).toString(), this.action, {
      duration: 3000
    }));
  }

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
  getErrormedId(){
    return this.medId.hasError('required') ? 'Mandatory field' : '';
  }

}
