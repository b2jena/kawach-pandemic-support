import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InformationService {

  LocationStats: LocationStats[] = [];
  constructor(private httpService: HttpClient) { }

  public getInformation(): Observable<LocationStats[]>
  {
    return this.httpService.get<LocationStats[]>('http://localhost:8769/api/v1/information');
  }
}
export class LocationStats {
  public loc: string;
  public confirmedCasesIndian: string;
  public confirmedCasesForeign: string;
  public discharged: string;
  public deaths: string;
  public totalConfirmed: string;
  constructor(loc: string, confirmedCasesIndian: string, confirmedCasesForeign: string,
              discharged: string, deaths: string, totalConfirmed: string) {
    this.loc = loc;
    this.confirmedCasesIndian = confirmedCasesIndian;
    this.confirmedCasesForeign = confirmedCasesForeign;
    this.discharged = discharged;
    this.deaths = deaths;
    this.totalConfirmed = totalConfirmed;
  }
}
