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
  constructor(private bedService: BedService) { }

  ngOnInit(): void {
      this.bedService.getUnverifiedBed().subscribe(data => {
        console.log(this.bed = data);
      });
  }

  putVerified(): void{
      this.bed.verificationStatus = true;
      this.bedService.updateBed(this.bed).subscribe(data => {
        console.log(this.bed = data);
      });
      this.bedService.getUnverifiedBed().subscribe(data => {
        console.log(this.bed = data);
      });
  }

  passOn(): void{
    this.bedService.getUnverifiedBed().subscribe(data => {
      console.log(this.bed = data);
    });
  }

  check() {
    this.isActive = !this.isActive;
  }
}
