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
    return this.httpService.post<Medicine>('http://localhost:9901/medicine/create', medicine);
  }
  public deleteMedicine(medicine: Medicine) {
    return this.httpService.delete<Medicine>('http://localhost:9901/medicine//delete/' + medicine.city);
  }
  public updateMedicine(medicine: Medicine) {
    console.log(medicine);
    return this.httpService.put<Medicine>('http://localhost:9901/medicine/update', medicine);
  }
  getMedicines(){
    return this.httpService.get<Medicine[]>('http://localhost:9901/medicine/getAll');
  }
}
export class Medicine{
  public city: string;
  public medicineName: string;
  public pharmacy: string;
  public address: string;
  public contactPerson: string;
  public mobileNumber: string;
  public verificationStatus: boolean;
  constructor( city: string, medicineName: string, pharmacy: string, address: string, contactPerson: string, mobileNumber: string, verificationStatus: boolean) {
    this.city = city;
    this.medicineName = medicineName;
    this.pharmacy = pharmacy;
    this.address = address;
    this.contactPerson = contactPerson;
    this.mobileNumber = mobileNumber;
    this.verificationStatus = verificationStatus;
  }
}
