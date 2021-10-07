import { Component, OnInit } from '@angular/core';
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
    this.equipService.updateEquipment(this.Equip).subscribe(data => {
      console.log(this.Equip = data);
    });
    this.equipService.getUnverifiedEquipments().subscribe(data => {
      console.log(this.Equip = data);
    });
  }

  passOn(): void{
    this.equipService.getUnverifiedEquipments().subscribe(data => {
      console.log(this.Equip = data);
    });
  }

}
