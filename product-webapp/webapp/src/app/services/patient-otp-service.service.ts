import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OtpServiceService {
  patient: Patient[] = [];
  constructor(private httpService: HttpClient) { }
  //to register the patient via EMial
  CreatePatient(patient: Patient) {
    console.log(patient);
    return this.httpService.post('/api/v1/otp/generateOtp', patient);
  }
  //to verify the otp that patient inputs
  VerifyOtp(otp: string): Observable<string>{
    console.log(otp);
    return this.httpService.get('/api/v1/otp/validateOtp/' + otp, {responseType: 'text'});
  }
}

export class Patient{
  public email: string;

  constructor(email: string)
  {
    this.email = email;
  }
}
