import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import {UserRegistrationService, Doctor} from 'src/app/services/user-registration.service';

interface Specialization{
  value: string;
}

@Component({
  selector: 'app-doctor-registration',
  templateUrl: './doctor-registration.component.html',
  styleUrls: ['./doctor-registration.component.css']
})
export class DoctorRegistrationComponent implements OnInit {

  user: Doctor = new Doctor('', '', '', 0, '', '');

  selectedFile!: File;
  isImageSaved = false;
  cardImageBase64: string | undefined;

  specialisation: Specialization[] = [
    {value: 'Allergist'},
    {value: 'Cardiologist'},
    {value: 'Dermatologist'},
    {value: 'Endocrinologist'},
    {value: 'Gastroenterologist'},
    {value: 'Hematologist'},
    {value: 'Infectious Disease Specialist'},
    {value: 'Neurologist'},
    {value: 'Pediatrician'},
    {value: 'Psychiatrist'}
  ];

  message: any;
  hide = true;
  action = 'OK';
  // email = new FormControl('', [Validators.required, Validators.email]);
  // name = new FormControl('', [Validators.required]);
  // medId = new FormControl('', [Validators.required]);
  // ph = new FormControl('', [Validators.required]);
  // pass = new FormControl('', [Validators.required]);


  constructor(private userService: UserRegistrationService, private formBuilder: FormBuilder, private snackBar: MatSnackBar, private httpClient: HttpClient) {}

  // profileForm = this.formBuilder.group({
  //   emailId: [''],
  //   fullName: [''],
  //   medicalRegistrationId: [''],
  //   phoneNumber: [''],
  //   specialization: [''],
  //   password: ['']
  // });

  profileForm = this.formBuilder.group({
    emailId: new FormControl('', [Validators.required, Validators.email]),
    fullName: new FormControl('', Validators.required),
    medicalRegistrationId: new FormControl('', Validators.required),
    phoneNumber: new FormControl('', Validators.required),
    specialization: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });





  ngOnInit(): void {
  }
  // createDoctor(): void{
  //   this.userService.createDoctor(this.user).subscribe( (data) => this.snackBar.open((this.message = data).toString(), this.action, {
  //     duration: 3000,
  //     horizontalPosition: 'center',
  //     verticalPosition: 'top'
  //   }));
  // }

  // getErrorMessage() {
  //   if (this.email.hasError('required')) {
  //     return 'Mandatory field';
  //   }

  //   return this.email.hasError('email') ? 'Not a valid email' : '';
  // }
  // getErrorName(){
  //   return this.name.hasError('required') ? 'Mandatory field' : '';
  // }
  // getErrorPh(){
  //   return this.ph.hasError('required') ? 'Mandatory field' : '';
  // }
  // getErrorPass(){
  //   return this.pass.hasError('required') ? 'Mandatory field' : '';
  // }
  // getErrormedId(){
  //   return this.medId.hasError('required') ? 'Mandatory field' : '';
  // }

  submitData() {
    console.log('form data:', this.profileForm.value);
    console.log('image:', this.selectedFile);

    const item = this.profileForm.value;
    const uploadFileData = new FormData();
    uploadFileData.append('item', JSON.stringify(item));
    uploadFileData.append('image', this.selectedFile);

    this.httpClient
      .post('http://localhost:8081/api/v1/register/doctor', uploadFileData, {
        observe: 'response',
      })
      .subscribe(
        (response) => {
          console.log(response);
          if (response.status === 201) {
            setTimeout(() => {
              location.reload();
            }, 2000);
          } else {
            setTimeout(() => {
              location.reload();
            }, 2000);
          }
        },
        (err) => console.log('Error Occured during saving: ' + err.message)
      );
  }

  fileChangeEvent(fileInput: any) {
    const imageIn = fileInput.target.files[0];
    this.selectedFile = imageIn;

    if (fileInput.target.files && fileInput.target.files[0]) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const image = new Image();
        image.src = e.target.result;
        image.onload = (rs: any) => {
          /* tslint:disable:no-string-literal */
          const IMG_HEIGHT = rs.currentTarget['height'];
          const IMG_WIDTH = rs.currentTarget['width'];
          /* tslint:enable:no-string-literal */
          console.log(IMG_HEIGHT, IMG_WIDTH);
          const imgBase64Path = e.target.result;
          this.cardImageBase64 = imgBase64Path;
          this.isImageSaved = true;
        };
      };

      reader.readAsDataURL(fileInput.target.files[0]);
    }
  }

}
