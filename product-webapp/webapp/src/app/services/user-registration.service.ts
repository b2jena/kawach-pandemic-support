import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserRegistrationService {

  doctor: Doctor[] = [];
  volunteer: Volunteer[] = [];

  constructor(private httpService: HttpClient) { }

  createDoctor(doctor: Doctor): Observable<Doctor>{
    console.log(doctor);
    return this.httpService.post<Doctor>('http://localhost:8081/api/v1/register/doctor', doctor);
  }

  createVolunteer(volunteer: Volunteer): Observable<Volunteer>{
    console.log(volunteer);
    return this.httpService.post<Volunteer>('http://localhost:8081/api/v1/register/volunteer', volunteer);
  }
}

export class Doctor{

  public emailId: string;
  public fullName: string;
  public medicalRegistrationId: string;
  public phoneNumber: number;
  public password: string;
  public specialization: string;

  constructor(emailId: string, fullName: string, medicalRegistrationId: string, phoneNumber: number, password: string, specialization: string) {
    this.emailId = emailId;
    this.fullName = fullName;
    this.medicalRegistrationId = medicalRegistrationId;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.specialization = specialization;
}
}

export class Volunteer{
  public emailId: string;
  public fullName: string;
  public phoneNumber: number;
  public password: string;

  constructor(emailId: string, fullName: string, phoneNumber: number, password: string){
    this.emailId = emailId;
    this.fullName = fullName;
    this.phoneNumber = phoneNumber;
    this.password = password;
  }

}
