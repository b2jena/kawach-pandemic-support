import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { Medicine, SMedicineService } from 'src/app/services/s-medicine.service';

@Component({
  selector: 'app-add-medicine',
  templateUrl: './add-medicine.component.html',
  styleUrls: ['./add-medicine.component.css']
})
export class AddMedicineComponent implements OnInit {

  user: Medicine = new Medicine( '', '', '', '', '', '' );

  constructor(private medicineService: SMedicineService) { }

  ngOnInit() {
  }
    CreateMedicine(): void {
      this.medicineService.CreateMedicine(this.user).subscribe( data => { alert('Medicine added successfully.'); });
    }
}
