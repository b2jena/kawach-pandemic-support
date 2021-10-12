import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EquipmentService, Equipment } from 'src/app/services/equipment.service';

@Component({
  selector: 'app-verify-equipments',
  templateUrl: './verify-equipments.component.html',
  styleUrls: ['./verify-equipments.component.css']
})
export class VerifyEquipmentsComponent implements OnInit {
  public Equip!: Equipment;

  constructor(private equipService: EquipmentService) { }

  ngOnInit(): void {
    this.equipService.getUnverifiedEquipments().subscribe(data => {
      console.log(this.Equip = data);
    });
  }

  putVerified(): void{
    this.Equip.verificationStatus = true;
    this.equipService.updateEquipment(this.Equip).subscribe(data1 => {
      console.log(this.Equip = data1);
      this.equipService.getUnverifiedEquipments().subscribe(data2 => {
        console.log(data2 = this.Equip);
      });
    });
  }

  passOn(): void{
    this.equipService.getUnverifiedEquipments().subscribe(data => {
      console.log(this.Equip = data);
    });
  }

}
