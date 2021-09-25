import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  public generateToken(user: User): Observable<string[]>{
    const jsonstr: string = '{ "id":"' + user.id + '", "password":"' + user.password + '" }';
    return this.http.post<string[]>('http://localhost:9099/api/v1/login/user', JSON.parse(jsonstr));
  }

}

/*export class HttpResp {

  public status: string;
  public token: string;
  public role: string;
  constructor(status: string, token: string, role: string) {
    this.role = role;
    this.status = status;
    this.token = token;
  }

}*/

export class User {
  public id: string;
  public password: string;
  constructor(id: string, password: string) {
    this.id = id;
    this.password = password;
  }
}
