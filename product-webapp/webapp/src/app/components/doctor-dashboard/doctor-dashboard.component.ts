import { Component, OnInit } from '@angular/core';
import { DoctorCardsService } from 'src/app/services/doctor-cards.service';
import { LoginService } from 'src/app/services/login.service';
import { Doctor } from 'src/app/services/user-registration.service';

@Component({
  selector: 'app-doctor-dashboard',
  templateUrl: './doctor-dashboard.component.html',
  styleUrls: ['./doctor-dashboard.component.css']
})
export class DoctorDashboardComponent implements OnInit {
  email = localStorage.getItem('loggedIn');
  constructor(private doctorService: DoctorCardsService, private loginService: LoginService) { }
  doctor: Doctor;
  ngOnInit(): void {
    this.doctorService.GetDoctorInfo(this.email).subscribe((doctorData) => {
      this.doctor = doctorData; });
  }
  DoctorOnline(emailId: string){
    this.doctorService.DoctorOnline(emailId).subscribe(data => {
      this.loginService.showsnackbar('You are visible to patient now!!');
    });
  }
  DoctorOffline(emailId: string){
    this.doctorService.deleteDoctor(emailId).subscribe(data => {
      this.loginService.showsnackbar('Thank you doctor for your time!!');
    });
  }

}
