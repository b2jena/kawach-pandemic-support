import { ListBedComponent } from './components/list-bed/list-bed.component';
import { AddBedComponent } from './components/add-bed/add-bed.component';
import { BedService } from './services/bed.service';
import { ListEquipmentsComponent } from './components/list-equipments/list-equipments.component';
import { EquipmentService } from './services/equipment.service';
import { AddEquipmentComponent } from './components/add-equipment/add-equipment.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatButtonModule } from '@angular/material/button';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddMedicineComponent } from './components/add-medicine/add-medicine.component';
import { ListMedicinesComponent } from './components/list-medicines/list-medicines.component';
import { SosRequestComponent } from './components/sos-request/sos-request.component';
import { UpdateMedicineComponent } from './components/update-medicine/update-medicine.component';
<<<<<<< HEAD
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
=======
import { FormsModule } from '@angular/forms';
>>>>>>> 9f41d10a86e073c83e65790a3e2501993b535d7e
import { SMedicineService } from './services/s-medicine.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { DoctorRegistrationComponent } from './components/doctor-registration/doctor-registration.component';
import { VolunteerRegistrationComponent } from './components/volunteer-registration/volunteer-registration.component';
import { UserRegistrationService } from './services/user-registration.service';
import { LoginComponent } from './components/login/login.component';
import { DoctorDashboardComponent } from './components/doctor-dashboard/doctor-dashboard.component';
import { WarroomDashboardComponent } from './components/warroom-dashboard/warroom-dashboard.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';

import { LatestInformationComponent } from './components/landing-page/latest-information/latest-information.component';
import { InformationService } from './services/information.service';
import { SosService } from './services/sos-service';
import { PatientOtpComponent } from './components/landing-page/patient-otp/patient-otp.component';
<<<<<<< HEAD
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSliderModule} from '@angular/material/slider';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {MatSelectModule} from '@angular/material/select';
=======
import { HeaderComponent } from './components/header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Overlay } from '@angular/cdk/overlay';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatExpansionModule  } from '@angular/material/expansion';
import { MatCardModule } from '@angular/material/card';
import { MatPaginatorModule } from '@angular/material/paginator';
import { FooterComponent } from './components/footer/footer.component';
import { MatTableModule } from '@angular/material/table';
import { FlexLayoutModule } from '@angular/flex-layout';
>>>>>>> 9f41d10a86e073c83e65790a3e2501993b535d7e



@NgModule({
  declarations: [
    AppComponent,
    SosRequestComponent,
    UpdateMedicineComponent,
    ListMedicinesComponent,
    AddMedicineComponent,
    PatientOtpComponent,
    DoctorRegistrationComponent,
    VolunteerRegistrationComponent,
    LandingPageComponent,
    LandingPageComponent,
    LatestInformationComponent,
    LoginComponent,
    DoctorDashboardComponent,
    WarroomDashboardComponent,
    LandingPageComponent,
    AddEquipmentComponent,
    ListEquipmentsComponent,
    LatestInformationComponent,
    AddBedComponent,
    ListBedComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
<<<<<<< HEAD
    MatSnackBarModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatSliderModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatCardModule,
    MatSelectModule
=======
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatGridListModule,
    MatExpansionModule,
    MatInputModule,
    MatSnackBarModule,
    FlexLayoutModule,
    MatCardModule,
    MatTableModule,
    MatPaginatorModule
>>>>>>> 9f41d10a86e073c83e65790a3e2501993b535d7e
  ],
  providers: [HttpClient, SMedicineService, UserRegistrationService, EquipmentService, BedService, SosService, MatSnackBarModule,
  MatSnackBar, Overlay],

  bootstrap: [AppComponent]
})
export class AppModule { }
