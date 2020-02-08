import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { Sala } from 'src/app/models/sala/sala';
import { MatTableDataSource } from '@angular/material';
import { Lekar } from 'src/app/models/lekar/lekar';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { RadniKalendarSaleComponent } from 'src/app/modules/shared/radni-kalendar-sale/radni-kalendar-sale-component/radni-kalendar-sale.component';
import { Operacija } from 'src/app/models/operacija/operacija';
import { Pregled } from 'src/app/models/pregled/pregled';
import { ZakaziTerminComponent } from '../zakazi-termin/zakazi-termin.component';
import {KlinikaService} from '../../../../services/klinika-service/klinika.service';
import { LekarService } from 'src/app/services/lekar-service/lekar.service';
import { Email } from 'src/app/models/email/email';
import { PacijentService } from 'src/app/services/pacijent-service/pacijent.service';
import { AdminKlinikeService} from "../../../../services/admin-klinike-service/admin-klinike.service";


@Component({
  selector: 'app-izbor-sale',
  templateUrl: './izbor-sale.component.html',
  styleUrls: ['./izbor-sale.component.css']
})
export class IzborSaleComponent implements OnInit {

  @Output() zahtevObradjen: EventEmitter<boolean> = new EventEmitter();

  searchTimeStart: NgbTimeStruct = { hour: 13, minute: 30, second: 0 };
  searchTimeEnd: NgbTimeStruct = { hour: 13, minute: 30, second: 0 };
  hourStep = 1;
  minuteStep = 15;
  secondStep = 0;

  startingDate: Date
  date: Date

  trajanje: number;

  checked: boolean

  noveSale: Sala[] = []

  adminKlinike: AdministratorKlinike = new AdministratorKlinike();
  sale: Sala[] = []
  lekari: Lekar[]

  displayedColumns: string[] = ['NazivBroj', 'SlobodnaOd', 'Kalendar', 'ZakaziTermin'];
  dataSource: any

  calendarDialog: any;
  zakaziTerminDialog: any;

  operacije: any
  pregledi: any

  @Input() public passZahtev: Zahtev;

  constructor(public dialog: MatDialog, private pacijentService: PacijentService, private lekarService: LekarService, private klinikaService: KlinikaService, private adminkService: AdminKlinikeService) {
    this.dataSource = new MatTableDataSource(this.sale);
  }

