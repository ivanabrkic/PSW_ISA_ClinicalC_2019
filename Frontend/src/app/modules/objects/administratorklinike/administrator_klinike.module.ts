import { NgModule } from '@angular/core';

import {SidebarComponent} from './sidebar.component';
import { AdministratorKlinikeComponent } from './administrator_klinike.component';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [SidebarComponent],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AdministratorKlinikeComponent]
})
export class AdministratorKlinikeModule { }
