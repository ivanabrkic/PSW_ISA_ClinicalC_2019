import { Component, OnInit, Input } from '@angular/core';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { Sala } from 'src/app/models/sala/sala';
import { MatTableDataSource } from '@angular/material';
import { Lekar } from 'src/app/models/lekar/lekar';
import { LekarService } from 'src/app/services/lekar-service/lekar.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { RadniKalendarSaleComponent } from 'src/app/modules/shared/radni-kalendar-sale/radni-kalendar-sale-component/radni-kalendar-sale.component';
import { Operacija } from 'src/app/models/operacija/operacija';
import { Pregled } from 'src/app/models/pregled/pregled';
import { ZakaziTerminComponent } from '../zakazi-termin/zakazi-termin.component';

@Component({
  selector: 'app-izbor-sale',
  templateUrl: './izbor-sale.component.html',
  styleUrls: ['./izbor-sale.component.css']
})
export class IzborSaleComponent implements OnInit {

  searchTimeStart: NgbTimeStruct = { hour: 13, minute: 30, second: 0 };
  searchTimeEnd: NgbTimeStruct = { hour: 13, minute: 30, second: 0 };
  hourStep = 1;
  minuteStep = 15;
  secondStep = 0;

  startingDate: Date
  date: Date

  trajanje: number;

  checked: boolean

  sveSale: Sala[]
  noveSale: Map<number, Sala>

  noveSaleNiz: Sala[]

  adminKlinike: AdministratorKlinike = new AdministratorKlinike();
  sale: Sala[]
  lekari: Lekar[]

  displayedColumns: string[] = ['NazivBroj', 'SlobodnaOd', 'Kalendar', 'ZakaziTermin'];
  dataSource: any

  calendarDialog: any;
  zakaziTerminDialog: any;

  operacije : any
  pregledi : any

  @Input() public passZahtev: Zahtev;

