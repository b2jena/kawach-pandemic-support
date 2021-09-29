import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OtpServiceService {
  patient: Patient[] = [];
  constructor(private httpService: HttpClient) { }

  CreatePatient(patient: Patient): Observable<Patient> {
    console.log(patient);
    return this.httpService.post<Patient>('http://localhost:9999/api/v1/generateOTP', patient);
  }

  VerifyOtp(otp: string): Observable<string>{
    console.log(otp);
    return this.httpService.get('http://localhost:9999/api/v1/validateOtp/' + otp, {responseType: 'text'});
  }
}

export class Patient{
  public email: string;

  constructor(email: string)
  {
    this.email = email;
  }
}
