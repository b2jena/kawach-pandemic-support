import { Component, OnInit } from '@angular/core';
import {UserRegistrationService, Doctor} from 'src/app/services/user-registration.service';

@Component({
  selector: 'app-doctor-registration',
  templateUrl: './doctor-registration.component.html',
  styleUrls: ['./doctor-registration.component.css']
})
export class DoctorRegistrationComponent implements OnInit {

  user: Doctor = new Doctor('', '', '', 0, '', '');
  message: any;

  constructor(private userService: UserRegistrationService) { }

  ngOnInit(): void {
  }
  createDoctor(): void{
    this.userService.createDoctor(this.user).subscribe( (data) => alert(this.message = data));
  }

}
