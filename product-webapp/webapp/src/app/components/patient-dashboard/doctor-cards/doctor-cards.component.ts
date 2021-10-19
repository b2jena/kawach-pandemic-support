import { Component, OnInit } from '@angular/core';
import { DoctorCardsService, DoctorRedis } from 'src/app/services/doctor-cards.service';
import { Doctor } from 'src/app/services/user-registration.service';

@Component({
  selector: 'app-doctor-cards',
  templateUrl: './doctor-cards.component.html',
  styleUrls: ['./doctor-cards.component.css']
})
export class DoctorCardsComponent implements OnInit {
  doctors!: DoctorRedis[];
  doctorDetails: Array<any> = [];
  constructor(private doctorService: DoctorCardsService) { }

  ngOnInit(): void {
    this.doctorService.GetOnlineDoctors().subscribe(data => this.handleSuccessfulResponse(data));
  }
  //Get all the online doctors and to get all the information about them.
  handleSuccessfulResponse(response: DoctorRedis[] )
{
    this.doctors = response;
    console.log('doctors online from redis:', this.doctors);
    this.doctors.forEach((value) => {
      this.doctorService.GetDoctorInfo(value.emailId).subscribe((doctorData) => {
        console.log("doctor data:", doctorData);
        this.doctorDetails.push(doctorData);
      });
    });
}
//To delete a particular doctors from databse.
deleteDoctor(doctor: Doctor): void {
  this.doctorService.deleteDoc(doctor)
    .subscribe( data => {
      this.doctors = this.doctors.filter(u => u !== doctor); }); }
}
