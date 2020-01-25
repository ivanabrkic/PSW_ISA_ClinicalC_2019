import { NgModule } from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCheckboxModule, MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule, MatDialogModule, MatSelectModule } from '@angular/material';
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

@NgModule({
  declarations: [SidebarAdminKlinikeComponent, 
                AdminKlinikeIzmenaComponent, 
                AdminKlinikePregledComponent,
                IzmenaProfilKlinikeComponent,
                RegistracijaMedicinskogOsobljaComponent,
                TabelaMedicinskogOsobljaComponent,
                DetaljiComponent,
                PregledSalaComponent,
                RegistracijaSalaComponent],
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
    RouterModule.forRoot([{ path: 'administratorklinikepregled', component: AdminKlinikePregledComponent},
                          { path: 'administratorklinikeizmena', component: AdminKlinikeIzmenaComponent},
                          { path: 'izmenaprofilklinike', component: IzmenaProfilKlinikeComponent},
                          { path: 'medicinskoosoblje', component: TabelaMedicinskogOsobljaComponent},
                          { path: 'sale', component: PregledSalaComponent}
  ])
  ],
  entryComponents: [
    DetaljiComponent,
    RegistracijaMedicinskogOsobljaComponent,
    RegistracijaSalaComponent
  ],
  providers: [AdminKlinikeService],
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
