import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UploadContentComponent } from './upload-content/upload-content.component';
import { ContentService } from './service/content.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { ViewContentComponent } from './view-content/view-content.component';
import { HomeComponent } from './home/home.component';
import { AllContentsComponent } from './home/all-contents/all-contents/all-contents.component';
//import { GoogleLoginProvider, SocialAuthServiceConfig, SocialLoginModule } from 'angularx-social-login';
//the above module is not working bcz of some version issue
@NgModule({
  declarations: [
    AppComponent,
    UploadContentComponent,
    LoginComponent,
    RegistrationComponent,
    LandingPageComponent,
    ViewContentComponent,
    HomeComponent,
    AllContentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    //SocialLoginModule
  ],
  providers: [ /*{
    provide: 'SocialAuthServiceConfig',
    useValue: {
      autoLogin: false,
      providers: [
        {
          id: GoogleLoginProvider.PROVIDER_ID,
          provider: new GoogleLoginProvider(
            '452695492932-ge8hm7bqvao281vg4p33i1ombimh33vu.apps.googleusercontent.com' // add web app client id
          )
        }
      ]
    } as SocialAuthServiceConfig
  },*/
  ContentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
