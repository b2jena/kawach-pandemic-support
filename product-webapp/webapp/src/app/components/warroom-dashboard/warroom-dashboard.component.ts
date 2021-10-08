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

  constructor(private router: Router) { }

  ngOnInit(): void {
  }


  toggleSidebar(){
    this.opened = !this.opened;
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
