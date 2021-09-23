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

@NgModule({
  declarations: [
    AppComponent,
    SosRequestComponent,
    UpdateMedicineComponent,
    ListMedicinesComponent,
    AddMedicineComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule, FormsModule
  ],
  providers: [HttpClient, SMedicineService],
  bootstrap: [AppComponent]
})
export class AppModule { }
