import { Component, ComponentFactoryResolver, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';

@Component({
  selector: 'app-warroom-add',
  templateUrl: './warroom-add.component.html',
  styleUrls: ['./warroom-add.component.css']
})
export class WarroomAddComponent implements OnInit {

  title = 'Side';
  opened = false;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }


  toggleSidebar(){
    this.opened = !this.opened;
  }




}
