import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Doctor } from './user-registration.service';


@Injectable({
  providedIn: 'root'
})
export class DoctorCardsService {
  doctor: DoctorRedis[] = [];

  constructor(private httpService: HttpClient) { }
  //To make the doctor 
  DoctorOnline(emailId: string) {
    console.log(emailId);
    return this.httpService.get('/api/v1/doctor/doctor/' + emailId);
  }
  //to get all the available doctors
  GetOnlineDoctors(){
    return this.httpService.get<DoctorRedis[]>('/api/v1/doctor/doctor');
  }
  //to make a doctor offline from doctor dashboard
  public deleteDoc(doctor: Doctor) {
    return this.httpService.delete<DoctorRedis>('/api/v1/doctor/' + doctor.emailId);
  }
  //to make a doctor offline from patient dashboard
  public deleteDoctor(emailId: string) {
    return this.httpService.delete<DoctorRedis>('/api/v1/doctor/doctor/' + emailId);
  }
  //To get all the details of the doctors.
  public GetDoctorInfo(emailId: string) {
    return this.httpService.get<Doctor>('/api/v1/user/doctor/' + emailId);
  }
}
export class DoctorRedis{
  public emailId: string ;
  public fullName: string ;
  public specialization: string ;
  constructor(emailId: string , fullName: string , specialization: string ) {
    this.emailId = emailId;
    this.fullName = fullName;
    this.specialization = specialization;
   }
}
