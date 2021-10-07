import { ListBedComponent } from './components/list-bed/list-bed.component';
import { AddBedComponent } from './components/add-bed/add-bed.component';
import { ListEquipmentsComponent } from './components/list-equipments/list-equipments.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { AddEquipmentComponent } from './components/add-equipment/add-equipment.component';
import { AddMedicineComponent } from './components/add-medicine/add-medicine.component';
import { LatestInformationComponent } from './components/landing-page/latest-information/latest-information.component';
import { DoctorDashboardComponent } from './components/doctor-dashboard/doctor-dashboard.component';
import { ListMedicinesComponent } from './components/list-medicines/list-medicines.component';
import { LoginComponent } from './components/login/login.component';
import { SosRequestComponent } from './components/sos-request/sos-request.component';
import { UpdateMedicineComponent } from './components/update-medicine/update-medicine.component';
import { DoctorRegistrationComponent } from './components/doctor-registration/doctor-registration.component';
import { VolunteerRegistrationComponent } from './components/volunteer-registration/volunteer-registration.component';
import { WarroomDashboardComponent } from './components/warroom-dashboard/warroom-dashboard.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { PatientDashboardComponent } from './components/patient-dashboard/patient-dashboard.component';
import { WarroomAddComponent } from './components/warroom-dashboard/warroom-add/warroom-add.component';
// import { CloseSosRequestComponent } from './components/close-sos-request/close-sos-request.component';
import { CloseSosComponent } from './components/close-sos/close-sos.component';
import { HeaderTabsComponent } from './components/header-tabs/header-tabs.component';
import { PageOneComponent } from './components/warroom-dashboard/warroom-add/pages/page-one/page-one.component';
import { PageTwoComponent } from './components/warroom-dashboard/warroom-add/pages/page-two/page-two.component';
import { PageThreeComponent } from './components/warroom-dashboard/warroom-add/pages/page-three/page-three.component';
// import { PageOneComponent } from './components/warroom-dashboard/warroom-add/pages/page-one/page-one.component';
import { ChatComponent } from './components/chat/chat.component';

const routes: Routes = [
  {
    path: 'sos',
    component: HeaderTabsComponent
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
    path: 'doctorregistration',
    component: DoctorRegistrationComponent
  }, {
    path: 'volunteerregistration',
    component: VolunteerRegistrationComponent
  },
  {
    path: 'latestInfo',
    component: LatestInformationComponent
  }, {
    path: 'login',
    component: LoginComponent
  }, {
    path: 'doctor-dashboard',
    component: DoctorDashboardComponent
  }, {
    path: 'war-room-dashboard',
    component: WarroomDashboardComponent,
    children: [{
      path: 'war-room-add',
    component: WarroomAddComponent,
    }]
  },
     {
    path: 'addequipment',
    component: AddEquipmentComponent
  }, {
    path: 'listequipment',
    component: ListEquipmentsComponent
  }, {
    path: '',
    component: LandingPageComponent
  }, {
    path: 'addbed',
    component: AddBedComponent
  }, {
    path: 'listbed',
    component: ListBedComponent
  }, {
    path: 'war-room-add',
    component: WarroomAddComponent,
    children: [
      {
        path: 'one', component: PageOneComponent
      },
      {
        path: 'two', component: PageTwoComponent
      }
    ]
  }, {
    path: 'closesos',
    component: CloseSosComponent
  }, {
    path: 'chat',
    component: ChatComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
