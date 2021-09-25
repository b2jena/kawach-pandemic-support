import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PatientOtpServiceService {
  // patient: PATIENT[] = [];
  // constructor(private httpService: HttpClient) { }

  // CreatePatient(patient: PATIENT):Observable<PATIENT> {
  //   console.log(patient);
  //     return this.httpService.post<PATIENT>("localhost:8080/api/v1/patient",patient);
  // }

  // VerifyOtp(otp: number):Observable<PATIENT> {
  //   console.log(otp);
  //     return this.httpService.post<PATIENT>("localhost:8080/api/v1/validateOtp/param?otpnum=",otp);
  // }
}

// export class PATIENT{
//   public EMAIL:string;

//   constructor(EMAIL:string)
//   {
//     this.EMAIL=EMAIL;
//   }
// }
