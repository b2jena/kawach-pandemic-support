import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-warroom-dashboard',
  templateUrl: './warroom-dashboard.component.html',
  styleUrls: ['./warroom-dashboard.component.css']
})
export class WarroomDashboardComponent implements OnInit {

  title = 'Side';
  opened = false;
  verifyEquipment = false;
  verifyMedicine = false;
  verifyBed = false;

  closeEquip= false;
  closeMed = true;
  closeBed = false;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }


  toggleSidebar(){
    this.opened = !this.opened;
  }

  verMed() {
    this.verifyMedicine=true;
    this.verifyEquipment=false;
    this.verifyBed=false;
  }

  verEquip() {
    this.verifyMedicine=false;
    this.verifyEquipment=true;
    this.verifyBed=false;
  }

  verBed() {
    this.verifyMedicine=false;
    this.verifyEquipment=false;
    this.verifyBed=true;
  }

  medSos() {
    this.closeMed=true;
    this.closeEquip=false;
    this.closeBed=false;
  }

  EquipSos() {
    this.closeMed=false;
    this.closeEquip=true;
    this.closeBed=false;
  }

  bedSos() {
    this.closeMed=false;
    this.closeEquip=false;
    this.closeBed=true;
  }


  // routeTo(role: string): void{
  //   if ( role === 'sos'){
  //     this.router.navigate(['/closesos']);
  //   }
  //   else if ( role === 'Verification'){
  //     this.router.navigate( ['/listmedicine'] );
  //   }
  //   else if ( role === 'Medicine'){
  //     this.router.navigate( ['/addmedicine'] );
  //   }
  //   else if ( role === 'Equipment'){
  //     this.router.navigate( ['/addequipment'] );
  //   }
  //   else if ( role === 'Bed'){
  //     this.router.navigate( ['/addbed'] );
  //   }
  // }

}
