import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Equipment, EquipmentService } from 'src/app/services/equipment.service';

@Component({
  selector: 'app-add-equipment',
  templateUrl: './add-equipment.component.html',
  styleUrls: ['./add-equipment.component.css']
})
export class AddEquipmentComponent implements OnInit {
  mobFormControl = new FormControl('', [
    Validators.required,
    Validators.pattern('^[0-9]{10}'),
  ]);
  isActive = false;
  user: Equipment = new Equipment( '', '', '', '', '', '', this.isActive);

  constructor(private equipmentService: EquipmentService, private snackBar: MatSnackBar,  private formBuilder: FormBuilder) { }

  ngOnInit() {
  }

  equipForm = this.formBuilder.group({
    equipmentName: new FormControl('', [Validators.required, Validators.minLength(2)]),
    city: new FormControl('', Validators.required),
    hospital: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    contactPerson: new FormControl('', [Validators.required, Validators.minLength(3)]),
    mobileNumber: new FormControl('', [Validators.required, Validators.pattern('[0-9]{10}')]),
    verificationStatus: new FormControl(''),
  });
  showSnackbars(content: string, action: string) {
    const snack = this.snackBar.open(content, action, {
      duration: 2000,
      verticalPosition: 'top', // Allowed values are  'top' | 'bottom'
      horizontalPosition: 'center', // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
    });
    snack.afterDismissed().subscribe(() => {
      console.log('This will be shown after snackbar disappeared');
    });
    snack.onAction().subscribe(() => {
      // window.location.reload();
      console.log('This will be called when snackbar button clicked');
    });
  }
    CreateEquipment(): void {
      this.equipForm.get('verificationStatus').setValue(this.isActive);
      console.log("data:", this.equipForm.value);
      this.equipmentService.CreateEquipment(this.equipForm.value).subscribe( data => {
         this.showSnackbars('Equipment added successfully.', 'x');
         this.equipForm.reset();
        });
    }
    check() {
      this.isActive = !this.isActive;
    }
}
