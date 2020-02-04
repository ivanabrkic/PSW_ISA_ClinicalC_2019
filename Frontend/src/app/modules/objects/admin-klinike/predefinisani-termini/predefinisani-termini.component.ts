import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSnackBar, MatSort, MatPaginator } from '@angular/material';
import { SelectionModel } from '@angular/cdk/collections';
import { Pregled } from 'src/app/models/pregled/pregled';
import { TipPregleda } from 'src/app/models/tippregleda/tippregleda';
import { Lekar } from 'src/app/models/lekar/lekar';
import { Sala } from 'src/app/models/sala/sala';
import { Termin } from 'src/app/models/termin/termin';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { LekarTrajanje } from 'src/app/models/termin/lekartrajanje';
import { PregledId } from 'src/app/models/pregled/PregledId';
import { Pacijent } from 'src/app/models/pacijent/pacijent';

@Component({
  selector: 'app-predefinisani-termini',
  templateUrl: './predefinisani-termini.component.html',
  styleUrls: ['./predefinisani-termini.component.css']
})
export class PredefinisaniTerminiComponent implements OnInit {

  displayedColumns: string[] = ['datum', 'pocetak', 'izaberiSalu'];
  dataSource : any
  selection : any
  
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;


  termini : Termin[]

  pregled : Pregled;

  tipovi : TipPregleda[];
  popusti : number[] = []
  sati : number[]
  minuti : number[]
  lekari : Lekar[]
  sale : Sala[]

  selectedTip : TipPregleda
  selectedSat : number
  selectedMinut : number
  selectedPopust : number
  selectedLekar : Lekar
  selectedSala : Sala
  selectedTermin : Termin

  klinikaId : number;

  constructor(private _snackBar: MatSnackBar, private klinikaService : KlinikaService, private adminkService : AdminKlinikeService) { }

  ngOnInit() {
    this.sati = [0, 1, 2, 3, 4, 5, 6, 7, 8]
    this.minuti = [0, 15, 30, 45]
    
    for(var i = 0; i <= 100; i+=10){
      this.popusti.push(i)
    }

    this.selectedPopust = this.popusti[0]
    this.selectedSat = this.sati[0]
    this.selectedMinut = this.minuti[0]

    this.adminkService.getUlogovanKorisnik().subscribe(data => {
        this.klinikaId = data.klinika.id
        this.klinikaService.getTipovi(this.klinikaId).subscribe(data => {
          this.tipovi = data
          this.selectedTip = this.tipovi[0]
        })
    })
    
  }

  onChangeTip(newValue) {
    this.selectedTip = newValue;
  }

  onChangeSat(newValue) {
    this.selectedSat = newValue;
  }

  onChangeMinut(newValue) {
    this.selectedMinut = newValue;
  }

  onChangePopust(newValue) {
    this.selectedPopust = newValue;
  }

  onChangeLekar(newValue) {
    this.selectedLekar = newValue;
  }

  onChangeSala(newValue) {
    this.selectedSala = newValue;
  }

  listaLekara(){
    this.klinikaService.getLekariForTip(this.selectedTip.id).subscribe(data => {
      this.lekari = data
      this.selectedLekar = this.lekari[0]
    })
  }
  
  getTermini(){

    var lekarTrajanje = new LekarTrajanje();
    lekarTrajanje.idLekara = this.selectedLekar.id
    lekarTrajanje.trajanje = this.selectedSat*60 + this.selectedMinut

    if (lekarTrajanje.trajanje == 0){
      lekarTrajanje.trajanje = 15
      this._snackBar.open("Trajanje ne može biti 0. Podešeno je trajanje od 15 minuta.", "U redu", {
        duration: 5000,
        verticalPosition: 'top'
      });
    }

    if (lekarTrajanje.trajanje > 480){
      lekarTrajanje.trajanje = 480
      this._snackBar.open("Trajanje ne može biti preko radnog vremena lekara. Podešeno je trajanje od 8 sati.", "U redu", {
        duration: 5000,
        verticalPosition: 'top'
      });
    }

    this.klinikaService.getTermini(lekarTrajanje).subscribe(data => {
      this.termini = data
      this.dataSource = new MatTableDataSource(this.termini);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    })
  }

  izaberiSalu(termin : Termin){
    this.sale = termin.sale
    this.selectedTermin = termin
    this.selectedSala = this.sale[0]
  }

  zakaziTermin(){
    var noviPregled = new PregledId()
    noviPregled.datum = this.selectedTermin.datum
    noviPregled.pocetak = this.selectedTermin.pocetak
    noviPregled.kraj = this.selectedTermin.kraj
    noviPregled.lekar = this.selectedLekar
    noviPregled.popust = this.selectedPopust
    noviPregled.status = 'Neaktivan'
    noviPregled.cenovnik = this.selectedTip
    noviPregled.pacijent = new Pacijent()
    noviPregled.sala = this.selectedSala

    this.klinikaService.zakaziTermin(noviPregled).subscribe(data => {
      this._snackBar.open(data.text.toString(), "", {
        duration: 2000,
        verticalPosition: 'top'
      });
    })
  }

}
