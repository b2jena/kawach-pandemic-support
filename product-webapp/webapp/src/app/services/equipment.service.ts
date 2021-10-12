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
    return this.httpService.post<Equipment>('/api/v1/resource/equipment/create', equipment);
  }
  public deleteEquipment(equipment: Equipment) {
    return this.httpService.delete<Equipment>('/api/v1/resource/equipment//delete/' + equipment.city);
  }
  public updateEquipment(equipment: Equipment) {
    console.log(equipment);
    return this.httpService.put<Equipment>('/api/v1/resource/equipment/update', equipment);
  }
  getEquipments(){
    return this.httpService.get<Equipment[]>('/api/v1/resource/equipment/getAll');
  }
  public getUnverifiedEquipments()
  {
    return this.httpService.get<Equipment>('/api/v1/resource/equipment/getUnverified');
  }
}
export class Equipment{
  public city: string;
  public equipmentName: string;
  public hospital: string;
  public address: string;
  public contactPerson: string;
  public mobileNumber: string;
  public verificationStatus: boolean;
  constructor( city: string, equipmentName: string, hospital: string, address: string, contactPerson: string, mobileNumber: string, verificationStatus: boolean) {
    this.city = city;
    this.equipmentName = equipmentName;
    this.hospital = hospital;
    this.address = address;
    this.contactPerson = contactPerson;
    this.mobileNumber = mobileNumber;
    this.verificationStatus = verificationStatus;
  }
}
