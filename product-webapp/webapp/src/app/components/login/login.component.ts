import { Component, Inject, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService, User } from 'src/app/services/login.service';
import { SESSION_STORAGE, WebStorageService } from 'ngx-webstorage-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  constructor(private router: Router, private formBuilder: FormBuilder, private loginService: LoginService, @Inject(SESSION_STORAGE) private storage: WebStorageService) {
    console.log('In constructor', this.userobj);
  }

  // Creating profileForm for email ID and password
  profileForm = this.formBuilder.group({
    email: ['', [Validators.required]],
    password: ['', [Validators.required]]
  });

  key: any;
  hide = true;

  // Creating objects to store user details (userobj) and to store data returned from back-end (obj)
  obj: string[] = new Array('','','','');
  userobj: User = new User('', '');


  ngOnInit(): void {
    console.log('On Init', this.userobj);
  }


  // Implementation of login on click, calls login() of LoginService and routes to dashboard
  async login(): Promise<any> {
        this.loginService.login(this.userobj).subscribe((data)=> {
          console.log("login data:", data);
          this.obj=data;
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
        })
  
  }

  // Method takes role of user as argument and routes to the corresponding dashboard
  routetoDash(role: string): void{
    if ( role === 'Doctor'){
      this.router.navigate(['/doctor-dashboard']);
    }
    else if ( role === 'Volunteer'){
      this.router.navigate( ['/war-room-dashboard'] );
    }
  }

  }



