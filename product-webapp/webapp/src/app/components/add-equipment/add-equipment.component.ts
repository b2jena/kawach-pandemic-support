import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Equipment, EquipmentService } from 'src/app/services/equipment.service';

@Component({
  selector: 'app-add-equipment',
  templateUrl: './add-equipment.component.html',
  styleUrls: ['./add-equipment.component.css']
})
export class AddEquipmentComponent implements OnInit {

  user: Equipment = new Equipment( '', '', '', '', '', '' );

  constructor(private equipmentService: EquipmentService) { }

  ngOnInit() {
  }
    CreateEquipment(): void {
      this.equipmentService.CreateEquipment(this.user).subscribe( data => { alert('Equipment added successfully.'); });
    }
}
