import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EquipmentService{
  // apiBaseUrl=environment.apiBaseUrl;
  Equipment: Equipment[] = [];
  constructor(private httpService: HttpClient) { }
  CreateEquipment(equipment: Equipment): Observable<Equipment> {
    console.log(equipment);
    return this.httpService.post<Equipment>('http://localhost:9901/equipment/create', equipment);
  }
  public deleteEquipment(equipment: Equipment) {
    return this.httpService.delete<Equipment>('http://localhost:9901/equipment//delete/' + equipment.city);
  }
  public updateEquipment(equipment: Equipment) {
    console.log(equipment);
    return this.httpService.put<Equipment>('http://localhost:9901/equipment/update', equipment);
  }
  getEquipments(){
    return this.httpService.get<Equipment[]>('http://localhost:9901/equipment/getAll');
  }
}
export class Equipment{
  public city: string;
  public equipmentName: string;
  public hospital: string;
  public address: string;
  public contactPerson: string;
  public mobileNumber: string;
  constructor( city: string, equipmentName: string, hospital: string, address: string, contactPerson: string, mobileNumber: string) {
    this.city = city;
    this.equipmentName = equipmentName;
    this.hospital = hospital;
    this.address = address;
    this.contactPerson = contactPerson;
    this.mobileNumber = mobileNumber;
  }
}
