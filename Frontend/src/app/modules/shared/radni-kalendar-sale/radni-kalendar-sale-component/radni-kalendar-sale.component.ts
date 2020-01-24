import { Component, OnInit, ViewChild } from '@angular/core';
import { View, PopupOpenEventArgs, ScheduleComponent } from '@syncfusion/ej2-angular-schedule';
import { loadCldr, L10n } from "@syncfusion/ej2-base";

declare var require: any;

@Component({
  selector: 'app-radni-kalendar-sale',
  templateUrl: './radni-kalendar-sale.component.html',
  styleUrls: ['./radni-kalendar-sale.component.css']
})

export class RadniKalendarSaleComponent implements OnInit {

  public setView: View = 'Month';
  @ViewChild('scheduleObj', { static: true })
  public scheduleObj: ScheduleComponent;

  constructor() {
    loadCldr(
      require('cldr-data/supplemental/numberingSystems.json'),
      require('cldr-data/main/sr-Latn/ca-gregorian.json'),
      require('cldr-data/main/sr-Latn/numbers.json'),
      require('cldr-data/main/sr-Latn/timeZoneNames.json'),
      require('cldr-data/supplemental/weekdata.json')); // To load the culture based first day of week

    L10n.load({
      'sr-Latn': {
        'schedule': {
          today: "danas",
          day: "dnevni prikaz",
          month: "mesečni prikaz",
          week: "nedeljni prikaz",

        },
        'calendar': {
          today: "danas",
        }
      }
    });

    
  }

  ngOnInit() {
    loadCldr(
      require('cldr-data/supplemental/numberingSystems.json'),
      require('cldr-data/main/sr/ca-gregorian.json'),
      require('cldr-data/main/sr/numbers.json'),
      require('cldr-data/main/sr/timeZoneNames.json'),
      require('cldr-data/supplemental/weekdata.json')); // To load the culture based first day of week

    L10n.load({
      'sr-Latn': {
        'schedule': {
          today: "danas",
          day: "dnevni prikaz",
          month: "mesečni prikaz",
          week: "nedeljni prikaz",
          title: "naslov",
          saveButton: 'Dodaj',
          cancelButton: 'Zatvori',
          deleteButton: 'Ukloni',
          newEvent: 'Dodaj termin',
          editEvent: "Izmeni termin"
        },
        'calendar': {
          today: "danas",
        }
      }
    });
  }

  onEventClick(args: any): void {
    //alert(JSON.stringify(args.event))
  }

  onPopupOpen(args: PopupOpenEventArgs): void {
    if (args.type === 'DeleteAlert')  {
        args.cancel = true;
        this.scheduleObj.deleteEvent((args.data as any).Id);
    }
    else if (args.type === 'QuickInfo'){
        args.cancel = true;
    }
  }

  public selectedDate: Date = new Date(2018, 1, 15);
  public showQuickInfo: boolean = false;
  public statusFields: Object = { text: 'StatusText', value: 'StatusText' };
  public StatusData: Object[] = [
    { StatusText: 'New', Id: 1 },
    { StatusText: 'Requested', Id: 2 },
    { StatusText: 'Confirmed', Id: 3 }
  ];
  public dateParser(data: string) {
    return new Date(data);
  }

}
