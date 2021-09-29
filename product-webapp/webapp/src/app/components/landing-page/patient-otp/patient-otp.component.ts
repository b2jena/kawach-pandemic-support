import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { OtpServiceService, Patient } from 'src/app/services/patient-otp-service.service';
import {Router, Routes} from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-patient-otp',
  templateUrl: './patient-otp.component.html',
  styleUrls: ['./patient-otp.component.css']
})
export class PatientOtpComponent implements OnInit {
  user: Patient = new Patient('');
  message = '';
  constructor(private patientotp: OtpServiceService, private httpClient: HttpClient, private route: Router , private snackbar: MatSnackBar) {
  }
  ngOnInit(): void {
  }
    CreatePatient(): void {
      if (this.user.email === '')
      {
        this.snackbar.open('Please Enter Email');
      }
      else{
      this.patientotp.CreatePatient(this.user).subscribe( data => { this.snackbar.open('Please Check E-mail for OTP.'); }); }
      }
      onClickSubmit(mess: { otp: string; }): void {
        if (mess.otp === '')
        {
          this.snackbar.open('Please Enter OTP');
        }
        else{
          this.patientotp.VerifyOtp(mess.otp).subscribe(response => this.handleResponse(response));
          if (this.message === 'SUCCESS')
          {
              this.route.navigate(['/sos']);
          }
          if (this.message === 'FAIL')
          {
            this.snackbar.open('Please Enter OTP'); }
        }
     }
     handleResponse(response: string): void
     {
       this.message = response;
     }
}
