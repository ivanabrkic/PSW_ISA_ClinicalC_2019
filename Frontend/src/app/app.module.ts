import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './modules/general/home/home.component';
import { NotFoundComponent } from './modules/general/not-found/not-found.component';
import {AdministratorKlinikeComponent} from './modules/objects/administratorklinike/administrator_klinike.component';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule } from '@angular/material';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {SidebarComponent} from './modules/objects/administratorklinike/sidebar.component';
import {SidebarLekarComponent} from './modules/objects/lekar/sidebarLekar.component';
import {LekarComponent} from './modules/objects/lekar/lekar.component';
import {LekarPregledComponent} from "./modules/objects/lekar/lekarPregled.component";
import {AdministratorKlinikePregledComponent} from "./modules/objects/administratorklinike/administratorKlinikePregled.component";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NotFoundComponent,
    AdministratorKlinikeComponent,
    SidebarComponent,
    LekarComponent,
    SidebarLekarComponent,
    LekarPregledComponent,
    AdministratorKlinikePregledComponent
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
      { path: 'lekar', component: LekarComponent},
      { path: 'lekarPregled', component: LekarPregledComponent},
      { path: 'administratorPregled', component: AdministratorKlinikePregledComponent},
      { path: 'welcome', component: HomeComponent},
      { path: '', redirectTo: 'welcome', pathMatch: 'full'},
      { path: '**', component: NotFoundComponent}])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
