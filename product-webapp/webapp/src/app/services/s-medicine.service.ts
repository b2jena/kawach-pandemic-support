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
    const addedBy= localStorage.getItem('loggedIn');
    return this.httpService.post<Medicine>(`/api/v1/resource/medicine/create/${addedBy}`, medicine);
  }
  public deleteMedicine(medicine: Medicine) {
    return this.httpService.delete<Medicine>('/api/v1/resource/medicine//delete/' + medicine.city);
  }
  public updateMedicine(medicine: Medicine) {
    console.log(medicine);
    return this.httpService.put<Medicine>('/api/v1/resource/medicine/update', medicine);
  }
  getMedicines(){
    return this.httpService.get<Medicine[]>('/api/v1/resource/medicine/getAll');
  }

  public getUnverifiedMed()
  {
    return this.httpService.get<Medicine>('/api/v1/resource/medicine/getUnverified');
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
