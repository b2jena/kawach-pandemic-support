import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Bed } from 'src/app/services/bed.service';
import { CloseSosService } from 'src/app/services/close-sos.service';
import { Equipment } from 'src/app/services/equipment.service';
import { Medicine } from 'src/app/services/s-medicine.service';

@Component({
  selector: 'app-search-dialog',
  templateUrl: './search-dialog.component.html',
  styleUrls: ['./search-dialog.component.css']
})
export class SearchDialogComponent implements OnInit {

  requirement: Array<any> = [];
  city: string;
  searchedMedicineData: Medicine;
  searchedBedData: Bed;
  searchedEquipData: Equipment;
  message: string;
  messageArray: Array<string> = [];
  showMessages = false;

  constructor(public dialogRef: MatDialogRef<SearchDialogComponent>,private formBuilder: FormBuilder, private service: CloseSosService,
    @Inject(MAT_DIALOG_DATA) public data:any) { 
      console.log("data recieved in dialog:", this.data);
      this.requirement = this.data.requirement;
      this.city = this.data.city;
    }

  ngOnInit(): void {
  }

  searchForm= this.formBuilder.group({
    requirement: ['', Validators.required],
    city: ['', Validators.required],
    comment:['', Validators.required]
  });

  searchData() {
    if (this.data.type == 'medicine') {
      this.service.getMedReq(this.searchForm.value.city, this.searchForm.value.requirement).subscribe((data)=> {
        this.constructMedString(data);
      })
    } else if(this.data.type == 'bed') {
      this.service.getBedReq(this.searchForm.value.city, this.searchForm.value.requirement).subscribe((data)=> {
        this.constructBedString(data);
      })
    } else if(this.data.type == 'equipment') {
      this.service.getEquipReq(this.searchForm.value.city, this.searchForm.value.requirement).subscribe((data)=> {
        this.constructEquipString(data);
      })
    }
  }

  constructMedString(searchResult: any) {
    this.showMessages = true;
    if (searchResult.length == 0) {
      this.messageArray = [];
      this.message = "Searched requirement is not available, please try again later!!";
      this.messageArray.push(this.message);
    } else {
      this.messageArray = [];
      searchResult.forEach((result:Medicine) => {
        this.searchedMedicineData = result;
        this.message = "Medicine "+this.searchedMedicineData.medicineName +" is available in specified city: "+this.searchedMedicineData.pharmacy + " address: "+this.searchedMedicineData.address+ " Kindly connect Mr/Mrs : "+this.searchedMedicineData.contactPerson+" (Phone number: "+ this.searchedMedicineData.mobileNumber+ ")"
        +" Get Well Soon, Stay safe.";
        this.messageArray.push(this.message);
      });
    }
  }

  constructBedString(searchResult: any) {
    this.showMessages = true;
    if (searchResult.length == 0) {
      this.messageArray = [];
      this.message = "Searched requirement is not available, please try again later!!";
      this.messageArray.push(this.message);
    } else {
      this.messageArray = [];
      searchResult.forEach((result:Bed) => {
        this.searchedBedData = result;
        this.message = "Bed of type "+this.searchedBedData.bedType +" is available in specified city: "+this.searchedBedData.city + " address: "+this.searchedBedData.address+ " Kindly connect Mr/Mrs : "+this.searchedBedData.contactPerson+" (Phone number: "+ this.searchedBedData.mobileNumber+ ")"
        +" Get Well Soon, Stay safe.";
        this.messageArray.push(this.message);
      });
    }
  }

  constructEquipString(searchResult: any) {
    this.showMessages = true;
    if (searchResult.length == 0) {
      this.messageArray = [];
      this.message = "Searched requirement is not available, please try again later!!";
      this.messageArray.push(this.message);
    } else {
      this.messageArray = [];
      searchResult.forEach((result:Equipment) => {
        this.searchedEquipData = result;
        this.message = "Equipment "+this.searchedEquipData.equipmentName +" is available in specified city: "+this.searchedEquipData.hospital + " address: "+this.searchedEquipData.address+ " Kindly connect Mr/Mrs : "+this.searchedEquipData.contactPerson+" (Phone number: "+ this.searchedEquipData.mobileNumber+ ")"
        +" Get Well Soon, Stay safe.";
        this.messageArray.push(this.message);
      });
    }
  }

  closeSOS() {
    this.data.close = true;
    this.data.message = this.searchForm.value.comment;
    this.dialogRef.close(this.data);
  }

  closeDialog() {
    this.data.close=false;
    this.dialogRef.close(this.data);
  }

}
