import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
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

  constructor(private equipmentService: EquipmentService, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }
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
      if ( this.user.equipmentName === '' || this.user.hospital === '' || this.user.address === '' || this.user.city === '' || this.user.contactPerson === '' || this.user.mobileNumber === ''){
        this.showSnackbars('Please fill the empty field(s).', 'x');
      } else {
        this.user.verificationStatus = this.isActive;
        this.equipmentService.CreateEquipment(this.user).subscribe( data => { this.showSnackbars('Equipment added successfully.', 'x'); });
        window.setTimeout(function(){location.reload()}, 2000);
      }
    }
    check() {
      this.isActive = !this.isActive;
    }
}
