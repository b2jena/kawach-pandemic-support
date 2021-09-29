import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Equipment, EquipmentService } from 'src/app/services/equipment.service';

@Component({
  selector: 'app-add-equipment',
  templateUrl: './add-equipment.component.html',
  styleUrls: ['./add-equipment.component.css']
})
export class AddEquipmentComponent implements OnInit {

  user: Equipment = new Equipment( '', '', '', '', '', '' );

  constructor(private equipmentService: EquipmentService, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }
    CreateEquipment(): void {
      if ( this.user.equipmentName === '' || this.user.hospital === '' || this.user.address === '' || this.user.city === '' || this.user.contactPerson === '' || this.user.mobileNumber === ''){
        this.snackBar.open('Please fill the empty field(s).');
      } else {
        this.equipmentService.CreateEquipment(this.user).subscribe( data => { this.snackBar.open('Equipment added successfully.'); });
      }
    }
}
