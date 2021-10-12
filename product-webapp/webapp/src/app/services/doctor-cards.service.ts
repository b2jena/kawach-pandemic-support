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
    return this.httpService.get('http://localhost:8989/api/v1/doctor/' + emailId);
  }
  GetOnlineDoctors(){
    return this.httpService.get<DoctorRedis[]>('http://localhost:8989/api/v1/doctor');
  }
  public deleteEmployee(doctor: Doctor) {
    return this.httpService.delete<DoctorRedis>('http://localhost:8989/api/v1//doctor/' + doctor.emailId);
  }
  public deleteDoctor(emailId: string) {
    return this.httpService.delete<DoctorRedis>('http://localhost:8989/api/v1//doctor/' + emailId);
  }
  public GetDoctorInfo(emailId: string) {
    return this.httpService.get<Doctor>('http://localhost:8090/api/v1/doctor/' + emailId);
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