  constructor(public dialog: MatDialog, private lekarService: LekarService, private klinikaService: KlinikaService, private adminkService: AdminKlinikeService) {
    this.adminkService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.adminKlinike = ulogovanKorisnik;
        this.klinikaService.getSaleSlobodneOd(this.passZahtev)
          .subscribe(data => {
            this.sale = data;
 
            for (var i = 0; i < this.sale.length; i++) {
              this.sale[i].datumSlobodna = this.passZahtev.datum
              this.sale[i].pocetakSlobodna = this.passZahtev.pocetak
              this.sale[i].krajSlobodna = this.passZahtev.kraj
            }

            this.dataSource = new MatTableDataSource(this.sale);
          });
        this.klinikaService.getSale(this.adminKlinike.klinika.id)
          .subscribe(data => {
            this.sveSale = data;
          });
        this.lekarService.getLekariKlinike(this.adminKlinike.klinika.id)
          .subscribe(data => {
            this.lekari = data;
          });
      });
  }

  ngOnInit() {
    this.adminkService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {

        this.adminKlinike = ulogovanKorisnik;

        this.klinikaService.getSaleSlobodneOd(this.passZahtev)
          .subscribe(data => {
            this.sale = data

            for (var i = 0; i < this.sale.length; i++) {
              this.sale[i].datumSlobodna = this.passZahtev.datum
              this.sale[i].pocetakSlobodna = this.passZahtev.pocetak
              this.sale[i].krajSlobodna = this.passZahtev.kraj
            }

            this.dataSource = new MatTableDataSource(this.sale);
          });
        this.klinikaService.getSale(this.adminKlinike.klinika.id)
          .subscribe(data => {
            this.sveSale = data;
          });
        this.lekarService.getLekariKlinike(this.adminKlinike.klinika.id)
          .subscribe(data => {
            this.lekari = data;
          });


      });

    var godina = parseInt(this.passZahtev.datum.split('.')[2])
    var mesec = parseInt(this.passZahtev.datum.split('.')[1])
    var dan = parseInt(this.passZahtev.datum.split('.')[0])
    var sat = parseInt(this.passZahtev.pocetak.split(':')[0])
    var minut = parseInt(this.passZahtev.pocetak.split(':')[1])
    this.searchTimeStart = { hour: sat, minute: minut, second: 0 };
    var sat = parseInt(this.passZahtev.kraj.split(':')[0])
    var minut = parseInt(this.passZahtev.kraj.split(':')[1])
    this.searchTimeEnd = { hour: sat, minute: minut, second: 0 };
    this.startingDate = new Date(godina, mesec - 1, dan)
    this.date = this.startingDate;
    this.checked = false

  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  drugiTermin(event: Event) {

    if (this.checked) {
      var i = 0

      this.noveSale = new Map()

      var godina = parseInt(this.passZahtev.datum.split('.')[2])
      var mesec = parseInt(this.passZahtev.datum.split('.')[1])
      var dan = parseInt(this.passZahtev.datum.split('.')[0])
      var satPocetak = parseInt(this.passZahtev.pocetak.split(':')[0])
      var minutPocetak = parseInt(this.passZahtev.pocetak.split(':')[1])
      var satKraj = parseInt(this.passZahtev.kraj.split(':')[0])
      var minutKraj = parseInt(this.passZahtev.kraj.split(':')[1])

      var datumPocetak = new Date(godina, mesec - 1, dan, satPocetak, minutPocetak, 0, 0)
      var datumKraj = new Date(godina, mesec - 1, dan, satKraj, minutKraj, 0, 0)

      var trajanje = datumKraj.getTime() - datumPocetak.getTime();
      var sledeciDan = new Date(godina, mesec - 1, dan, 0, 0, 0, 0)

      this.noviTermin(i, sledeciDan, trajanje)
    }
    else{
      this.dataSource = new MatTableDataSource(this.sale);
    }
  }

  noviTermin(i: number, sledeciDan: Date, trajanje: number) {

    var noviZahtev = new Zahtev()

    noviZahtev.idKlinike = this.adminKlinike.klinika.id

    sledeciDan.setMinutes(sledeciDan.getMinutes() + i * 15);

    var krajnji = new Date(sledeciDan)
    var probni = new Date(sledeciDan)

    probni.setMilliseconds(probni.getMilliseconds() + trajanje)

    if (probni.getDate() == sledeciDan.getDate()) {
      krajnji.setMilliseconds(krajnji.getMilliseconds() + trajanje)
    }
    else {
      sledeciDan.setDate(sledeciDan.getDate() + 1)
    }

    noviZahtev.datum = sledeciDan.getDate().toString() + "." + (sledeciDan.getMonth() + 1).toString() + "." + sledeciDan.getFullYear().toString() + "."
    noviZahtev.pocetak = sledeciDan.getHours().toString() + ":" + sledeciDan.getMinutes().toString()
    noviZahtev.kraj = krajnji.getHours().toString() + ":" + krajnji.getMinutes().toString()

    noviZahtev.jboPacijenta = this.passZahtev.jboPacijenta

    this.klinikaService.getSaleSlobodneOd(noviZahtev)
      .subscribe(data => {
        for (var i = 0; i < data.length; i++) {
          this.noveSale.set(data[i].id, data[i])
          this.noveSale.get(data[i].id).datumSlobodna = noviZahtev.datum
          this.noveSale.get(data[i].id).pocetakSlobodna = noviZahtev.pocetak
          this.noveSale.get(data[i].id).krajSlobodna = noviZahtev.kraj
        }
        if (this.sveSale.length != this.noveSale.size){
          return this.noviTermin(1, sledeciDan, trajanje)
        }
        else{
          this.noveSaleNiz = new Array()

          for (var [key, value] of this.noveSale) {
            this.noveSaleNiz.push(value)
          }

          this.dataSource = new MatTableDataSource(this.noveSaleNiz);
          return
        }

      });
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

    zakaziTermin(sala : Sala){
        var noviZahtev : Zahtev = new Zahtev()

        noviZahtev.datum = sala.datumSlobodna
        noviZahtev.pocetak = sala.pocetakSlobodna
        noviZahtev.kraj = sala.krajSlobodna
        noviZahtev.idKlinike = this.passZahtev.idKlinike

        this.lekarService.getSlobodniLekari(noviZahtev)
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
              jboLekara: this.passZahtev.posiljalacJbo
            };

            this.zakaziTerminDialog = this.dialog.open(ZakaziTerminComponent, dialogConfig);
      });
    }

}
