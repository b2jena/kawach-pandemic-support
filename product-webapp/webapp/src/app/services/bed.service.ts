import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BedService{
  // apiBaseUrl=environment.apiBaseUrl;
  Bed!: Bed;
  count!: number;
  constructor(private httpService: HttpClient) { }
  CreateBed(bed: Bed): Observable<Bed> {
    console.log(bed);
    const addedBy= localStorage.getItem('loggedIn');
    console.log("addedd by", addedBy);
    return this.httpService.post<Bed>(`/api/v1/resource/bed/create/${addedBy}`, bed);
  }
  public deleteBed(bed: Bed) {
    return this.httpService.delete<Bed>('/api/v1/resource/bed//delete/' + bed.city);
  }
  public updateBed(bed: Bed) {
    console.log(bed);
    return this.httpService.put<Bed>('/api/v1/resource/bed/update', bed);
  }
  public getBeds(){
    return this.httpService.get<Bed[]>('/api/v1/resource/bed/getAll');
  }

  public getUnverifiedBed()
  {
    return this.httpService.get<Bed>('/api/v1/resource/bed/getUnverified');
  }

}
export class Bed{
  public city: string;
  public bedType: string;
  public address: string;
  public contactPerson: string;
  public mobileNumber: string;
  public verificationStatus: boolean;
  constructor( city: string, bedType: string, address: string, contactPerson: string, mobileNumber: string, verificationStatus: boolean) {
    this.city = city;
    this.bedType = bedType;
    this.address = address;
    this.contactPerson = contactPerson;
    this.mobileNumber = mobileNumber;
    this.verificationStatus = verificationStatus;
  }
}
