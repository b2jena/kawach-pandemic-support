import { Component, OnInit } from '@angular/core';
import { Doctor, DoctorCardsService } from 'src/app/services/doctor-cards.service';

@Component({
  selector: 'app-doctor-cards',
  templateUrl: './doctor-cards.component.html',
  styleUrls: ['./doctor-cards.component.css']
})
export class DoctorCardsComponent implements OnInit {
  doctors!: Doctor[];
  constructor(private doctorService: DoctorCardsService) { }

  ngOnInit(): void {
    this.doctorService.GetOnlineDoctors().subscribe(data => this.handleSuccessfulResponse(data));
  }
  handleSuccessfulResponse(response: Doctor[] )
{
    this.doctors = response;
}
deleteEmployee(doctor: Doctor): void {
  this.doctorService.deleteEmployee(doctor)
    .subscribe( data => {
      this.doctors = this.doctors.filter(u => u !== doctor); }); }
}
