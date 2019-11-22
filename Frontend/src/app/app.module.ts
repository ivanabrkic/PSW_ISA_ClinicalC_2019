import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './home';
import { NotFoundComponent } from './modules/general/not-found/not-found.component';
import { LoginComponent } from './home/login';
import { RegisterComponent } from './home/register';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MustMatch} from './_helpers/MustMatch';

import {AdministratorKlinikeComponent} from './modules/objects/administratorklinike/administrator_klinike.component';
import {RouterModule} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule } from '@angular/material';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {SidebarComponent} from './modules/objects/administratorklinike/sidebar.component';
import { RegistracijaKlinikeComponent } from './modules/general/register/registracija-klinike/registracija-klinike.component';
import { SidebarAdminkcComponent } from './modules/objects/adminkc/sidebar-adminkc.component';
import { AdminkcComponent } from './modules/objects/adminkc/adminkc.component';
import { RegistracijaAdministratoraKlinikeComponent } from './modules/general/register/registracija-administratora-klinike/registracija-administratora-klinike.component';
import {SidebarLekarComponent} from './modules/objects/lekar/sidebarLekar.component';
import {LekarComponent} from './modules/objects/lekar/lekar.component';
import {LekarPregledComponent} from './modules/objects/lekar/lekarPregled.component';
import {AdministratorKlinikePregledComponent} from './modules/objects/administratorklinike/administratorKlinikePregled.component';

import {PacijentComponent} from './modules/objects/pacijent/pacijent.component';
import {SidebarPacijentComponent} from './modules/objects/pacijent/sidebar.component';
import {PacijentPregledComponent} from './modules/objects/pacijent/PacijentPregled.component';

import { ProfilMedSestraComponent } from './modules/objects/medicinskas/profil-med-sestra.component';
import { PrikazPacijenataTabelaComponent } from './modules/shared/prikaz-pacijenata-tabela/prikaz-pacijenata-tabela.component';
import { SidebarMedSestraComponent } from './modules/objects/medicinskas/sidebar-med-sestra.component';
import { OdmorComponent } from './modules/objects/medicinskas/odmor/odmor.component';
import { OdsustvoComponent } from './modules/objects/medicinskas/odsustvo/odsustvo.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NotFoundComponent,
    LoginComponent,
    RegisterComponent,
    AdministratorKlinikeComponent,
    SidebarComponent,
    RegistracijaKlinikeComponent,
    SidebarAdminkcComponent,
    AdminkcComponent,
    RegistracijaAdministratoraKlinikeComponent,
    LekarComponent,
    SidebarLekarComponent,
    LekarPregledComponent,
    AdministratorKlinikePregledComponent,
    SidebarComponent,
    PacijentComponent,
    SidebarPacijentComponent,
    PacijentPregledComponent,
    ProfilMedSestraComponent,
    PrikazPacijenataTabelaComponent,
    SidebarMedSestraComponent,
    OdmorComponent,
    OdsustvoComponent,
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    AngularFontAwesomeModule,
    HttpClientModule,
    RouterModule.forRoot([{ path: 'administratorklinike', component: AdministratorKlinikeComponent},
      { path: 'administratorKc', component: AdminkcComponent},
      { path: 'registracijaKlinike', component: RegistracijaKlinikeComponent},
      { path: 'registracijaAdminKlinike', component: RegistracijaAdministratoraKlinikeComponent},
      { path: 'medicinskaSestra', component: ProfilMedSestraComponent},
      { path: 'prikazPacijenata', component: PrikazPacijenataTabelaComponent},
      { path: 'lekar', component: LekarComponent},
      { path: 'odmor', component: OdmorComponent},
      { path: 'odsustvo', component: OdsustvoComponent},
      { path: 'sidebarMedSestra', component: SidebarMedSestraComponent},
      { path: 'lekarPregled', component: LekarPregledComponent},
      { path: 'administratorPregled', component: AdministratorKlinikePregledComponent},
      {path: 'pacijentPregled', component: PacijentPregledComponent},
      {path: 'pacijent', component: PacijentComponent},
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'welcome', component: HomeComponent},
      { path: '', redirectTo: 'welcome', pathMatch: 'full'},
      { path: '**', component: NotFoundComponent}])
  ],
  providers: [RegisterComponent,LoginComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
