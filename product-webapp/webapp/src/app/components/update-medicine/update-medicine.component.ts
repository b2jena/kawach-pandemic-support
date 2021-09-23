import { Component, OnInit } from '@angular/core';
import { Medicine, SMedicineService } from 'src/app/services/s-medicine.service';


@Component({
  selector: 'app-update-medicine',
  templateUrl: './update-medicine.component.html',
  styleUrls: ['./update-medicine.component.css']
})
export class UpdateMedicineComponent implements OnInit {
  user: Medicine = new Medicine('', '', '');
  constructor(private medService: SMedicineService) { }
  ngOnInit() {
  }
  updatemedicine(): void {
    console.log(this.user.city);
    this.medService.updateMedicine(this.user)
        .subscribe( data => {
          alert('Medicine updated successfully.');
        });
  }
}
