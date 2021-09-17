import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../_models/user';
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _http : HttpClient) { }

  public loginUserFromRemote(user:User):Observable<any>{

    return this._http.post("http://localhost:8080/user/login",user)
}

public registerUserFromRemote(user:User):Observable<any>{

  return this._http.post("http://localhost:8080/user/signup",user)
}



}
