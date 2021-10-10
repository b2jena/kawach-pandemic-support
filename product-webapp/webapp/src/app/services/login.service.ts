import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})


export class LoginService {

  constructor(private http: HttpClient, private snackbar: MatSnackBar) { }

  public generateToken(user: User): Observable<string[]>{
    const jsonstr: string = '{ "id":"' + user.id + '", "password":"' + user.password + '" }';
    try{
      return this.http.post<string[]>('http://localhost:9099/api/v1/login/user', JSON.parse(jsonstr));
    }
    catch{
      catchError(this.handleError);
      return null;
    }
    //return this.http.post<string[]>('http://localhost:9099/api/v1/login/user', JSON.parse(jsonstr)).pipe(
      //catchError(this.handleError) );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.log('An error occurred:', error.error);
      // alert('An error occurred:' + error.error);
      // this.showsnackbar('An error occurred:' + error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.log(
        `Backend returned code ${error.status}, body was: `, error.error);
      // alert(`Error code ${error.status}:` + error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Something bad happened; please try again later.');
  }

  showsnackbar(message: string){
    this.snackbar.open(message, 'OK', { duration: 4000,
    horizontalPosition: 'center',
    verticalPosition: 'top'} );
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
