import { Component, Inject, Injectable, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService, User } from 'src/app/services/login.service';
import { SESSION_STORAGE, WebStorageService } from 'ngx-webstorage-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form = new FormGroup({
    email : new FormControl('' , Validators.required),
    password : new FormControl('' , Validators.required)
  });

  constructor(private router: Router, private loginService: LoginService, @Inject(SESSION_STORAGE) private storage: WebStorageService) {
   }

  key: any;

  // obj: HttpResp = new HttpResp('Default', '', '');
  obj!: string[];
  userobj: User = new User('', '');
  // err: string= this.errorMsg.valueOf;

  ngOnInit(): void {
  }

  login(): void{
    if (this.userobj.id === ''){
      // window.alert('Email ID cannot be empty');
      /*x = document.getElementById("snackbar");

      // Add the "show" class to DIV
      if (x!= null){
        x.className = "show";

        // After 3 seconds, remove the show class from DIV
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
      }*/
      this.loginService.showsnackbar('Email ID cannot be empty');
    }
    else if (this.userobj.password === ''){
      // window.alert('Password cannot be empty');
      /*x = document.getElementById("snackbar");

      // Add the "show" class to DIV
      if (x!= null){
        x.className = "show";

        // After 3 seconds, remove the show class from DIV
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
      }*/
      this.loginService.showsnackbar('Password cannot be empty');
    }
    else{
      this.loginService.generateToken(this.userobj).subscribe( data => { this.obj = data ; } );
      /*
      if(this.obj[0]!= 'Success'){
        this.obj[0] = 'Login failed';
      }*/
      // alert(this.obj[0]);
      /*var x = document.getElementById("snackbar");

      // Add the "show" class to DIV
      if (x!= null){
        x.className = "show";

        // After 3 seconds, remove the show class from DIV
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
      }*/

      this.storage.set(this.key, this.obj[1]);
      this.routetoDash(this.obj[2]);
    }
  }

  routetoDash(role: string): void{
    if ( role === 'Doctor'){
      this.router.navigate(['/doctor-dashboard']);
    }
    else if ( role === 'Volunteer'){
      this.router.navigate( ['/war-room-dashboard'] );
    }
  }

}
