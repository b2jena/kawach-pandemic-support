import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Equipment, EquipmentService } from 'src/app/services/equipment.service';

@Component({
    selector: 'app-list-equipments',
    templateUrl: './list-equipments.component.html',
    styleUrls: ['./list-equipments.component.css']
  })
  export class ListEquipmentsComponent implements OnInit {
    equipments!: Equipment[];
    constructor(private medService: EquipmentService, private router: Router) { }
    ngOnInit() {
      this.medService.getEquipments().subscribe(
        response => this.handleSuccessfulResponse(response),
       );
    }
    handleSuccessfulResponse(response: Equipment[]){
      this.equipments = response;
    }
    deleteEquipment(equipment: Equipment): void {
      this.medService.deleteEquipment(equipment)
        .subscribe( data => {
          this.equipments = this.equipments.filter(u => u !== equipment); } );
    }
  }

