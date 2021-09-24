import { Component, OnInit } from '@angular/core';
import {  PatientOtpServiceService } from 'src/app/services/patient-otp-service.service';

@Component({
  selector: 'app-patient-otp',
  templateUrl: './patient-otp.component.html',
  styleUrls: ['./patient-otp.component.css']
})
export class PatientOtpComponent implements OnInit {

  // user: PATIENT = new PATIENT("");

  constructor(private patientotp: PatientOtpServiceService) { }

  ngOnInit() {
  }

}
