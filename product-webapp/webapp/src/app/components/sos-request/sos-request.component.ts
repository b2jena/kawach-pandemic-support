import { Component, OnInit } from '@angular/core';
import { Requirement, SOSRequest, SosService } from 'src/app/services/sos-service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
// import { AnyRecord } from 'dns';
import { FormGroup } from '@angular/forms';


@Component({
  selector: 'app-sos-request',
  templateUrl: './sos-request.component.html',
  styleUrls: ['./sos-request.component.css']
})


export class SosRequestComponent implements OnInit {

  user1: Requirement = new Requirement('', '', '');
  user: SOSRequest = new SOSRequest('', '', [this.user1], '', '', '', '', '');
  file: File | any ;

  constructor(private sosService: SosService, private snackBar: MatSnackBar, private route: Router) { }

  ngOnInit(): void {}

  Upload(): void {
    this.sosService.PostFile(this.file).subscribe( data => { this.snackBar.open('file uploded succesfully'); } );
  }

  Create(): void {
    if (this.user.patientName === '' || this.user.city === '' || this.user.email === '' || this.user.gender === '' || this.user.hospitalised === '' || this.user.phoneNo === '' || this.user.requestStatus === '' || this.user1.requirementName === '' || this.user1.requirementQuantity === '' || this.user1.unitOfMeasure === '' ) {
      this.snackBar.open('Please enter the required details.');
    }
    else{
      console.log(this.user1);
      console.log(this.user1.requirementName);
      console.log(this.user1.requirementQuantity);
      console.log(this.user1.unitOfMeasure);
      this.sosService.CreateSosRequest(this.user).subscribe( data => { this.snackBar.open('SOS request added successfully.'); this.route.navigate ( [ '/sos' ] ); } );
    }
  }
}
