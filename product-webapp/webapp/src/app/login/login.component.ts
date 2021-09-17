import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../_services/user-service.service';
import { User } from '../_models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  msg = ""
  user = new User()
  Form = new FormGroup({
    email: new FormControl(),
    password: new FormControl()
}); 
  

  constructor(
    private _service : UserService,
    private fb:FormBuilder,
    private router: Router
   
  ) {
  }

  ngOnInit(): void {
    this.Form=this.fb.group({
      email:["",[Validators.required,Validators.email]],
      password:["",[Validators.required,Validators.minLength(6)]]
    })
  }

  signInHandler(): void {
   console.log("not implemented yet")
  }

  onSubmit(){
    this._service.loginUserFromRemote(this.user).subscribe(
      data => {console.log("Success!!", data);
              localStorage.setItem("username", data.email);
              this.router.navigate(['/home']);
    },
      error => {console.log("Error occured")
                this.msg = "  Please check your email or password"
    }

    )
      //...
    
  }
  

}
