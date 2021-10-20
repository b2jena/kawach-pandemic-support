import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class UserRegistrationService {

  volunteer: Volunteer[] = [];

  constructor(private httpService: HttpClient) { }

  /* This method makes a post request to the backend to save the volunteer registration details */
  createVolunteer(volunteer: Volunteer): Observable<Volunteer>{
    console.log(volunteer);
    return this.httpService.post<Volunteer>('/api/v1/user/register/volunteer', volunteer);
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
