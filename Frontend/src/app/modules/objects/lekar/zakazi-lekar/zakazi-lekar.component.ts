import { Component, OnInit, ViewChild, Input, NgZone } from '@angular/core';
import { MatSnackBar, MatSort, MatPaginator, MatStepper, MatTableDataSource } from '@angular/material';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { TipPregleda } from 'src/app/models/tippregleda/tippregleda';
import { Termin } from 'src/app/models/termin/termin';
import { AdminKlinikePregledComponent } from '../../admin-klinike/admin-klinike-pregled/admin-klinike-pregled.component';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { LekarService } from 'src/app/services/lekar-service/lekar.service';
import { Lekar } from 'src/app/models/lekar/lekar';
import { Pacijent } from 'src/app/models/pacijent/pacijent';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-zakazi-lekar',
  templateUrl: './zakazi-lekar.component.html',
  styleUrls: ['./zakazi-lekar.component.css']
})
export class ZakaziLekarComponent implements OnInit {

  displayedColumns: string[] = ['datum', 'pocetak', 'zakaziTermin'];
  dataSource : any;

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  @ViewChild('stepper', { static: true }) stepper: MatStepper;

  tipoviPoseta : String[] = []
  tipoviPregleda : TipPregleda[] = []
  sati : number[] = []
  minuti : number[] = []

  termini : Termin[] = []

  selectedTipPosete : String 
  selectedTipPregleda : TipPregleda
  selectedSat: number
  selectedMinut: number
  selectedTermin: Termin

  klinikaId : number

  prosledjenLekar : Lekar
  @Input() public prosledjenPacijent : Pacijent

  dodatneInformacije : any 

  @ViewChild('autosize', { static: true }) autosize: CdkTextareaAutosize;


  constructor(private _ngZone: NgZone, private _snackBar: MatSnackBar, private lekarService : LekarService) { 
    this.dataSource = new MatTableDataSource(null);
  }

  ngOnInit() {
    this.sati = [0, 1, 2, 3, 4, 5, 6, 7, 8]
    this.minuti = [0, 15, 30, 45]
    this.tipoviPoseta = ["Operacija", "Pregled"]

    this.selectedSat = this.sati[0]
    this.selectedMinut = this.minuti[0]
    this.selectedTipPosete = this.tipoviPoseta[0]

    this.lekarService.getUlogovanKorisnik().subscribe(data => {
      this.prosledjenLekar = data
      this.lekarService.getBySpecAndKlinika(this.prosledjenLekar).subscribe(data => {
        this.tipoviPregleda = data
        this.selectedTipPregleda = this.tipoviPregleda[0]
      })
    })

    this.dodatneInformacije = " "
  }

  triggerResize() {
    // Wait for changes to be applied, then trigger textarea resize.
    this._ngZone.onStable.pipe(take(1))
        .subscribe(() => this.autosize.resizeToFitContent(true));
  }

  onChangeTipPregleda(newValue) {
    this.selectedTipPregleda = newValue;
  }

  onChangeSat(newValue) {
    this.selectedSat = newValue;
  }

  onChangeMinut(newValue) {
    this.selectedMinut = newValue;
  }

  onChangeTipPosete(newValue) {
    this.selectedTipPosete = newValue;
  }

  listaTermina(){

    if (this.tipoviPregleda.length == 0) {

      this._snackBar.open("Ne postoje tipovi pregleda za Vasu specijalizaciju!", "", {
        duration: 3000,
        verticalPosition: 'top'
      });

      this.stepper.previous()
    }

    var trajanje = 60*this.selectedSat + this.selectedMinut

    if (trajanje == 0) {
      trajanje = 15
      this._snackBar.open("Trajanje ne može biti 0. Podešeno je trajanje od 15 minuta.", "U redu", {
        duration: 5000,
        verticalPosition: 'top'
      });
    }

    this.lekarService.getTerminiLekar(this.prosledjenLekar, this.prosledjenPacijent, trajanje).subscribe(data =>{
      this.termini = data
      this.dataSource = new MatTableDataSource(this.termini);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    })

  }

  zakaziTermin(termin : Termin){
    this.selectedTermin = termin
  }

  potvrdi(){
      var zahtev = new Zahtev()
      zahtev.idKlinike = this.prosledjenLekar.klinika.id
      zahtev.idStavke = this.selectedTipPregleda.id
      zahtev.jboLekara = this.prosledjenLekar.jbo
      zahtev.jboPacijenta = this.prosledjenPacijent.jbo
      zahtev.posiljalacImePrezime = this.prosledjenLekar.ime + " " + this.prosledjenLekar.prezime
      zahtev.posiljalacJbo = this.prosledjenLekar.jbo
      zahtev.stavkaCenovnika = this.selectedTipPregleda.naziv
      zahtev.tipPosete = this.selectedTipPosete
      zahtev.tipPosiljaoca = 'Lekar'
      zahtev.datum = this.selectedTermin.datum
      zahtev.pocetak = this.selectedTermin.pocetak
      zahtev.kraj = this.selectedTermin.kraj

      zahtev.dodatneInformacije = this.dodatneInformacije + " "

      this.lekarService.posaljiZahtevAdminu(zahtev).subscribe(data => {
        this._snackBar.open("Uspešno ste poslali zahtev administratorima klinike.", "", {
          duration: 2000,
          verticalPosition: 'top'
        });
      })

      this.stepper.reset();
  }

}