  ngOnInit() {
    this.adminkService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.adminKlinike = ulogovanKorisnik;
        this.klinikaService.getSlobodneSale(this.passZahtev)
          .subscribe(data => {
            this.sale = data;
            this.dataSource = new MatTableDataSource(this.sale);
          });

      });

    var godina = parseInt(this.passZahtev.datum.split(".")[2]);
    var mesec = parseInt(this.passZahtev.datum.split(".")[1]);
    var dan = parseInt(this.passZahtev.datum.split(".")[0]);

    this.startingDate = new Date(godina, mesec, dan)
    this.date = this.startingDate

    this.zahtevObradjen.emit(true)

  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  drugiTermin(event: Event) {

    if (this.checked) {
      this.klinikaService.getDrugiTermin(this.passZahtev)
        .subscribe(data => {
          this.noveSale = data
          this.dataSource = new MatTableDataSource(this.noveSale);
          return
        });
    }
    else {
      this.dataSource = new MatTableDataSource(this.sale);
    }
  }

  openCalendar(id: Sala) {

    this.klinikaService.getOperacije(id)
      .subscribe(data => {
        this.operacije = data;
        this.klinikaService.getPregledi(id)
          .subscribe(data => {
            this.pregledi = data;
            const dialogConfig = new MatDialogConfig();

            dialogConfig.disableClose = true;
            dialogConfig.autoFocus = true;
            dialogConfig.width = '800px';
            dialogConfig.height = '600px';

            dialogConfig.data = {
              operacije: this.operacije,
              pregledi: this.pregledi
            };

            this.calendarDialog = this.dialog.open(RadniKalendarSaleComponent, dialogConfig);
          });
      });
  }

  posaljiPacijentuMail(jboPacijenta: String, sala: Sala, operacija : any, tipPosete : String) {
    this.pacijentService.findPacijentByJbo(jboPacijenta).subscribe(data => {
      var email: Email = new Email()
      email.email = data.email
      email.subject = "Obaveštenje o zakazanoj/om  " + tipPosete + "!"
      email.text =
        "Poštovani/a " + data.ime + " " + data.prezime + " obaveštavamo vas o zakazanoj/om  " + tipPosete + " " + operacija.tipPregleda + " u sali " + sala.naziv + " " + sala.broj + ". Operacija je zakazan za "
        + operacija.datum + " od " + operacija.pocetak + " do " + operacija.kraj + " "
        + "kod lekara " + operacija.jboLekara + " za pacijenta " + operacija.jboPacijenta + "."
      this.klinikaService.sendEmail(email).subscribe(data => alert(data.text))

    })
  }

  posaljiLekaruMail(jboLekara: String, sala: Sala, pregled : any, tipPosete : String) {
    this.lekarService.findLekarByJbo(jboLekara).subscribe(lekar => {
      var email: Email = new Email()
      email.email = lekar.email
      email.subject = "Obaveštenje o zakazanoj/om " + tipPosete + "!"
      email.text =
        "Poštovani/a " + lekar.ime + " " + lekar.prezime + " obaveštavamo vas o zakazanoj/om  " + tipPosete + " " + pregled.tipPregleda + " u sali " + sala.naziv + " " + sala.broj + ". Operacija je zakazan za "
        + pregled.datum + " od " + pregled.pocetak + " do " + pregled.kraj + " "
        + "kod lekara (JBO lekara) " + pregled.jboLekara + " za pacijenta (JBO pacijenta) " + pregled.jboPacijenta + "."
      this.klinikaService.sendEmail(email).subscribe(data =>
        alert(data.text + " " + lekar.ime + " " + lekar.prezime))
    })
  }

  zakaziTermin(sala: Sala) {

    this.passZahtev.datum = sala.datumSlobodna
    this.passZahtev.pocetak = sala.pocetakSlobodna
    this.passZahtev.kraj = sala.krajSlobodna

    this.klinikaService.getSlobodniLekari(this.passZahtev)
      .subscribe(data => {
        var lekari = data;
        const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = true;
        dialogConfig.autoFocus = true;
        dialogConfig.width = '500px';
        dialogConfig.height = '400px';


        dialogConfig.data = {
          lekari: lekari,
          sala: sala,
          tip: this.passZahtev.tipPosete,
          jboLekara: this.passZahtev.posiljalacJbo,
          jboPacijenta: this.passZahtev.jboPacijenta,
          tipId: this.passZahtev.idStavke,
          tipPregleda: this.passZahtev.stavkaCenovnika,
          idZahteva: this.passZahtev.id
        };

        this.zakaziTerminDialog = this.dialog.open(ZakaziTerminComponent, dialogConfig);

        this.zakaziTerminDialog.afterClosed().subscribe(
          dialogResult => {
            if (this.passZahtev.tipPosete == 'Operacija') {
              this.klinikaService.zakaziOperaciju(dialogResult[1])
                .subscribe(data => {
                  alert(data.text)
                  this.klinikaService.removeZahtev(dialogResult[2])
                    .subscribe(data => {
                      dialogResult[1].jboLekara.forEach(element => {
                        this.posaljiLekaruMail(element, dialogResult[3], dialogResult[1], this.passZahtev.tipPosete)
                      })
                      this.posaljiPacijentuMail(dialogResult[1].jboPacijenta, dialogResult[3], dialogResult[1], this.passZahtev.tipPosete)
                      this.zahtevObradjen.emit(dialogResult[0])
                    },
                    error => alert(JSON.stringify(data)));
                });
            }
            else {
              this.klinikaService.zakaziPregled(dialogResult[1])
                .subscribe(data => {
                  alert(data.text)
                  this.klinikaService.removeZahtev(dialogResult[2])
                    .subscribe(data => {
                      this.posaljiLekaruMail(dialogResult[1].jboLekara, dialogResult[3], dialogResult[1], this.passZahtev.tipPosete)
                      this.posaljiPacijentuMail(dialogResult[1].jboPacijenta, dialogResult[3], dialogResult[1], this.passZahtev.tipPosete)
                      this.zahtevObradjen.emit(dialogResult[0])
                    });
                });
            }
          });
      });
  }

}
