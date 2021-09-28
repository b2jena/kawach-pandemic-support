import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { OtpServiceService, Patient } from 'src/app/services/patient-otp-service.service';
import {Router, Routes} from '@angular/router';
@Component({
  selector: 'app-patient-otp',
  templateUrl: './patient-otp.component.html',
  styleUrls: ['./patient-otp.component.css']
})
export class PatientOtpComponent implements OnInit {
  user: Patient = new Patient('');
  message = '';
  constructor(private patientotp: OtpServiceService, private httpClient: HttpClient, private route: Router) {
  }
  ngOnInit(): void {
  }
    CreatePatient(): void {
      this.patientotp.CreatePatient(this.user).subscribe( data =>
        { alert('Please Check E-mail for OTP.'); });
      }
      onClickSubmit(mess: { otp: string; }): void {
          this.patientotp.VerifyOtp(mess.otp).subscribe(response => this.handleResponse(response));
          if(this.message === 'SUCCESS')
          {
              this.route.navigate(['/sos']);
          }
     }
     handleResponse(response: string): void
     {
       this.message = response;
     }
}
