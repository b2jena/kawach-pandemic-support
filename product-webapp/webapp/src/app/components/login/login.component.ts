import { Component, Inject, Injectable, OnInit } from '@angular/core';
import { Validators, FormControl, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService, User } from 'src/app/services/login.service';
import { SESSION_STORAGE, WebStorageService } from 'ngx-webstorage-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email = new FormControl('', [Validators.required, Validators.email]);
  pass = new FormControl('', [Validators.required]);


  constructor(private router: Router, private formBuilder: FormBuilder, private loginService: LoginService, @Inject(SESSION_STORAGE) private storage: WebStorageService) {
    console.log('In constructor', this.userobj);
  }

  profileForm = this.formBuilder.group({
    email: [''],
    password: ['']
  });

  key: any;

  obj: string[] = new Array('','','','');
  userobj: User = new User('', '');


  ngOnInit(): void {
    console.log('On Init', this.userobj);
  }


  async login(): Promise<any> {
      if (this.userobj.id === '' || this.email.invalid){
          this.loginService.showsnackbar('Email ID cannot be empty');
          this.loginService.showsnackbar(this.getErrorMessage());
        }
      else if (this.userobj.password === '' || this.pass.invalid){
          this.loginService.showsnackbar(this.getErrorPass());
        }
      else{
          const res= await this.loginService.genToken(this.userobj);
          console.log("res: ",res);
          this.obj= res;

          console.log("userobj in login(): ", this.userobj);
          console.log("this.obj[] after generateToken(): ",this.obj);
          console.log("this.obj[0] after generateToken(): ",this.obj[0]);
          if(this.obj[0] === ''){
            this.loginService.showsnackbar("Error, please try again after some time");
          }
          else if (this.obj[0] === 'Success'){
            this.loginService.showsnackbar(this.obj[0]);
            this.storage.set(this.key, this.obj[1]);
            this.routetoDash(this.obj[2]);
            localStorage.setItem('loggedIn', this.obj[3]);
          }
          else{
            this.loginService.showsnackbar('Error: ' + this.obj[0]);
          }
      
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
    }

  }



