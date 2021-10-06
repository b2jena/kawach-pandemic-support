import { Component, Inject, Injectable, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService, User } from 'src/app/services/login.service';
import { SESSION_STORAGE, WebStorageService } from 'ngx-webstorage-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email = new FormControl('', [Validators.required, Validators.email/*, Validators.pattern('^[A-za-z]{4,20}'), */]);
  pass = new FormControl('', [Validators.required]);

  /* form = ({
    email : new FormControl('' , [ Validators.required, Validators.email ]),
    password : new FormControl('' , Validators.required)
  });*/

  constructor(private router: Router, private formBuilder: FormBuilder, private loginService: LoginService, @Inject(SESSION_STORAGE) private storage: WebStorageService) {
   }

  profileForm = this.formBuilder.group({
    email: [''],
    password: ['']
  });

  key: any;

  // obj: HttpResp = new HttpResp('Default', '', '');
  obj!: string[];
  userobj: User = new User('', '');
  // err: string= this.errorMsg.valueOf;

  ngOnInit(): void {
  }

  login(): void{
    if (this.userobj.id === '' || this.email.invalid){
      // window.alert('Email ID cannot be empty');
      /*x = document.getElementById("snackbar");

      // Add the "show" class to DIV
      if (x!= null){
        x.className = "show";

        // After 3 seconds, remove the show class from DIV
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
      }*/
      // this.loginService.showsnackbar('Email ID cannot be empty');
      this.loginService.showsnackbar(this.getErrorMessage());
    }
    else if (this.userobj.password === '' || this.pass.invalid){
      // window.alert('Password cannot be empty');
      /*x = document.getElementById("snackbar");

      // Add the "show" class to DIV
      if (x!= null){
        x.className = "show";

        // After 3 seconds, remove the show class from DIV
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
      }*/
      this.loginService.showsnackbar(this.getErrorPass());
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
      this.loginService.showsnackbar(this.obj[0]);
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

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'Mandatory field- Email ID cannot be empty';
    }
    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

  getErrorPass(){
    return this.pass.hasError('required') ? 'Mandatory field- Password cannot be empty' : '';
    // return this.pass.hasError('Mandatory field');
    }

  }

  /*get email(){
    return this.form.get('email');
  }

  get password(){
    return this.form.get('password');
  }*/


