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
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
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
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSliderModule } from '@angular/material/slider';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { HeaderComponent } from './components/header/header.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Overlay } from '@angular/cdk/overlay';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatExpansionModule  } from '@angular/material/expansion';
import { MatPaginatorModule } from '@angular/material/paginator';
import { FooterComponent } from './components/footer/footer.component';
import { MatTableModule } from '@angular/material/table';
import { FlexLayoutModule } from '@angular/flex-layout';
import { PatientDashboardComponent } from './components/patient-dashboard/patient-dashboard.component';
import { WarroomAddComponent } from './components/warroom-dashboard/warroom-add/warroom-add.component';
import { MatListModule } from '@angular/material/list';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { UploadFileComponent } from './components/upload-file/upload-file.component';
import { GridComponent } from './components/landing-page/grid/grid.component';
import { DoctorCardComponent } from './components/landing-page/grid/doctor-card/doctor-card.component';
import { PatientCardComponent } from './components/landing-page/grid/patient-card/patient-card.component';
import { VolunteerCardComponent } from './components/landing-page/grid/volunteer-card/volunteer-card.component';
import { CarouselComponent } from './components/landing-page/carousel/carousel.component';
import { CloseSosComponent } from './components/close-sos/close-sos.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MaterialFileInputModule } from 'ngx-material-file-input';
import { MatDividerModule } from '@angular/material/divider';
import { HeaderTabsComponent } from './components/header-tabs/header-tabs.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatSidenavModule } from '@angular/material/sidenav';
import { TimeslotComponent } from './components/doctor-dashboard/timeslot/timeslot.component';
import { FeedbackComponent } from './components/doctor-dashboard/feedback/feedback.component';
import { ConsultComponent } from './components/doctor-dashboard/consult/consult.component';
import { WarroomVerifyComponent } from './components/warroom-dashboard/warroom-verify/warroom-verify.component';
import { VerifyBedComponent } from './components/verify/verify-bed/verify-bed.component';
import { VolunteerRevertComponent } from './components/landing-page/volunteer-revert/volunteer-revert.component';

import { DoctorCardsComponent } from './components/patient-dashboard/doctor-cards/doctor-cards.component';

import { ChatComponent } from './components/chat/chat.component';
import { DataService } from './components/chat/service/data.service';
import { VerifyMedicineComponent } from './components/verify/verify-bed/verify-medicine/verify-medicine.component';
import { VerifyEquipmentsComponent } from './components/verify/verify-equipments/verify-equipments.component';
import { WarroomAddBedComponent } from './components/warroom-dashboard/warroom-add-bed/warroom-add-bed.component';
import { WarroomAddMedicineComponent } from './components/warroom-dashboard/warroom-add-medicine/warroom-add-medicine.component';
import { WarroomAddEquipmentComponent } from './components/warroom-dashboard/warroom-add-equipment/warroom-add-equipment.component';
import { MessageComponent } from './components/message/message.component';
import { MsgComponent } from './components/message/msg/msg.component';
import { MessageService } from './services/message.service';
import { DoctorMessageComponent } from './components/doctor-message/doctor-message.component';
import {NgxAutoScrollModule} from 'ngx-auto-scroll';
import { CloseSosBedComponent } from './components/close-sos-bed/close-sos-bed.component';
import { CloseSosEquipmentComponent } from './components/close-sos-equipment/close-sos-equipment.component';
import { WarroomSosMedicineComponent } from './components/warroom-dashboard/warroom-sos-medicine/warroom-sos-medicine.component';
import { WarroomSosBedComponent } from './components/warroom-dashboard/warroom-sos-bed/warroom-sos-bed.component';
import { WarroomSosEquipmentComponent } from './components/warroom-dashboard/warroom-sos-equipment/warroom-sos-equipment.component';
import { WarroomVerifyBedComponent } from './components/warroom-dashboard/warroom-verify-bed/warroom-verify-bed.component';
import { WarroomVerifyMedicineComponent } from './components/warroom-dashboard/warroom-verify-medicine/warroom-verify-medicine.component';
import { WarroomVerifyEquipmentComponent } from './components/warroom-dashboard/warroom-verify-equipment/warroom-verify-equipment.component';
import { SearchDialogComponent } from './components/search-dialog/search-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';


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
    FooterComponent,
    PatientDashboardComponent,
    WarroomAddComponent,
    UploadFileComponent,
    GridComponent,
    DoctorCardComponent,
    PatientCardComponent,
    VolunteerCardComponent,
    CarouselComponent,
    CloseSosComponent,
    HeaderTabsComponent,
    TimeslotComponent,
    FeedbackComponent,
    ConsultComponent,
    WarroomVerifyComponent,
    VerifyBedComponent,
    VolunteerRevertComponent,
    ChatComponent,
    VerifyMedicineComponent,
    VerifyEquipmentsComponent,
    DoctorCardsComponent,
    ChatComponent,
    WarroomAddBedComponent,
    WarroomAddMedicineComponent,
    WarroomAddEquipmentComponent,
    MessageComponent,
    MsgComponent,
    DoctorMessageComponent,
    CloseSosBedComponent,
    CloseSosEquipmentComponent,
    WarroomSosMedicineComponent,
    WarroomSosBedComponent,
    WarroomSosEquipmentComponent,
    WarroomVerifyBedComponent,
    WarroomVerifyMedicineComponent,
    WarroomVerifyEquipmentComponent,
    SearchDialogComponent
  ],
  imports: [
    NgxAutoScrollModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatAutocompleteModule,
    FormsModule,
    MatTabsModule,
    BrowserAnimationsModule,
    MaterialFileInputModule,
    MatDividerModule,
    MatSnackBarModule,
    MatFormFieldModule,
    MatToolbarModule,
    ReactiveFormsModule,
    MatSliderModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatCardModule,
    MatSelectModule,
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
    MatPaginatorModule,
    MatListModule,
    MatFormFieldModule,
    MatCardModule,
    MatPaginatorModule,
    MatGridListModule,
    MatSidenavModule,
    MatSelectModule,
    MatToolbarModule,
    MatSidenavModule,
    MatDialogModule
  ],
  providers: [HttpClient, SMedicineService, UserRegistrationService, EquipmentService, BedService, SosService, MatSnackBarModule,
  MatSnackBar, Overlay, DataService, MessageService],

  bootstrap: [AppComponent]
})
export class AppModule { }
