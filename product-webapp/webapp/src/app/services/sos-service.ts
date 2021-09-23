import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SosService {

  SosRequest: SOSRequest[] = [];

  constructor(private httpService: HttpClient) { }

  CreateSosRequest(SosRequest: SOSRequest): Observable <SOSRequest> {
    console.log(SOSRequest);
    return this.httpService.post<SOSRequest>('http://localhost:9999/medicine/create', SOSRequest);
  }
}

export class SOSRequest{

  public name: string;
  public phoneNo: string;
  public email: string;
  public city: string;
  public gender: string;
  public hospitalStatus: string;

  constructor( name: string, email: string, phoneNo: string, city: string, gender: string, hospitalStatus: string) {
    this.name = name;
    this.phoneNo = phoneNo;
    this.email = email;
    this.city = city;
    this.gender = gender;
    this.hospitalStatus = hospitalStatus;

  }
}
