import {Component, OnInit, ViewChild} from '@angular/core';
import {KlinikaService} from '../../../../services/klinika-service/klinika.service';
import {Router} from '@angular/router';
import {PregledService} from '../../../../services/pregled-service/pregled.service';
import {SessionService} from '../../../../services/SessionService/session.service';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ZdravstveniKartonService} from '../../../../services/zdravstveni-karton-service/zdravstveni-karton.service';
import {PacijentService} from '../../../../services/pacijent-service/pacijent.service';
import {OpstiIzvestajService} from '../../../../services/opsti-izvestaj/opsti-izvestaj.service';
import {DatePipe} from '@angular/common';
import {L10n, loadCldr} from '@syncfusion/ej2-base';
import {PregledDTO} from '../../../../models/PregledDTO/pregled-dto';
import {EventSettingsModel, PopupOpenEventArgs, View} from '@syncfusion/ej2-schedule';
import {OperacijaKalendarDTO} from '../../../../models/OperacijaKalendarDTO/operacija-kalendar-dto';
import {ScheduleComponent} from '@syncfusion/ej2-angular-schedule';
import {PregledIzvestajDTO} from '../../../../models/pregledIzvestajDTO/pregled-izvestaj-dto';
import {LekarService} from '../../../../services/lekar-service/lekar.service';
import {OdsustvoDijalogComponent} from "../../../shared/dijalog-odsustvo/odsustvo-dijalog.component";

@Component({
  selector: 'app-radni-kalendar-sestra',
  templateUrl: './radni-kalendar-sestra.component.html',
  styleUrls: ['./radni-kalendar-sestra.component.css']
})
export class RadniKalendarSestraComponent implements OnInit {

  private pregledi: PregledDTO[] = [];
  public data: object[] = [];
  public eventSettings: EventSettingsModel = { dataSource: this.data }; // izvor podataka za kalendar
  public setView: View = 'Month';
  public selectedEvent: Event;
  public od: string;
  public do: string;
  public datum: string;
  public operacije: OperacijaKalendarDTO[] = [];
  public poceo = false;

  @ViewChild('scheduleObj', { static: true })
  public scheduleObj: ScheduleComponent;

