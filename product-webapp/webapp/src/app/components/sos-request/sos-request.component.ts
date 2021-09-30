import { Component, OnInit } from '@angular/core';
import { Requirment, SOSRequest, SosService } from 'src/app/services/sos-service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sos-request',
  templateUrl: './sos-request.component.html',
  styleUrls: ['./sos-request.component.css']
})
export class SosRequestComponent implements OnInit {

  user1: Requirment = new Requirment('', '', '');
  user: SOSRequest = new SOSRequest('', '', [], '', '', '', '', '');



  constructor(private sosService: SosService, private snackBar: MatSnackBar, private route: Router) { }


  ngOnInit(): void {
  }

  Create(): void {
    if (this.user.patientName === '' || this.user.city === '' || this.user.email === '' || this.user.gender === '' || this.user.hospitalised === '' || this.user.phoneNo === '' || this.user.requestStatus === '' || this.user1.requirementName === '' || this.user1.requirementQuantity === '' || this.user1.unitOfMeasure === '' ) {
      this.snackBar.open('Please enter the required details.');
    }
    else{
      this.sosService.CreateSosRequest(this.user).subscribe( data => { this.snackBar.open('SOS request added successfully.'); this.route.navigate ( [ '/sos' ] ); } );
    }
  }
}
