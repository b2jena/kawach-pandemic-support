import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { SosRequestComponent } from './components/sos-request/sos-request.component';

const routes: Routes = [
  {
    path: '',
    component: AppComponent
  }, {
    path: 'sos',
    component: SosRequestComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
