import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SMedicineService {
  Medicine: Medicine[] = [];
  constructor(private httpService: HttpClient) { }

  CreateMedicine(medicine: Medicine): Observable<Medicine> {
    console.log(medicine);
    return this.httpService.post<Medicine>('http://localhost:9999/medicine/create', medicine);
  }
  public deleteMedicine(medicine: Medicine) {
    return this.httpService.delete<Medicine>('http://localhost:9999/medicine//delete/' + medicine.city);
  }
  public updateMedicine(medicine: Medicine) {
    console.log(medicine);
    return this.httpService.put<Medicine>('http://localhost:9999/medicine/update', medicine);
  }
  getMedicines(){
    return this.httpService.get<Medicine[]>('http://localhost:9999/medicine/getAll');
  }
}

export class Medicine{

  public city: string;
  public medName: string;
  public pharmacy: string;

  constructor(city: string, medName: string, pharmacy: string) {
    this.city = city;
    this.medName = medName;
    this.pharmacy = pharmacy;

  }
}
