import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CloseSosService {

  closeSos: CloseSos[] = [];

  constructor(private httpService: HttpClient) { }

  getSos(): Observable<CloseSos[]>{
    return this.httpService.get<CloseSos[]>('http://localhost:9901/sos/getMedSOS');
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

export class CloseSos{

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
