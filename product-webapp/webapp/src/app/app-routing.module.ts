import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { AddMedicineComponent } from './components/add-medicine/add-medicine.component';
import { LatestInformationComponent } from './components/landing-page/latest-information/latest-information.component';
import { ListMedicinesComponent } from './components/list-medicines/list-medicines.component';
import { SosRequestComponent } from './components/sos-request/sos-request.component';
import { UpdateMedicineComponent } from './components/update-medicine/update-medicine.component';

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
  },
  {
    path: 'latestInfo',
    component: LatestInformationComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
