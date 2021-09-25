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
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { PatientOtpComponent } from './components/landing-page/patient-otp/patient-otp.component';
import { LatestInformationComponent } from './components/landing-page/latest-information/latest-information.component';

@NgModule({
  declarations: [
    AppComponent,
    SosRequestComponent,
    UpdateMedicineComponent,
    ListMedicinesComponent,
    AddMedicineComponent,
    LandingPageComponent,
    PatientOtpComponent,
    LatestInformationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [HttpClient, SMedicineService],
  bootstrap: [AppComponent]
})
export class AppModule { }
