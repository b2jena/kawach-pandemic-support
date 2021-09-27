import { ListBedComponent } from './components/list-bed/list-bed.component';
import { AddBedComponent } from './components/add-bed/add-bed.component';
import { BedService } from './services/bed.service';
import { ListEquipmentsComponent } from './components/list-equipments/list-equipments.component';
import { EquipmentService } from './services/equipment.service';
import { AddEquipmentComponent } from './components/add-equipment/add-equipment.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddMedicineComponent } from './components/add-medicine/add-medicine.component';
import { ListMedicinesComponent } from './components/list-medicines/list-medicines.component';
import { SosRequestComponent } from './components/sos-request/sos-request.component';
import { UpdateMedicineComponent } from './components/update-medicine/update-medicine.component';
import {FormsModule} from '@angular/forms';
import { SMedicineService } from './services/s-medicine.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { DoctorDashboardComponent } from './components/doctor-dashboard/doctor-dashboard.component';
import { WarroomDashboardComponent } from './components/warroom-dashboard/warroom-dashboard.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
<<<<<<< HEAD
import { LatestInformationComponent } from './components/landing-page/latest-information/latest-information.component';
import { InformationService } from './services/information.service';
=======
import { PatientOtpComponent } from './components/landing-page/patient-otp/patient-otp.component';
import { LatestInformationComponent } from './components/landing-page/latest-information/latest-information.component';
>>>>>>> a0d4ac3451d66f1b33da4fdfc8085aaa7f9c2f18

@NgModule({
  declarations: [
    AppComponent,
    SosRequestComponent,
    UpdateMedicineComponent,
    ListMedicinesComponent,
    AddMedicineComponent,
    LandingPageComponent,
    PatientOtpComponent,
<<<<<<< HEAD
    LandingPageComponent,
    LatestInformationComponent
=======
    LoginComponent,
    DoctorDashboardComponent,
    WarroomDashboardComponent,
    LandingPageComponent,
    AddEquipmentComponent,
    ListEquipmentsComponent,
    LatestInformationComponent,
    AddBedComponent,
    ListBedComponent
>>>>>>> a0d4ac3451d66f1b33da4fdfc8085aaa7f9c2f18
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
<<<<<<< HEAD
  providers: [HttpClient, SMedicineService, InformationService],
=======
  providers: [HttpClient, SMedicineService, EquipmentService, BedService],
>>>>>>> a0d4ac3451d66f1b33da4fdfc8085aaa7f9c2f18
  bootstrap: [AppComponent]
})
export class AppModule { }
