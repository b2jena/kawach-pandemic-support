import { Component, OnInit } from '@angular/core';
import { DoctorCardsService } from 'src/app/services/doctor-cards.service';

@Component({
  selector: 'app-doctor-dashboard',
  templateUrl: './doctor-dashboard.component.html',
  styleUrls: ['./doctor-dashboard.component.css']
})
export class DoctorDashboardComponent implements OnInit {
  email = 'uio@asd.com';
  constructor(private doctorService: DoctorCardsService) { }

  ngOnInit(): void {
  }
  DoctorOnline(emailId: string){
    this.doctorService.DoctorOnline(emailId).subscribe(data => {});
  }

}
