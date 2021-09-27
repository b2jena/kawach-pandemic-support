import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BedService{
  // apiBaseUrl=environment.apiBaseUrl;
  Bed: Bed[] = [];
  constructor(private httpService: HttpClient) { }
  CreateBed(bed: Bed): Observable<Bed> {
    console.log(bed);
    return this.httpService.post<Bed>('http://localhost:9900/bed/create', bed);
  }
  public deleteBed(bed: Bed) {
    return this.httpService.delete<Bed>('http://localhost:9900/bed//delete/' + bed.city);
  }
  public updateBed(bed: Bed) {
    console.log(bed);
    return this.httpService.put<Bed>('http://localhost:9900/bed/update', bed);
  }
  getBeds(){
    return this.httpService.get<Bed[]>('http://localhost:9900/bed/getAll');
  }
}
export class Bed{
  public city: string;
  public bedType: string;
  public address: string;
  public contactPerson: string;
  public mobileNumber: string;
  constructor( city: string, bedType: string, address: string, contactPerson: string, mobileNumber: string) {
    this.city = city;
    this.bedType = bedType;
    this.address = address;
    this.contactPerson = contactPerson;
    this.mobileNumber = mobileNumber;
  }
}
