import { Component, OnInit, ViewChild, NgZone } from '@angular/core';
import { MatSort, MatPaginator, MatStepper, MatTableDataSource, MatSnackBar, MatInput } from '@angular/material';
import { ZahtevOdsustvo } from 'src/app/models/zahtev-odsustvo/zahtev-odsustvo';
import { ZahtevOdsustvoService } from 'src/app/services/zahtev-odsustvo-service/zahtev-odsustvo.service';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { Message } from 'src/app/models/message/message';
import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-zahtevi-godisnji-odsustvo',
  templateUrl: './zahtevi-godisnji-odsustvo.component.html',
  styleUrls: ['./zahtevi-godisnji-odsustvo.component.css']
})
export class ZahteviGodisnjiOdsustvoComponent implements OnInit {

  displayedColumns: string[] = ['korisnikUloga', 'korisnikJbo', 'korisnikIme', 'datumOd', 'opis', 'proveriValidnost'];
  dataSource: any

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  @ViewChild('stepper', { static: true }) stepper: MatStepper;

  zahtevi: ZahtevOdsustvo[] = []

  validationMessage: Message

  validationFailed: boolean = false

  razlog: String = " "

  idKlinike: number

  @ViewChild('autosize', { static: true }) autosize: CdkTextareaAutosize;

  constructor(private _ngZone: NgZone, private _snackBar: MatSnackBar, private adminKService: AdminKlinikeService, private zahtevOdsustvoService: ZahtevOdsustvoService) {
    this.dataSource = new MatTableDataSource(null);
  }

  ngOnInit() {
    this.adminKService.getUlogovanKorisnik().subscribe(data => {
      this.idKlinike = data.klinika.id
      this.zahtevOdsustvoService.getAllByKlinikaId(data.klinika.id).subscribe(zahtevi => {
        this.zahtevi = zahtevi
        this.dataSource = new MatTableDataSource(this.zahtevi);
        this.dataSource.sort = this.sort;
      })
    })
  }

  triggerResize() {
    // Wait for changes to be applied, then trigger textarea resize.
    this._ngZone.onStable.pipe(take(1))
        .subscribe(() => this.autosize.resizeToFitContent(true));
  }

  selectedZahtev: ZahtevOdsustvo

  // test(){
  //     this.selectedZahtev.razlog = this.razlog
  //     alert(this.razlog)
  // }

  proveriValidnost(zahtev: ZahtevOdsustvo) {

    this.zahtevOdsustvoService.checkValidnostZahteva(zahtev).subscribe(message => {
      this.validationMessage = message
      if (this.validationMessage.text === 'Korisnik ispunjava osnovne uslove za odobrenje godišnjeg odmora!') {
        this.validationFailed = false
      }
      else {
        this.validationFailed = true
      }
    })

    this.selectedZahtev = zahtev
  }

  prihvatiZahtev() {
    this.zahtevOdsustvoService.prihvatiZahtevZaOdsustvo(this.selectedZahtev).subscribe(message => {
      this.zahtevOdsustvoService.getAllByKlinikaId(this.idKlinike).subscribe(zahtevi => {
        this.zahtevi = zahtevi
        this.dataSource = new MatTableDataSource(this.zahtevi);
        this.dataSource.sort = this.sort;
      })
      this._snackBar.open(message.text.toString(), "", {
        duration: 2000,
        verticalPosition: 'top'
      });
    })
    this.stepper.reset();

  }

  odbijZahtev() {

    this.selectedZahtev.razlog = this.razlog

    this.zahtevOdsustvoService.odbijZahtevZaOdsustvo(this.selectedZahtev).subscribe(message => {
      this.zahtevOdsustvoService.getAllByKlinikaId(this.idKlinike).subscribe(zahtevi => {
        this.zahtevi = zahtevi
        this.dataSource = new MatTableDataSource(this.zahtevi);
        this.dataSource.sort = this.sort;
      })
      this._snackBar.open(message.text.toString(), "", {
        duration: 2000,
        verticalPosition: 'top'
      });
    })

    this.stepper.reset();

  }

}
