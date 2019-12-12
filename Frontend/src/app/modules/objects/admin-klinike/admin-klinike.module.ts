import { NgModule } from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule } from '@angular/material';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { SidebarAdminKlinikeComponent } from './sidebar-admin-klinike.component';
import { AdminKlinikeIzmenaComponent } from './admin-klinike-izmena.component';
import { AdminKlinikePregledComponent } from './admin-klinike-pregled.component';
import { AdminKlinikeService } from 'src/app/_services/admin-klinike-service/admin-klinike.service';

@NgModule({
  declarations: [SidebarAdminKlinikeComponent, AdminKlinikeIzmenaComponent, AdminKlinikePregledComponent],
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
    RouterModule.forRoot([{ path: 'administratorklinikepregled', component: AdminKlinikePregledComponent},
                          { path: 'administratorklinikeizmena', component: AdminKlinikeIzmenaComponent},
  ])
  ],
  providers: [AdminKlinikeService],
  exports:   [SidebarAdminKlinikeComponent, AdminKlinikeIzmenaComponent, AdminKlinikePregledComponent],
  bootstrap: [SidebarAdminKlinikeComponent, AdminKlinikeIzmenaComponent, AdminKlinikePregledComponent]
})
export class AdminKlinikeModule { }
