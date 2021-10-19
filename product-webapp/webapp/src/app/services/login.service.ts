import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})


export class LoginService {

  constructor(private http: HttpClient, private snackbar: MatSnackBar) { }

  // Method that makes http post request to back-end with login details
  login(user: User) {
    const jsonstr: string = '{ "id":"' + user.id + '", "password":"' + user.password + '" }';
    return this.http.post<string[]>('/api/v1/login/user', JSON.parse(jsonstr)).pipe(
      catchError(this.handleError) );
  }

  // Method to handle errors
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

  // Method to display snackbar
  showsnackbar(message: string){
    this.snackbar.open(message, 'OK', { duration: 4000,
    horizontalPosition: 'center',
    verticalPosition: 'top'} );
  }

}


// User class with string id and password, object can store login details
export class User {
  public id: string;
  public password: string;
  constructor(id: string, password: string) {
    this.id = id;
    this.password = password;
  }
}
