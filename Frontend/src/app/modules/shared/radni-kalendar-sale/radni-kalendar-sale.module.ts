import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RadniKalendarSaleComponent } from './radni-kalendar-sale-component/radni-kalendar-sale.component';
import { BrowserModule } from '@angular/platform-browser';
import { ScheduleModule, RecurrenceEditorModule, DayService, WeekService, WorkWeekService, MonthService, MonthAgendaService } from '@syncfusion/ej2-angular-schedule';
import { DropDownListModule } from '@syncfusion/ej2-angular-dropdowns';
import { DateTimePickerModule } from '@syncfusion/ej2-angular-calendars';
import { MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule, MatDialogModule, MatSelectModule, MatCheckboxModule, MatIconModule } from '@angular/material';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [RadniKalendarSaleComponent],
  imports: [
    BrowserModule,
    CommonModule,
    ScheduleModule, 
    RecurrenceEditorModule,
    DropDownListModule, 
    DateTimePickerModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    AngularFontAwesomeModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatSelectModule, 
    MatCheckboxModule,
    MatIconModule
  ],
  providers: [DayService, WeekService, WorkWeekService, MonthService, MonthAgendaService],
  exports: [RadniKalendarSaleComponent],
  bootstrap: [RadniKalendarSaleComponent]
})
export class RadniKalendarSaleModule { }
