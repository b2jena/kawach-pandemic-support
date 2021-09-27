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
    console.log(SosRequest);
    return this.httpService.post<SOSRequest>('http://localhost:9900/sos/createSos', SosRequest);
  }
}

export class SOSRequest{

  public patientName: string;
  public gender: string;
  public phoneNo: string;
  public email: string;
  public hospitalised: string;
  public city: string;
  public requirementName: string;
  public requirementQuantity: string;
  public unitOfMeasure: string;
  public requestStatus: string;



  constructor( patientName: string, email: string, phoneNo: string, city: string, gender: string, hospitalised: string, requirementName: string, requirementQuantity: string, unitOfMeasure: string, requestStatus: string) {
    this.patientName = patientName;
    this.gender = gender;
    this.phoneNo = phoneNo;
    this.email = email;
    this.hospitalised = hospitalised;
    this.city = city;
    this.requirementName = requirementName;
    this.requirementQuantity = requirementQuantity;
    this.unitOfMeasure = unitOfMeasure;
    this.requestStatus = requestStatus;
  }
}
