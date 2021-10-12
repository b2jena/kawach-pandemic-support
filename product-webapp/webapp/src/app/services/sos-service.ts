import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormArray, FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class SosService {

  SosRequest: SOSRequest[] = [];

  constructor(private httpService: HttpClient) { }

  CreateSosRequest(SosRequest: SOSRequest): Observable <SOSRequest> {
    console.log(SosRequest);
    console.log(Requirement);
    return this.httpService.post<SOSRequest>('/api/v1/resource/sos/createSos', SosRequest);
  }

  PostFile(file: File): Observable <File> {
    // console.log(file);
    console.log(file);
    return this.httpService.post<File>('/api/v1/resource/sos/createSos/upload', file);
  }

}

export class Requirement{
  public requirementName: string;
  public requirementQuantity: string;
  public unitOfMeasure: string;

  constructor( requirementName: string, requirementQuantity: string, unitOfMeasure: string){
    this.requirementName = requirementName;
    this.requirementQuantity = requirementQuantity;
    this.unitOfMeasure = unitOfMeasure;
  }
}

export class SOSRequest{

  public patientName: string;
  public gender: string;
  public phoneNo: string;
  public email: string;
  public hospitalised: string;
  public city: string;
  public requirement: any;
  public requestStatus: string;
  public formStatus: string;


  constructor( patientName: string, formStatus: string, email: string, requirement: any, phoneNo: string, city: string, gender: string, hospitalised: string, requestStatus: string) {
    this.patientName = patientName;
    this.gender = gender;
    this.phoneNo = phoneNo;
    this.email = email;
    this.hospitalised = hospitalised;
    this.city = city;
    this.requirement = requirement;
    this.requestStatus = requestStatus;
    this.formStatus = formStatus;
  }
}
