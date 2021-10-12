import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Doctor } from './user-registration.service';


@Injectable({
  providedIn: 'root'
})
export class DoctorCardsService {
  doctor: DoctorRedis[] = [];

  constructor(private httpService: HttpClient) { }
  DoctorOnline(emailId: string) {
    console.log(emailId);
    return this.httpService.get('/api/v1/doctor/doctor/' + emailId);
  }
  GetOnlineDoctors(){
    return this.httpService.get<DoctorRedis[]>('/api/v1/doctor/doctor');
  }
  public deleteEmployee(doctor: Doctor) {
    return this.httpService.delete<DoctorRedis>('/api/v1/doctor/doctor/' + doctor.emailId);
  }
  public deleteDoctor(emailId: string) {
    return this.httpService.delete<DoctorRedis>('/api/v1/doctor/doctor/' + emailId);
  }
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
