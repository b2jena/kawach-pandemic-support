import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Medicine, SMedicineService } from 'src/app/services/s-medicine.service';

@Component({
  selector: 'app-list-medicines',
  templateUrl: './list-medicines.component.html',
  styleUrls: ['./list-medicines.component.css']
})
export class ListMedicinesComponent implements OnInit {
  medicines!: Medicine[];
  constructor(private medService: SMedicineService, private router: Router) { }
  ngOnInit() {
    this.medService.getMedicines().subscribe(
      response => this.handleSuccessfulResponse(response),
     );
  }
  handleSuccessfulResponse(response: Medicine[]){
    this.medicines = response;
  }
  deleteMedicine(medicine: Medicine): void {
    this.medService.deleteMedicine(medicine)
      .subscribe( data => {
        this.medicines = this.medicines.filter(u => u !== medicine); } );
      }
}
