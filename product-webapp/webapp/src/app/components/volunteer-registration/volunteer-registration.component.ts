import { Component, OnInit } from '@angular/core';
import {UserRegistrationService, Volunteer } from 'src/app/services/user-registration.service';

@Component({
  selector: 'app-volunteer-registration',
  templateUrl: './volunteer-registration.component.html',
  styleUrls: ['./volunteer-registration.component.css']
})
export class VolunteerRegistrationComponent implements OnInit {

  user: Volunteer = new Volunteer('', '', 0, '');
  message: any;

  constructor(private userService: UserRegistrationService) { }

  ngOnInit(): void {
  }

  createVolunteer(): void{
    this.userService.createVolunteer(this.user).subscribe( (data) => alert(this.message = data));
  }


}
