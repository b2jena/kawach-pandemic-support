import { Component, OnInit } from '@angular/core';
import { SMedicineService, Medicine } from 'src/app/services/s-medicine.service';

@Component({
  selector: 'app-verify-medicine',
  templateUrl: './verify-medicine.component.html',
  styleUrls: ['./verify-medicine.component.css']
})
export class VerifyMedicineComponent implements OnInit {
  public med!: Medicine;
  constructor(private medService: SMedicineService) { }

  ngOnInit(): void {
    this.getUnVerfifiedData();
  }

  getUnVerfifiedData() {
    this.medService.getUnverifiedMed().subscribe(data => {
      this.med = data;
      console.log(this.med = data);
    });
  }

  putVerified(): void{
    this.med.verificationStatus = true;
    this.medService.updateMedicine(this.med).subscribe(data1 => {
      console.log(this.med = data1);
      this.medService.getUnverifiedMed().subscribe(data2 => {
        console.log(this.med = data2);
      });
    });
  }
passOn(): void{
    this.medService.getUnverifiedMed().subscribe(data => {
      console.log(this.med = data);
    });
  }
}
