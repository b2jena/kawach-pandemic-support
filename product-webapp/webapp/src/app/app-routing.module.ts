import { ListBedComponent } from './components/list-bed/list-bed.component';
import { AddBedComponent } from './components/add-bed/add-bed.component';
import { ListEquipmentsComponent } from './components/list-equipments/list-equipments.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { AddEquipmentComponent } from './components/add-equipment/add-equipment.component';
import { AddMedicineComponent } from './components/add-medicine/add-medicine.component';
<<<<<<< HEAD
import { LatestInformationComponent } from './components/landing-page/latest-information/latest-information.component';
=======
import { DoctorDashboardComponent } from './components/doctor-dashboard/doctor-dashboard.component';
>>>>>>> a0d4ac3451d66f1b33da4fdfc8085aaa7f9c2f18
import { ListMedicinesComponent } from './components/list-medicines/list-medicines.component';
import { LoginComponent } from './components/login/login.component';
import { SosRequestComponent } from './components/sos-request/sos-request.component';
import { UpdateMedicineComponent } from './components/update-medicine/update-medicine.component';
import { WarroomDashboardComponent } from './components/warroom-dashboard/warroom-dashboard.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';

const routes: Routes = [
  {
    path: '',
    component: AppComponent
  }, {
    path: 'sos',
    component: SosRequestComponent
  }, {
    path: 'addmedicine',
    component: AddMedicineComponent
  }, {
    path: 'listmedicine',
    component: ListMedicinesComponent
  }, {
    path: 'updatemedicine',
    component: UpdateMedicineComponent
<<<<<<< HEAD
  },
  {
    path: 'latestInfo',
    component: LatestInformationComponent
=======
  }, {
    path: 'login',
    component: LoginComponent
  }, {
    path: 'doctor-dashboard',
    component: DoctorDashboardComponent
  }, {
    path: 'war-room-dashboard',
    component: WarroomDashboardComponent},
     {
    path: 'addequipment',
    component: AddEquipmentComponent
  }, {
    path: 'listequipment',
    component: ListEquipmentsComponent
  }, {
    path: 'landingpage',
    component: LandingPageComponent
  }, {
    path: 'addbed',
    component: AddBedComponent
  }, {
    path: 'listbed',
    component: ListBedComponent
>>>>>>> a0d4ac3451d66f1b33da4fdfc8085aaa7f9c2f18
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
