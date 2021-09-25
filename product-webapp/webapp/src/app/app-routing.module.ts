import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { AddMedicineComponent } from './components/add-medicine/add-medicine.component';
import { DoctorDashboardComponent } from './components/doctor-dashboard/doctor-dashboard.component';
import { ListMedicinesComponent } from './components/list-medicines/list-medicines.component';
import { LoginComponent } from './components/login/login.component';
import { SosRequestComponent } from './components/sos-request/sos-request.component';
import { UpdateMedicineComponent } from './components/update-medicine/update-medicine.component';
import { WarroomDashboardComponent } from './components/warroom-dashboard/warroom-dashboard.component';

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
  }, {
    path: 'login',
    component: LoginComponent
  }, {
    path: 'doctor-dashboard',
    component: DoctorDashboardComponent
  }, {
    path: 'war-room-dashboard',
    component: WarroomDashboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
