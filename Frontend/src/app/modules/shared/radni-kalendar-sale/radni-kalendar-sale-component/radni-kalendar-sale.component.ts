import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { View, PopupOpenEventArgs, ScheduleComponent, EventSettingsModel } from '@syncfusion/ej2-angular-schedule';
import { loadCldr, L10n } from "@syncfusion/ej2-base";
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Operacija } from 'src/app/models/operacija/operacija';
import { KlinikaService } from 'src/app/modules/shared/services/klinika-service/klinika.service';
import { Lekar } from 'src/app/models/lekar/lekar';
import { Pregled } from 'src/app/models/pregled/pregled';

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

  public data: object[] = []

  public eventSettings: EventSettingsModel = { dataSource: this.data };

  public selectedEvent: Event;

  public operacije: Operacija[]
  public pregledi : Pregled[]

  constructor(private dialogRef: MatDialogRef<RadniKalendarSaleComponent>,
    @Inject(MAT_DIALOG_DATA) data, private klinikaService: KlinikaService) {
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
            title: "naslov",
            saveButton: 'Sačuvaj',
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

    this.operacije = data.operacije
    this.pregledi = data.pregledi

  }

  ngOnInit() {

    this.dialogRef.updatePosition({
      top: '2%',
      left: '20%'
    });

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
          saveButton: 'Sačuvaj',
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

    if (this.operacije) {

      var i

      for (i = 0; i < this.operacije.length; i++) {

        this.operacije[i].godina = parseInt(this.operacije[i].datum.split('.')[2])
        this.operacije[i].mesec = parseInt(this.operacije[i].datum.split('.')[1]) - 1
        this.operacije[i].dan = parseInt(this.operacije[i].datum.split('.')[0])
        this.operacije[i].sat1 = parseInt(this.operacije[i].pocetak.split(':')[0])
        this.operacije[i].minut1 = parseInt(this.operacije[i].pocetak.split(':')[1])
        this.operacije[i].sat2 = parseInt(this.operacije[i].kraj.split(':')[0])
        this.operacije[i].minut2 = parseInt(this.operacije[i].kraj.split(':')[1])

        this.data.push({
          Id: i,
          Subject: 'Operacija',
          EventType: this.operacije[i].jboPacijenta,
          Description: this.operacije[i].jboLekara,
          StartTime: new Date(this.operacije[i].godina, this.operacije[i].mesec, this.operacije[i].dan, this.operacije[i].sat1, this.operacije[i].minut1),
          EndTime: new Date(this.operacije[i].godina, this.operacije[i].mesec, this.operacije[i].dan, this.operacije[i].sat2, this.operacije[i].minut2),
          IsAllDay: false
        })
      }

    }

    if (this.pregledi) {

      var i

      for (i = 0; i < this.pregledi.length; i++) {

        this.pregledi[i].godina = parseInt(this.pregledi[i].datum.split('.')[2])
        this.pregledi[i].mesec = parseInt(this.pregledi[i].datum.split('.')[1]) - 1
        this.pregledi[i].dan = parseInt(this.pregledi[i].datum.split('.')[0])
        this.pregledi[i].sat1 = parseInt(this.pregledi[i].pocetak.split(':')[0])
        this.pregledi[i].minut1 = parseInt(this.pregledi[i].pocetak.split(':')[1])
        this.pregledi[i].sat2 = parseInt(this.pregledi[i].kraj.split(':')[0])
        this.pregledi[i].minut2 = parseInt(this.pregledi[i].kraj.split(':')[1])

        this.data.push({
          Id: i,
          Subject: 'Pregled',
          EventType: this.pregledi[i].jboPacijenta,
          Description: this.pregledi[i].jboLekara,
          StartTime: new Date(this.pregledi[i].godina, this.pregledi[i].mesec, this.pregledi[i].dan, this.pregledi[i].sat1, this.pregledi[i].minut1),
          EndTime: new Date(this.pregledi[i].godina, this.pregledi[i].mesec, this.pregledi[i].dan, this.pregledi[i].sat2, this.pregledi[i].minut2),
          IsAllDay: false
        })
      }

    }

  }

  onEventClick(args: any): void {
    this.selectedEvent = args.event;
  }

  onPopupOpen(args: PopupOpenEventArgs): void {
    if (args.type === 'DeleteAlert') {
      args.cancel = true;
      this.scheduleObj.deleteEvent((args.data as any).Id);
    }
    else if (args.type === 'Editor') {
      args.cancel = true;
    }

    if ((!args.target.classList.contains('e-appointment') && (args.type === 'QuickInfo'))) {
      args.cancel = true;
    }
  }

  public statusFields: Object = { text: 'StatusText', value: 'StatusText' };
  public StatusData: Object[] = [
    { StatusText: 'New', Id: 1 },
    { StatusText: 'Requested', Id: 2 },
    { StatusText: 'Confirmed', Id: 3 }
  ];
  public dateParser(data: string) {
    return new Date(data);
  }

  public onCloseClick(): void {
    this.scheduleObj.quickPopup.quickPopupHide();
  }

}
