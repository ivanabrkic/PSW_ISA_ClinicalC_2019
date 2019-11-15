import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './home';
import { NotFoundComponent } from './modules/general/not-found/not-found.component';
<<<<<<< HEAD
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './home/login';
import { RegisterComponent } from './home/register';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MustMatch} from './_helpers/MustMatch';

=======
import {AdministratorKlinikeComponent} from './modules/objects/administratorklinike/administrator_klinike.component';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule } from '@angular/material';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {SidebarComponent} from './modules/objects/administratorklinike/sidebar.component';
>>>>>>> 990d238f00c1b135dcb6ae3a06146fac630e9934

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NotFoundComponent,
<<<<<<< HEAD
    LoginComponent,
    RegisterComponent,
=======
    AdministratorKlinikeComponent,
    SidebarComponent
>>>>>>> 990d238f00c1b135dcb6ae3a06146fac630e9934
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  imports: [
    BrowserModule,
<<<<<<< HEAD
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
=======
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    AngularFontAwesomeModule,
    RouterModule.forRoot([{ path: 'administratorklinike', component: AdministratorKlinikeComponent},
      { path: 'welcome', component: HomeComponent},
      { path: '', redirectTo: 'welcome', pathMatch: 'full'},
      { path: '**', component: NotFoundComponent}])
>>>>>>> 990d238f00c1b135dcb6ae3a06146fac630e9934
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
