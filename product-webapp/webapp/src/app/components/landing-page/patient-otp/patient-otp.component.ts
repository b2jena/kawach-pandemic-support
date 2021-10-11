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
  otp = '';
  constructor(private patientotp: OtpServiceService, private httpClient: HttpClient, private route: Router , private snackbar: MatSnackBar) {
  }
  ngOnInit(): void {
  }
  showSnackbar(content: string, action: string) {
    const snack = this.snackbar.open(content, action, {
      duration: 2000,
      verticalPosition: 'top', // Allowed values are  'top' | 'bottom'
      horizontalPosition: 'center', // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
    });
    snack.afterDismissed().subscribe(() => {
      console.log('This will be shown after snackbar disappeared');
    });
    snack.onAction().subscribe(() => {
      console.log('This will be called when snackbar button clicked');
    });
  }
    CreatePatient(): void {
      if (this.user.email === '')
      {
        this.showSnackbar('Please Enter Email', 'x');
      }
      else{
      this.patientotp.CreatePatient(this.user).subscribe( data => { this.showSnackbar('Please Check E-mail for OTP.', 'x'); }); }
      }
      onClickSubmit(): void {
        if (this.otp === '')
        {
          this.showSnackbar('Please Enter OTP', 'x');
        }
        else{

          this.patientotp.VerifyOtp(this.otp).subscribe(response => {this.message = response; if (this.message === 'SUCCESS'){this.route.navigate(['/sos']); }if (this.message === 'FAIL'){this.showSnackbar('Please Enter Correct OTP', 'x'); }});
          localStorage.setItem('paitentEmail', this.user.email);
        }
     }
}
