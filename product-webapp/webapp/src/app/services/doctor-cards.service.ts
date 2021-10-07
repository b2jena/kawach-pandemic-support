import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class DoctorCardsService {
  doctor: Doctor[] = [];

  constructor(private httpService: HttpClient) { }
  DoctorOnline(emailId: string) {
    console.log(emailId);
    return this.httpService.post('http://localhost:8989/api/v1/doctor' + emailId, '');
  }
  GetOnlineDoctors(){
    return this.httpService.get<Doctor[]>('http://localhost:8989/api/v1/doctor');
  }
  public deleteEmployee(doctor: Doctor) {
    return this.httpService.delete<Doctor>('http://localhost:8989/api/v1//doctor/' + doctor.emailId);
  }
}
export class Doctor{
  public emailId: string ;
  public fullName: string ;
  public specialization: string ;
  constructor(emailId: string , fullName: string , specialization: string ) {
    this.emailId = emailId;
    this.fullName = fullName;
    this.specialization = specialization;
   }
}
