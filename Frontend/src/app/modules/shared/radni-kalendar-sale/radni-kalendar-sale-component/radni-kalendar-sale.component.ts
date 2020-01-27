import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { View, PopupOpenEventArgs, ScheduleComponent, EventSettingsModel } from '@syncfusion/ej2-angular-schedule';
import { loadCldr, L10n } from "@syncfusion/ej2-base";
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Operacija } from 'src/app/models/operacija/operacija';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { Lekar } from 'src/app/models/lekar/lekar';

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
  
//   public data: object [] = [{
//     Id: 3,
//     Subject: 'Pregled',
//     EventType: '123456789',
//     Description: '111111111',
//     StartTime: new Date(2020, 0, 11, 9, 0),
//     EndTime: new Date(2020, 0, 11, 10, 0),
//     IsAllDay: false
//     },{
//     Id: 4,
//     Subject: 'Operacija',
//     EventType: '1235654432',
//     Description: '4234324234',
//     StartTime: new Date(2020, 0, 13, 9, 0),
//     EndTime: new Date(2020, 0, 13, 10, 0),
//     IsAllDay: false
// }];
  public data: object [] = []

  public eventSettings: EventSettingsModel = { dataSource: this.data };

  public selectedEvent : Event;

  public operacije : Operacija[]

  constructor(private dialogRef: MatDialogRef<RadniKalendarSaleComponent>,
    @Inject(MAT_DIALOG_DATA) data, private klinikaService:KlinikaService) {
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

    
    var operacije = data.operacije
    var pregledi = data.pregledi

    if (data.operacije != undefined){

    var i

    for (i = 0; i < operacije.length; i++) {
      var operacija : Operacija = new Operacija()

      operacija.id = i
      operacija.pacijent = operacije[i].pacijent.jbo

      this.klinikaService.getLekariOperacije(operacije[i])
      .subscribe(data => {
        var l
        for(l in data){
          operacija.lekari.push(l.jbo)
        } 
      });

      operacija.godina = operacije[i].id.datum.split('.')[2]
      operacija.mesec = parseInt(operacije[i].id.datum.split('.')[1]) - 1
      operacija.dan = operacije[i].id.datum.split('.')[0]
      operacija.sat1 = operacije[i].pocetak.split(':')[0]
      operacija.minut1 = operacije[i].pocetak.split(':')[1]
      operacija.sat2 = operacije[i].kraj.split(':')[0]
      operacija.minut2 = operacije[i].kraj.split(':')[1]

      this.operacije.push(operacija);
    }

    for (i = 0; i < this.operacije.length; i++) {
      this.data.push({
        Id : i,
        Subject : 'Operacija',
        EventType : operacije[0].pacijent,
        Description : operacije[0].lekari,
        StartTime: new Date(operacije[0].godina, operacije[0].mesec, operacije[0].dan, operacije[0].sat1, operacije[0].minut1),
        EndTime: new Date(operacije[0].godina, operacije[0].mesec, operacije[0].dan, operacije[0].sat2, operacije[0].minut2),
        IsAllDay: false
      })
    }
  }


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
  }

  onEventClick(args: any): void {
    this.selectedEvent = args.event;
  }

  onPopupOpen(args: PopupOpenEventArgs): void {
    if (args.type === 'DeleteAlert')  {
        args.cancel = true;
        this.scheduleObj.deleteEvent((args.data as any).Id);
    }
    else if (args.type === 'Editor'){
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
