import { Component, OnInit } from '@angular/core';
import { SOSRequest, SosService } from 'src/app/services/sos-service';

@Component({
  selector: 'app-sos-request',
  templateUrl: './sos-request.component.html',
  styleUrls: ['./sos-request.component.css']
})
export class SosRequestComponent implements OnInit {

  user: SOSRequest = new SOSRequest('', '', '', '', '', '');

  constructor(private sosService: SosService) { }


  ngOnInit(): void {
  }
  Create(): void {
    this.sosService.CreateSosRequest(this.user).subscribe( data => { alert('SOS request added successfully.'); } );
  }
}
