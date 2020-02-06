import { NgModule } from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCheckboxModule, MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule, MatDialogModule, MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatListModule, MatCardModule, MatTabsModule, MatStepperModule, MatSnackBarModule, MatButtonModule } from '@angular/material';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { SidebarAdminKlinikeComponent } from './sidebar-admin-klinike/sidebar-admin-klinike.component';
import { AdminKlinikeIzmenaComponent } from './admin-klinike-izmena/admin-klinike-izmena.component';
import { AdminKlinikePregledComponent } from './admin-klinike-pregled/admin-klinike-pregled.component';
import { IzmenaProfilKlinikeComponent } from './izmena-profil-klinike/izmena-profil-klinike.component';
import { RegistracijaMedicinskogOsobljaComponent } from './registracija-medicinskog-osoblja/registracija-medicinskog-osoblja.component';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { TabelaMedicinskogOsobljaComponent } from './tabela-medicinskog-osoblja/tabela-medicinskog-osoblja.component';
import { DetaljiComponent } from './detalji/detalji.component';
import { MatIconModule} from '@angular/material/icon';
import { PregledSalaComponent } from './pregled-sala/pregled-sala.component';
import { RadniKalendarSaleModule } from '../../shared/radni-kalendar-sale/radni-kalendar-sale.module';
import { RegistracijaSalaComponent } from './registracija-sala/registracija-sala.component';
import { ZahteviOperacijePreglediComponent } from './zahtevi-operacije-pregledi/zahtevi-operacije-pregledi.component';
import { IzborSaleComponent } from './izbor-sale/izbor-sale.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ZakaziTerminComponent } from './zakazi-termin/zakazi-termin.component';
import { MapaComponent } from './mapa/mapa.component';
import { AgmCoreModule } from '@agm/core';
import { TipoviPregledaComponent } from './tipovi-pregleda/tipovi-pregleda.component';
import { RegistracijaTipovaComponent } from './registracija-tipova/registracija-tipova.component';
import { PredefinisaniTerminiComponent } from './predefinisani-termini/predefinisani-termini.component';
import { PredefTerminiServiceService } from 'src/app/services/predefTermini-service/predef-termini-service.service';

@NgModule({
  declarations: [SidebarAdminKlinikeComponent,
    AdminKlinikeIzmenaComponent,
    AdminKlinikePregledComponent,
    IzmenaProfilKlinikeComponent,
    RegistracijaMedicinskogOsobljaComponent,
    TabelaMedicinskogOsobljaComponent,
    DetaljiComponent,
    PregledSalaComponent,
    RegistracijaSalaComponent,
    ZahteviOperacijePreglediComponent,
    IzborSaleComponent,
    ZakaziTerminComponent,
    MapaComponent,
    TipoviPregledaComponent,
    RegistracijaTipovaComponent,
    PredefinisaniTerminiComponent
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
    ReactiveFormsModule,
    MatDialogModule,
    MatSelectModule,
    MatCheckboxModule,
    MatIconModule,
    RadniKalendarSaleModule,
    MatDatepickerModule,
    NgbModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatListModule,
    MatCardModule,
    MatTabsModule,
    MatStepperModule,
    MatSnackBarModule,
    MatButtonModule,
    MatPaginatorModule,
    RouterModule.forRoot([{ path: 'administratorklinikepregled', component: AdminKlinikePregledComponent},
      { path: 'administratorklinikeizmena', component: AdminKlinikeIzmenaComponent},
      { path: 'izmenaprofilklinike', component: IzmenaProfilKlinikeComponent},
      { path: 'medicinskoosoblje', component: TabelaMedicinskogOsobljaComponent},
      { path: 'sale', component: PregledSalaComponent},
      {path: 'zahtevi', component: ZahteviOperacijePreglediComponent},
      {path: 'tipovi', component:TipoviPregledaComponent},
      {path: 'predefinisani', component:PredefinisaniTerminiComponent}
    ]),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBcBUQxfS6JldNG0Ltoju5YxE_0-CKJsu4',
      libraries: ['places']
    })
  ],
  entryComponents: [
    DetaljiComponent,
    RegistracijaMedicinskogOsobljaComponent,
    RegistracijaSalaComponent,
    ZakaziTerminComponent,
    RegistracijaTipovaComponent
  ],
  providers: [AdminKlinikeService, PredefTerminiServiceService],
  exports:   [SidebarAdminKlinikeComponent,
    AdminKlinikeIzmenaComponent,
    AdminKlinikePregledComponent,
    IzmenaProfilKlinikeComponent,
    TabelaMedicinskogOsobljaComponent],
  bootstrap: [SidebarAdminKlinikeComponent,
    AdminKlinikeIzmenaComponent,
    AdminKlinikePregledComponent,
    IzmenaProfilKlinikeComponent,
    TabelaMedicinskogOsobljaComponent]
})
export class AdminKlinikeModule { }
