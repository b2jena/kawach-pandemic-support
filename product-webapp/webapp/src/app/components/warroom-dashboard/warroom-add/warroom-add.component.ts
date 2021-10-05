import { Component, ComponentFactoryResolver, OnInit } from '@angular/core';
import { AddBedComponent } from '../../add-bed/add-bed.component';
import { AddEquipmentComponent } from '../../add-equipment/add-equipment.component';
import { AddMedicineComponent } from '../../add-medicine/add-medicine.component';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';

@Component({
  selector: 'app-warroom-add',
  templateUrl: './warroom-add.component.html',
  styleUrls: ['./warroom-add.component.css']
})
export class WarroomAddComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  title = 'Side';
  opened=false;
  
  toggleSidebar(){
    this.opened = !this.opened;
  }

  // routeTo(role: string): void{
  //   }
  //   if ( role === 'Medicine'){
  //     this.comp=AddMedicineComponent;
  //   }
  //   else if ( role === 'Equipment'){
  //     this.comp = AddEquipmentComponent;
  //   }
  //   else if ( role === 'Bed'){
  //     this.comp = AddBedComponent;
  //   }
  // }


}
