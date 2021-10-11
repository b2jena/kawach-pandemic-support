import { Component, OnInit } from '@angular/core';
import { DoctorCardsService } from 'src/app/services/doctor-cards.service';

@Component({
  selector: 'app-doctor-dashboard',
  templateUrl: './doctor-dashboard.component.html',
  styleUrls: ['./doctor-dashboard.component.css']
})
export class DoctorDashboardComponent implements OnInit {
  email = localStorage.getItem('loggedIn');
  constructor(private doctorService: DoctorCardsService) { }

  ngOnInit(): void {
  }
  DoctorOnline(emailId: string){
    this.doctorService.DoctorOnline(emailId).subscribe(data => {});
  }
  DoctorOffline(emailId: string){
    this.doctorService.deleteDoctor(emailId).subscribe(data => {});
  }

}
