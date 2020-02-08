import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {EventSettingsModel, PopupOpenEventArgs, View} from '@syncfusion/ej2-schedule';
import {ScheduleComponent} from '@syncfusion/ej2-angular-schedule';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {KlinikaService} from '../../../services/klinika-service/klinika.service';
import {DijalogKreiranjeReceptaComponent} from '../dijalog-kreiranje-recepta/dijalog-kreiranje-recepta.component';
import {L10n, loadCldr} from '@syncfusion/ej2-base';
import {Router} from '@angular/router';
import {PregledService} from '../../../services/pregled-service/pregled.service';
import {SessionService} from '../../../services/SessionService/session.service';
import {PregledDTO} from '../../../models/PregledDTO/pregled-dto';
import {OdsustvoDijalogComponent} from "../dijalog-odsustvo/odsustvo-dijalog.component";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Operacija} from "../../../models/operacija/operacija";
import {OperacijaKalendarDTO} from "../../../models/OperacijaKalendarDTO/operacija-kalendar-dto";
import {duration} from "moment";
import {ZdravstveniKartonService} from "../../../services/zdravstveni-karton-service/zdravstveni-karton.service";
import {PacijentService} from "../../../services/pacijent-service/pacijent.service";
import {OpstiIzvestajService} from "../../../services/opsti-izvestaj/opsti-izvestaj.service";

@Component({
  selector: 'app-radni-kalendar-lekar',
  templateUrl: './radni-kalendar-lekar.component.html',
  styleUrls: ['./radni-kalendar-lekar.component.css']
})
export class RadniKalendarLekarComponent implements OnInit {
  private pregledi: PregledDTO[] = [];
  public data: object[] = [];
  public eventSettings: EventSettingsModel = { dataSource: this.data }; // izvor podataka za kalendar
  public setView: View = 'Month';
  public selectedEvent: Event;
  public od: string;
  public do: string;
  public datum: string;
  public operacije: OperacijaKalendarDTO[] = [];

  @ViewChild('scheduleObj', { static: true })
  public scheduleObj: ScheduleComponent;

  constructor(private klinikaService: KlinikaService, private router: Router, private pregledService: PregledService,
              private sessionService: SessionService, private dialog: MatDialog, private snackBar: MatSnackBar,
              private zkService: ZdravstveniKartonService, private pacijentService: PacijentService,
              private opstiIzvestajService: OpstiIzvestajService) {
    this.pregledService.getPredefinisane(this.sessionService.ulogovanLekar.jbo).subscribe(pregledi => {
      this.pregledi = pregledi;

      if (this.pregledi) {

        let i;

        for (i = 0; i < this.pregledi.length; i++) {

          let godina = parseInt(this.pregledi[i].datum.split('.')[2]);
          let mesec = parseInt(this.pregledi[i].datum.split('.')[1]) - 1;
          let dan = parseInt(this.pregledi[i].datum.split('.')[0]);
          let sat1 = parseInt(this.pregledi[i].pocetak.split(':')[0]);
          let minut1 = parseInt(this.pregledi[i].pocetak.split(':')[1]);
          let sat2 = parseInt(this.pregledi[i].kraj.split(':')[0]);
          let minut2 = parseInt(this.pregledi[i].kraj.split(':')[1]);

          if (this.pregledi[i].jboPacijenta === undefined) {
            this.data.push({
              Id: i,
              Subject: 'Predefinisan termin',
              EventType: this.pregledi[i].jboPacijenta,
              StartTime: new Date(godina, mesec, dan, sat1, minut1),
              EndTime: new Date(godina, mesec, dan, sat2, minut2),
              IsAllDay: false
            });
          } else {
            this.data.push({
              Id: i,
              Subject: 'Pregled',
              EventType: this.pregledi[i].jboPacijenta,
              StartTime: new Date(godina, mesec, dan, sat1, minut1),
              EndTime: new Date(godina, mesec, dan, sat2, minut2),
              IsAllDay: false
            });
          }
        }
      }
    });
    this.klinikaService.findOperacijeByLekar(this.sessionService.ulogovanLekar).subscribe(podaci => {
      this.operacije = podaci;
      console.log(this.operacije);

      if(this.operacije){
        let j;

        for(j = 0; j < this.operacije.length; j++){
          let godina = parseInt(this.operacije[j].datum.split('.')[2]);
          let mesec = parseInt(this.operacije[j].datum.split('.')[1]) - 1;
          let dan = parseInt(this.operacije[j].datum.split('.')[0]);
          let sat1 = parseInt(this.operacije[j].pocetak.split(':')[0]);
          let minut1 = parseInt(this.operacije[j].pocetak.split(':')[1]);
          let sat2 = parseInt(this.operacije[j].kraj.split(':')[0]);
          let minut2 = parseInt(this.operacije[j].kraj.split(':')[1]);

          this.data.push({
            Id: j,
            Subject: 'Operacija',
            EventType: this.operacije[j].jboPacijenta,
            StartTime: new Date(godina, mesec, dan, sat1, minut1),
            EndTime: new Date(godina, mesec, dan, sat2, minut2),
            IsAllDay: false
          });
        }
      }

    });

    loadCldr(
      require('cldr-data/supplemental/numberingSystems.json'),
      require('cldr-data/main/sr-Latn/ca-gregorian.json'),
      require('cldr-data/main/sr-Latn/numbers.json'),
      require('cldr-data/main/sr-Latn/timeZoneNames.json'),
      require('cldr-data/supplemental/weekdata.json')); // To load the culture based first day of week

    L10n.load({
      'sr-Latn': {
        schedule: {
          today: 'danas',
          day: 'dnevni prikaz',
          month: 'mesečni prikaz',
          week: 'nedeljni prikaz',
          title: 'naslov',
          saveButton: 'Sačuvaj',
          cancelButton: 'Zatvori',
          deleteButton: 'Ukloni',
          newEvent: 'Dodaj termin',
          editEvent: 'Izmeni termin'
        },
        calendar: {
          today: 'danas',
        }
      }
    });
  }

