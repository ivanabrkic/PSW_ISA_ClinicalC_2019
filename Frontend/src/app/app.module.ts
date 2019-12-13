import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './home';
import { NotFoundComponent } from './modules/general/not-found/not-found.component';
import { LoginComponent } from './home/login';
import { RegisterComponent } from './home/register';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MustMatch} from './_helpers/MustMatch';

import {RouterModule} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule } from '@angular/material';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import { RegistracijaKlinikeComponent } from './modules/objects/klinika/registracija-klinike/registracija-klinike.component';
import { SidebarAdminkcComponent } from './modules/objects/adminkc/sidebar-adminkc.component';
import { AdminkcComponent } from './modules/objects/adminkc/adminkc.component';
import { RegistracijaAdministratoraKlinikeComponent } from './modules/objects/admin-klinike/registracija-administratora-klinike/registracija-administratora-klinike.component';
import {SidebarLekarComponent} from './modules/objects/lekar/sidebarLekar.component';
import {LekarComponent} from './modules/objects/lekar/lekar.component';
import {LekarPregledComponent} from './modules/objects/lekar/lekarPregled.component';

import {PacijentComponent} from './modules/objects/pacijent/pacijent.component';
import {SidebarPacijentComponent} from './modules/objects/pacijent/sidebar.component';
import {PacijentPregledComponent} from './modules/objects/pacijent/PacijentPregled.component';

import { ProfilMedSestraComponent } from './modules/objects/medicinskas/profil-med-sestra.component';
import { PrikazPacijenataTabelaComponent } from './modules/shared/prikaz-pacijenata-tabela/prikaz-pacijenata-tabela.component';
import { SidebarMedSestraComponent } from './modules/objects/medicinskas/sidebar-med-sestra.component';
import { OdmorComponent } from './modules/objects/medicinskas/odmor/odmor.component';
import { OdsustvoComponent } from './modules/objects/medicinskas/odsustvo/odsustvo.component';
import { AdminkcIzmenaComponent } from './modules/objects/adminkc/adminkc-izmena/adminkc-izmena.component';
import { MedSestraIzmenaComponent } from './modules/objects/medicinskas/medsestra-izmena/medsestra-izmena.component';
import { ZahteviRegistracijaComponent } from './modules/objects/adminkc/zahtevi-registracija/zahtevi-registracija.component';
import {KlinikaService} from './_services/KlinikaService/klinika.service';
import {LoginService, RegisterService} from './_services/LoginAndRegister';
import {ListaKlinikaComponent} from './modules/shared/lista-klinika/lista-klinikaComponent';
import {ZdravstveniKartonComponent} from './modules/shared/zdravstveni-karton/zdravstveni-kartonComponent';
import { OdbijanjeObrazlozenjeComponent } from './modules/objects/adminkc/odbijanje-obrazlozenje/odbijanje-obrazlozenje.component';
import { KreiranjeDijagnozaComponent } from './modules/objects/adminkc/kreiranje-dijagnoza/kreiranje-dijagnoza.component';
import { AdminKlinikeModule } from './modules/objects/admin-klinike/admin-klinike.module';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NotFoundComponent,
    LoginComponent,
    RegisterComponent,
    RegistracijaKlinikeComponent,
    SidebarAdminkcComponent,
    AdminkcComponent,
    RegistracijaAdministratoraKlinikeComponent,
    LekarComponent,
    SidebarLekarComponent,
    LekarPregledComponent,
    PacijentComponent,
    SidebarPacijentComponent,
    PacijentPregledComponent,
    ProfilMedSestraComponent,
    PrikazPacijenataTabelaComponent,
    SidebarMedSestraComponent,
    OdmorComponent,
    OdsustvoComponent,
    AdminkcIzmenaComponent,
    MedSestraIzmenaComponent,
    ZahteviRegistracijaComponent,
    ListaKlinikaComponent,
    ZdravstveniKartonComponent,
    OdbijanjeObrazlozenjeComponent,
    KreiranjeDijagnozaComponent  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    AngularFontAwesomeModule,
    HttpClientModule,
    AdminKlinikeModule,
    RouterModule.forRoot([
      { path: 'administratorKc', component: AdminkcComponent},
      { path: 'adminkcIzmena', component: AdminkcIzmenaComponent},
      { path: 'registracijaKlinike', component: RegistracijaKlinikeComponent},
      { path: 'registracijaAdminKlinike', component: RegistracijaAdministratoraKlinikeComponent},
      { path: 'medicinskaSestra', component: ProfilMedSestraComponent},
      { path: 'medicinskaSestraIzmena', component: MedSestraIzmenaComponent},
      { path: 'prikazPacijenata', component: PrikazPacijenataTabelaComponent},
      { path: 'lekar', component: LekarComponent},
      { path: 'odbijanjeObrazlozenje', component: OdbijanjeObrazlozenjeComponent},
      { path: 'zahteviRegistracija', component: ZahteviRegistracijaComponent},
      { path: 'odmor', component: OdmorComponent},
      { path: 'odsustvo', component: OdsustvoComponent},
      { path: 'sidebarMedSestra', component: SidebarMedSestraComponent},
      { path: 'lekarPregled', component: LekarPregledComponent},
      {path: 'pacijentPregled', component: PacijentPregledComponent},
      {path: 'pacijent', component: PacijentComponent},
      { path: 'login', component: LoginComponent },
      { path: 'kreiranjeDijagnoza', component: KreiranjeDijagnozaComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'welcome', component: HomeComponent},
      { path: '', redirectTo: 'welcome', pathMatch: 'full'},
      { path: 'listaKlinika', component: ListaKlinikaComponent},
      {path: 'zdravstveniKarton', component: ZdravstveniKartonComponent},
      { path: '**', component: NotFoundComponent},
      ])
  ],
  providers: [KlinikaService, LoginService, RegisterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
