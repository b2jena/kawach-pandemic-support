import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../_models/user';
import { UserService } from '../_services/user-service.service';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user = new User()
  Form = new FormGroup({
    email: new FormControl(),
    password: new FormControl(),
    name: new FormControl(),
    phone: new FormControl(),
    occupation: new FormControl()
}); 
  msg=""
  

  constructor(
    private _service: UserService, 
    private _router: Router,
    private fb:FormBuilder,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.Form=this.fb.group({
      email:["",[Validators.required,Validators.email]],
      password:["",[Validators.required,Validators.minLength(4)]],
      name:["",[Validators.required,Validators]],
      phone:["",[Validators.required,Validators.minLength(10)]],
      occupation:["",[Validators.required,Validators]]
     
    })
  }

  onSubmit(){
    this._service.registerUserFromRemote(this.user).subscribe(
      data =>{
        console.log("response recieved");
        this.router.navigate(['/login']);
      },
      error =>{
        console.log("exception occured");
        this.msg=error.error.message;
      }
    )
  }

}
