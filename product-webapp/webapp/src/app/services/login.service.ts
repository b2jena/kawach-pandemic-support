import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ObserversModule } from '@angular/cdk/observers';

@Injectable({
  providedIn: 'root'
})


export class LoginService {

  constructor(private http: HttpClient, private snackbar: MatSnackBar) { }


  async genToken( user: User): Promise<any>{ 
    const jsonstr: string = '{ "id":"' + user.id + '", "password":"' + user.password + '" }';
    try{
      return this.http.post<string[]>('/api/v1/login/user', JSON.parse(jsonstr));
    }
    catch {
      catchError(this.handleError);
      return null;
    }
    // return this.http.post<string[]>('/api/v1/login/user', JSON.parse(jsonstr)).pipe(
      // catchError(this.handleError) );
  }

  login(user: User) {
    const jsonstr: string = '{ "id":"' + user.id + '", "password":"' + user.password + '" }';
    return this.http.post<string[]>('/api/v1/login/user', JSON.parse(jsonstr)).pipe(
      catchError(this.handleError) );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.log('An error occurred:', error.error);
    } else {
      console.log(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    return throwError(
      'Something bad happened; please try again later.');
  }

  showsnackbar(message: string){
    this.snackbar.open(message, 'OK', { duration: 4000,
    horizontalPosition: 'center',
    verticalPosition: 'top'} );
  }

}


export class User {
  public id: string;
  public password: string;
  constructor(id: string, password: string) {
    this.id = id;
    this.password = password;
  }
}
