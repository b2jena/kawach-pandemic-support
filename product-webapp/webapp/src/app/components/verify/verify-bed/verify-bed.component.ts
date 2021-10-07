import { Component, OnInit } from '@angular/core';
import { BedService, Bed } from 'src/app/services/bed.service';

@Component({
  selector: 'app-verify-bed',
  templateUrl: './verify-bed.component.html',
  styleUrls: ['./verify-bed.component.css']
})
export class VerifyBedComponent implements OnInit {
  public bed !: Bed;
  isActive = false;
  count = 0;
  constructor(private bedService: BedService) { }

  ngOnInit(): void {
      this.bedService.getUnverifiedBed().subscribe(data => {
        console.log(this.bed = data);
      });
  }

  putVerified(): void{
      this.bed.verificationStatus = true;
      this.bedService.updateBed(this.bed).subscribe(data1 => {
        console.log(this.bed = data1);
        this.bedService.getUnverifiedBed().subscribe(data2 => {
          console.log(this.bed = data2);
        });
      }); }

  passOn(): void{
    this.count += 1;
    this.bedService.getUnverifiedBed().subscribe(data => {
      console.log(this.bed = data);
    });
  }

  check() {
    this.isActive = !this.isActive;
  }
}
