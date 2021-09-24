import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  public generateToken(request){
    return this.http.post('http://localhost:8081/api/v1/register/volunteer',request,{responseType: 'text' as 'json'})
  }
}
