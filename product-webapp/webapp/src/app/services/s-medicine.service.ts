import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SMedicineService{
  Medicine: Medicine[] = [];
  constructor(private httpService: HttpClient) { }
  CreateMedicine(medicine: Medicine): Observable<Medicine> {
    console.log(medicine);
    return this.httpService.post<Medicine>('http://localhost:8080/medicine/create', medicine);
  }
  public deleteMedicine(medicine: Medicine) {
    return this.httpService.delete<Medicine>('http://localhost:8080/medicine//delete/' + medicine.city);
  }
  public updateMedicine(medicine: Medicine) {
    console.log(medicine);
    return this.httpService.put<Medicine>('http://localhost:8080/medicine/update', medicine);
  }
  getMedicines(){
    return this.httpService.get<Medicine[]>('http://localhost:8080/medicine/getAll');
  }
}
export class Medicine{
  public city: string;
  public medName: string;
  public pharmacy: string;
  public address: string;
  public contactPerson: string;
  public mobileNumber: string;
  constructor( city: string, medName: string, pharmacy: string, address: string, contactPerson: string, mobileNumber: string) {
    this.city = city;
    this.medName = medName;
    this.pharmacy = pharmacy;
    this.address = address;
    this.contactPerson = contactPerson;
    this.mobileNumber = mobileNumber;
  }
}