  ngOnInit() {
  }

  dijalogZahtev(){
    this.sessionService.preglediKalendar = this.pregledi;
    if (this.do === undefined || this.od === undefined) {
      this.snackBar.open('Morate uneti oba datuma!',  '', { duration: 3000});
      return;
    } else if (this.od > this.do) {
      this.snackBar.open('Neispravno uneti datumi!', '', { duration: 3000});
      return;
    }
    this.datum = this.od + '|' + this.do;
    const dialogRef = this.dialog.open(OdsustvoDijalogComponent, {
      data: this.datum
    });

    dialogRef.afterClosed().subscribe(
      result => {
        console.log('zatvara se');
      },
      err => console.log('Neuspesno otvaranje prozora!'),
      () => console.log('zatvorio se')
    );
  }


  onEventClick(args: any) {
    this.selectedEvent = args.event;
  }

  onPopupOpen(args: PopupOpenEventArgs) {
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

  onCloseClick(event) {
    this.scheduleObj.quickPopup.quickPopupHide();
  }

  zapocniPregled(pregled: any, pacijentJbo: any, datum: any, ){
    this.sessionService.fromKalendar = true;

    let pacijent;
    // tslint:disable-next-line:no-shadowed-variable
    this.pacijentService.getPacijent(pacijentJbo).subscribe( pacijent => {
      this.zkService.getPacijentovZk(pacijent).subscribe(data => {
        this.opstiIzvestajService.getIzvestaj(data.id).subscribe( opstiIzvestaj =>{
          this.sessionService.zkPregled = data;
          this.sessionService.opstiIzvestaj = opstiIzvestaj;
          this.sessionService.pacijentZaPregled = pacijent;
          this.sessionService.pacijentProfil = pacijent;
          this.router.navigate(['/formaIzvestaj']);
        });
      });
    });


    console.log(pregled + " " + pacijentJbo + " " + datum);
    this.sessionService.pregled = pregled;
    // this.pacijentZaPregled = this.sessionService.pacijentProfil;
    // this.zdravstveniKarton = this.sessionService.zkPregled;
    // this.opstiIzvestaj = this.sessionService.opstiIzvestaj;
    //this.router.navigate(['/formaIzvestaj']);

  }
}
