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
    this.medService.getUnverifiedMed().subscribe(data => {
      console.log(this.med = data);
    });
  }

  putVerified(): void{
    this.med.verificationStatus = true;
    this.medService.updateMedicine(this.med).subscribe(data => {
      console.log(this.med = data);
    });
    this.medService.getUnverifiedMed().subscribe(data => {
      console.log(this.med = data);
    });
  }

  passOn(): void{
    this.medService.getUnverifiedMed().subscribe(data => {
      console.log(this.med = data);
    });
  }
}