  constructor(private klinikaService: KlinikaService, private router: Router, private pregledService: PregledService,
              private sessionService: SessionService, private dialog: MatDialog, private snackBar: MatSnackBar,
              private zkService: ZdravstveniKartonService, private pacijentService: PacijentService,
              private opstiIzvestajService: OpstiIzvestajService, private datePipe: DatePipe,
              private lekarService: LekarService) {
    this.pregledService.getAllPreglede().subscribe(pregledi => {
      this.pregledi = pregledi;
      console.log(pregledi);
      if (this.pregledi) {
        let i;

        for (i = 0; i < this.pregledi.length; i++) {

          const godina = parseInt(this.pregledi[i].datum.split('.')[2]);
          const mesec = parseInt(this.pregledi[i].datum.split('.')[1]) - 1;
          const dan = parseInt(this.pregledi[i].datum.split('.')[0]);
          const sat1 = parseInt(this.pregledi[i].pocetak.split(':')[0]);
          const minut1 = parseInt(this.pregledi[i].pocetak.split(':')[1]);
          const sat2 = parseInt(this.pregledi[i].kraj.split(':')[0]);
          const minut2 = parseInt(this.pregledi[i].kraj.split(':')[1]);
          if (this.pregledi[i].jboPacijenta === undefined) {
            this.data.push({
              Id: i,
              Subject: 'Predefinisan termin',
              EventType: this.pregledi[i].jboPacijenta,
              StartTime: new Date(godina, mesec, dan, sat1, minut1),
              EndTime: new Date(godina, mesec, dan, sat2, minut2),
              idPregleda: this.pregledi[i].id,
              lekarJbo: this.pregledi[i].jboLekara,
              IsAllDay: false
            });
          } else {
            this.data.push({
              Id: i,
              Subject: 'Pregled',
              EventType: this.pregledi[i].jboPacijenta,
              StartTime: new Date(godina, mesec, dan, sat1, minut1),
              EndTime: new Date(godina, mesec, dan, sat2, minut2),
              idPregleda: this.pregledi[i].id,
              lekarJbo: this.pregledi[i].jboLekara,
              IsAllDay: false
            });
          }
        }
      }
    });

    this.klinikaService.getZakazaneOperacije().subscribe(podaci => {
      this.operacije = podaci;
      console.log(this.operacije);

      if (this.operacije) {
        let j;

        for (j = 0; j < this.operacije.length; j++) {
          const godina = parseInt(this.operacije[j].datum.split('.')[2]);
          const mesec = parseInt(this.operacije[j].datum.split('.')[1]) - 1;
          const dan = parseInt(this.operacije[j].datum.split('.')[0]);
          const sat1 = parseInt(this.operacije[j].pocetak.split(':')[0]);
          const minut1 = parseInt(this.operacije[j].pocetak.split(':')[1]);
          const sat2 = parseInt(this.operacije[j].kraj.split(':')[0]);
          const minut2 = parseInt(this.operacije[j].kraj.split(':')[1]);

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

  onEventClick(args: any) {
    this.selectedEvent = args.event;
  }

  onPopupOpen(args: PopupOpenEventArgs) {
    if (args.type === 'DeleteAlert') {
      args.cancel = true;
      this.scheduleObj.deleteEvent((args.data as any).Id);
    } else if (args.type === 'Editor') {
      args.cancel = true;
    }

    if ((!args.target.classList.contains('e-appointment') && (args.type === 'QuickInfo'))) {
      args.cancel = true;
    }
  }

  onCloseClick(event) {
    this.scheduleObj.quickPopup.quickPopupHide();
  }

  zapocniPregled(lekarJbo: any, pacijentJbo: any, startTime: Date, endTime: Date, idPregleda: number) {
    const date = this.datePipe.transform(new Date(), 'd.M.yyyy.').split('.', 3);
    const time = this.datePipe.transform(new Date(), 'H:mm').split(':');
    const dateString = this.datePipe.transform(startTime, 'd.M.yyyy.');
    const startTimeString = this.datePipe.transform(startTime, 'HH:mm');
    const endTimeString = this.datePipe.transform(endTime, 'HH:mm');
    if (!this.proveriVreme(date, time, dateString, startTimeString, endTimeString)) {
      this.snackBar.open('Nije moguće započeti ovaj pregled!', '', {
        duration: 3000
      });
      return;
    }

    this.sessionService.fromKalendar = true;
    // this.sessionService.datumZaPregled = datum;
    const datum = this.datePipe.transform(startTime, 'd.M.yyyy.');
    const pocetak = this.datePipe.transform(startTime, 'hh:mm');
    const kraj = this.datePipe.transform(endTime, 'hh:mm');
    this.lekarService.findLekarByJbo(lekarJbo).subscribe( lekar => {
      console.log(lekar);
      const pregled = new PregledIzvestajDTO(idPregleda, pacijentJbo, datum, pocetak, kraj,
        lekar.jbo, lekar.ime, lekar.prezime);

      this.pacijentService.getPacijent(pacijentJbo).subscribe( pacijent => {
        this.zkService.getPacijentovZk(pacijent).subscribe(data => {
          this.opstiIzvestajService.getIzvestaj(data.id).subscribe( opstiIzvestaj => {
            this.sessionService.pregled = pregled;
            this.sessionService.zkPregled = data;
            this.sessionService.opstiIzvestaj = opstiIzvestaj;
            this.sessionService.pacijentZaPregled = pacijent;
            this.sessionService.pacijentProfil = pacijent;
            this.router.navigate(['/formaIzvestaj']);
          });
        });
      });
    });
  }

  dijalogZahtev() {
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


  proveriVreme(datum, vreme, dateString, startTimeString, endTimeString): boolean {
    this.poceo = false;
    console.log('usao je ovde');
    const date = new Date(datum);
    console.log(date.getDate());
    const sati = +vreme[0];
    const minuti = +vreme[1];

    const datumDan = (date.getMonth() + 1).toString();
    const datumMesec = date.getDate().toString();
    const datumGodina = date.getFullYear().toString();


    const datumPregled = dateString.split('.', 3);
    const vremePocetakPregled = startTimeString.split(':', );
    const vremeKrajPregled = endTimeString.split(':');
    console.log(datumPregled + ' ' + vremePocetakPregled +  ' ' + vremeKrajPregled);

    if ((datumDan === datumPregled[0]) && (datumMesec === datumPregled[1]) && (datumGodina === datumPregled[2])) {
      console.log('taj je datum');
      const satiPocetakPregled = +vremePocetakPregled[0];
      const minutiPocetakPregled = +vremePocetakPregled[1];
      console.log(satiPocetakPregled);
      console.log(minutiPocetakPregled);
      if (sati > satiPocetakPregled) {
        console.log('sati > satiPocetakPregled');
        const satiKrajPregled = +vremeKrajPregled[0];
        const minutiKrajPregled = +vremeKrajPregled[1];
        console.log(satiKrajPregled + ' ' + minutiKrajPregled);
        if (sati < satiKrajPregled) {
          console.log('sati > satiKrajPregled');
          this.poceo = true;
          console.log('sati kraja su ok');
        } else if (sati === satiKrajPregled) {
          console.log('Minuti trenutno: ' + minuti);
          console.log('Minuti kada se zavrsava pregled: ' + minutiKrajPregled);
          if (minuti < minutiKrajPregled) {
            this.poceo = true;
          } else {
            this.poceo = false;
          }
        } else {
          this.poceo = false;
        }
      } else if (sati === satiPocetakPregled) {
        if (minuti >= minutiPocetakPregled) {
          const satiKrajPregled = +vremeKrajPregled[0];
          const minutiKrajPregled = +vremeKrajPregled[1];
          if (sati < satiKrajPregled) {
            this.poceo = true;
          } else if (sati === satiKrajPregled) {
            if (minuti < minutiKrajPregled) {
              this.poceo = true;
            } else {
              this.poceo = false;
            }
          } else {
            this.poceo = false;
          }
        } else {
          this.poceo = false;
        }
      } else {
        this.poceo = false;
      }
    } else {
      this.poceo = false;
    }

    return this.poceo;
  }

}
