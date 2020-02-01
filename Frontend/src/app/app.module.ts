import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home';
import { LoginComponent } from './home/login';
import { RegisterComponent } from './home/register';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MustMatch} from './helpers/MustMatch';

import {RouterModule} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule, MatDatepickerModule } from '@angular/material';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import { RegistracijaKlinikeComponent } from './modules/objects/klinika/registracija-klinike/registracija-klinike.component';
import { SidebarAdminkcComponent } from './modules/objects/adminkc/sidebar-adminkc.component';
import { AdminkcComponent } from './modules/objects/adminkc/adminkc.component';
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
import {ListaKlinikaComponent} from './modules/shared/lista-klinika/lista-klinikaComponent';
import {ZdravstveniKartonComponent} from './modules/shared/zdravstveni-karton/zdravstveni-kartonComponent';
import { KreiranjeDijagnozaComponent } from './modules/objects/adminkc/kreiranje-dijagnoza/kreiranje-dijagnoza.component';
import { PoseteComponent } from './modules/shared/pregledi-operacije-lista/pregledi-operacije-lista';
import { AdminKlinikeModule } from './modules/objects/admin-klinike/admin-klinike.module';
import { DijalogOdbijanjeZahtevaComponent } from './modules/objects/adminkc/dijalog-odbijanje-zahteva/dijalog-odbijanje-zahteva.component';
import {MatDialogModule} from '@angular/material/dialog';
import { KreiranjeLekovaComponent } from './modules/objects/adminkc/kreiranje-lekova/kreiranje-lekova.component';
import { NotFoundComponent } from './home/not-found/not-found.component';
import { KlinikaService } from './modules/shared/services/klinika-service/klinika.service';
import { LoginService } from './modules/shared/services/login-and-register-service/login.service';
import { RegisterService } from './modules/shared/services/login-and-register-service/register.service';
import { RegistracijaAdministratoraKlinikeComponent } from './modules/objects/adminkc/registracija-administratora-klinike/registracija-administratora-klinike.component';
import { AdminKlinikeService } from './modules/shared/services/admin-klinike-service/admin-klinike.service';
import { ZdravstveniKartonService } from './modules/shared/services/zdravstveni-karton-service/zdravstveni-karton.service';
import { PacijentService } from './modules/shared/services/pacijent-service/pacijent.service';
import { PoseteService } from './modules/shared/services/posete-service/posete.service';
import { RadniKalendarSaleModule } from './modules/shared/radni-kalendar-sale/radni-kalendar-sale.module';
import { PromenaSifreComponent } from './modules/shared/promena-sifre/promena-sifre.component';

import { OveraRecepataComponent } from './modules/objects/medicinskas/overa-recepata/overa-recepata.component';

// search module
import { Ng2SearchPipeModule } from 'ng2-search-filter'
import { PretragafilterFilter } from './helpers/filter';
import { ListaLekaraComponent } from './modules/shared/lista-lekara/lista-lekaraComponent';
import { ProfilKlinikeComponent } from './modules/shared/profil-klinike/profil-klinikeComponent';
import { PretragafilterLekari } from './helpers/filterLekari';
import { MatNativeDateModule } from '@angular/material';


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
    KreiranjeDijagnozaComponent,
    PoseteComponent,
    ZdravstveniKartonComponent,
    KreiranjeDijagnozaComponent,
    DijalogOdbijanjeZahtevaComponent,
    KreiranjeLekovaComponent,
    PromenaSifreComponent,
    OveraRecepataComponent,
    ListaLekaraComponent,
    PretragafilterFilter,
    ProfilKlinikeComponent,
    PretragafilterLekari
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  imports: [
    Ng2SearchPipeModule,
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
    MatDialogModule,
    MatDatepickerModule,
    CommonModule,
    MatDatepickerModule,
    RouterModule.forRoot([
      { path: 'administratorKc', component: AdminkcComponent},
      { path: 'adminkcIzmena', component: AdminkcIzmenaComponent},
      { path: 'registracijaKlinike', component: RegistracijaKlinikeComponent},
      { path: 'registracijaAdminKlinike', component: RegistracijaAdministratoraKlinikeComponent},
      { path: 'medicinskaSestra', component: ProfilMedSestraComponent},
      { path: 'medicinskaSestraIzmena', component: MedSestraIzmenaComponent},
      { path: 'prikazPacijenata', component: PrikazPacijenataTabelaComponent},
      { path: 'lekar', component: LekarComponent},
      { path: 'odbijanjeObrazlozenje', component: DijalogOdbijanjeZahtevaComponent},
      { path: 'zahteviRegistracija', component: ZahteviRegistracijaComponent},
      { path: 'odmor', component: OdmorComponent},
      { path: 'promenaLozinke', component: PromenaSifreComponent},
      { path: 'overaRecepta', component: OveraRecepataComponent},
      { path: 'odsustvo', component: OdsustvoComponent},
      { path: 'sidebarMedSestra', component: SidebarMedSestraComponent},
      { path: 'lekarPregled', component: LekarPregledComponent},
      {path: 'pacijentPregled', component: PacijentPregledComponent},
      {path: 'pacijent', component: PacijentComponent},
      { path: 'login', component: LoginComponent },
      { path: 'kreiranjeDijagnoza', component: KreiranjeDijagnozaComponent },
      { path: 'kreiranjeLekova', component: KreiranjeLekovaComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'welcome', component: HomeComponent},
      { path: '', redirectTo: 'welcome', pathMatch: 'full'},
      { path: 'listaKlinika', component: ListaKlinikaComponent},
      {path: 'zdravstveniKarton', component: ZdravstveniKartonComponent},
       {path: 'listaPoseta', component: PoseteComponent},
       {path: 'listaLekara', component: ListaLekaraComponent},
       {path: 'profilKlinike', component: ProfilKlinikeComponent},
      { path: '**', component: NotFoundComponent},
      ])
  ],
  providers: [AdminKlinikeService, KlinikaService, LoginService, RegisterService, ZdravstveniKartonService,PacijentService,PoseteService],

  bootstrap: [AppComponent]
})
export class AppModule { }
