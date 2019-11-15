import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './modules/general/home/home.component';
import { NotFoundComponent } from './modules/general/not-found/not-found.component';
import {AdministratorKlinikeComponent} from './modules/objects/administratorklinike/administrator_klinike.component';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule } from '@angular/material';
import { SidebarComponent } from './modules/objects/administratorklinike/sidebar.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { RegistracijaKlinikeComponent } from './modules/general/register/registracija-klinike/registracija-klinike.component';
import { SidebarAdminkcComponent } from './modules/objects/adminkc/sidebar-adminkc.component';
import { AdminkcComponent } from './modules/objects/adminkc/adminkc.component';
import { RegistracijaAdministratoraKlinikeComponent } from './modules/general/register/registracija-administratora-klinike/registracija-administratora-klinike.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NotFoundComponent,
    AdministratorKlinikeComponent,
    SidebarComponent,
    RegistracijaKlinikeComponent,
    SidebarAdminkcComponent,
    AdminkcComponent,
    RegistracijaAdministratoraKlinikeComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    AngularFontAwesomeModule,
    RouterModule.forRoot([{ path: 'administratorklinike', component: AdministratorKlinikeComponent},
      { path: 'administratorKc', component: AdminkcComponent},
      { path: 'registracijaKlinike', component: RegistracijaKlinikeComponent},
      { path: 'registracijaAdminKlinike', component: RegistracijaAdministratoraKlinikeComponent},
      { path: 'welcome', component: HomeComponent},
      { path: '', redirectTo: 'welcome', pathMatch: 'full'},
      { path: '**', component: NotFoundComponent}])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
