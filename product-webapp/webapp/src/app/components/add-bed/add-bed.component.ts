import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Bed, BedService } from 'src/app/services/bed.service';

@Component({
  selector: 'app-add-bed',
  templateUrl: './add-bed.component.html',
  styleUrls: ['./add-bed.component.css']
})
export class AddBedComponent implements OnInit {

  user: Bed = new Bed( '', '', '', '', '');

  constructor(private equipmentService: BedService) { }

  ngOnInit() {
  }
    CreateEquipment(): void {
      this.equipmentService.CreateBed(this.user).subscribe( data => { alert('Bed added successfully.'); });
    }
}
