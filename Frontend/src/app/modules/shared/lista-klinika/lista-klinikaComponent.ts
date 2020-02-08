import {Component, OnInit} from '@angular/core';
import {Klinika} from '../../../models/klinika/klinika';
import {Observable, Subscription} from 'rxjs';
import { ListaKlinikaService } from 'src/app/services/lista-klinika-service/lista-klinika.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import { pretragaDTO } from 'src/app/models/pretragaDTO/pretragaDTO';
import { MatDatepickerInputEvent, MatSnackBar } from '@angular/material';
import { Cenovnik } from 'src/app/models/Cenovnik/cenovnik';
import { klinikaCena } from 'src/app/models/klinikaCena/klinikaCena';

@Component({
  selector: 'app-lista-klinika',
  templateUrl: './lista-klinikaComponent.html',
  styleUrls: ['/lista-klinikaComponent.css']
})



export class ListaKlinikaComponent implements OnInit {
  private klinike: klinikaCena[];
  selectedKlinika: klinikaCena;
  date = new FormControl(new Date);
  zahtev = new pretragaDTO();
  tipovi: Cenovnik[];
  searchPregled: Cenovnik=null;
  minDate:Date= new Date(); 
  searchOcena:number;
  mapa:Map<Klinika,number>;

  constructor(private _snackBar: MatSnackBar,private listaKlinikaService: ListaKlinikaService, private router: Router) {
  }

  // getKlinike() {
  //   this.listaKlinikaService.findAll().subscribe(
  //     podaci => {
  //       this.klinike = podaci;
  //     },
  //     err => console.log('Nisu ucitane klinike'),
  //     () => console.log('Uspesno ucitane klinike')
  //   );
  // }

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    this.zahtev.idKlinike = null;
    var selectedDate = this.date.value;
    selectedDate = moment(selectedDate).format('D.M.YYYY.');
    this.zahtev.datum = selectedDate
    this.zahtev.start = '00:00';
    this.zahtev.finis = '23:59';
    if(this.zahtev.specijalizacija!=null){
      console.log(this.zahtev)
      this.listaKlinikaService.getSlobodneKlinike(this.zahtev).subscribe(
        podaci => {
          this.klinike=podaci;
          
        },
        err => console.log('Nisu ucitane klinike'),
        () => console.log('Uspesno ucitane klinike')
      );
      }
  }

  onSelect(klinika: klinikaCena): void {
    this.selectedKlinika = klinika;
    console.log(klinika)
    this.zahtev.idKlinike = klinika.klinika.id;
  }

  ngOnInit() {
  //  this.getKlinike();
    this.getTipovi();
    this.searchOcena=0;
  }

  getTipovi() {
    this.listaKlinikaService.findTipovi().subscribe(
      podaci => {
        this.tipovi = podaci;
      },
      err => console.log('Nisu ucitani tipovi'),
      () => console.log('Uspesno ucitani tipovi')
    );
  }

  onChange(selected) {
    console.log(selected)
    if (selected!=null){
    this.zahtev.specijalizacija = selected.naziv;
    this.zahtev.idKlinike = null;
    this.zahtev.start = '00:00';
    this.zahtev.finis = '23:59';
    if(this.zahtev.datum!=null){
      console.log(this.zahtev)
      this.listaKlinikaService.getSlobodneKlinike(this.zahtev).subscribe(
        podaci => {
          this.klinike=podaci;
        },
        err => console.log('Nisu ucitane klinike'),
        () => console.log('Uspesno ucitane klinike')
      );
      }
    }
  }


  lekariNavigate(event) {
    if(this.zahtev.specijalizacija==null || this.zahtev.datum==null){
      this._snackBar.open("Izaberite datum i tip pregleda!", "",  {
        duration: 3000,
        verticalPosition: 'bottom'
      });
      return;
    }
      
    var selectedDate = this.date.value;
    selectedDate = moment(selectedDate).format('D.M.YYYY.');
// <<<<<<< 3_1_Implementiranje_izmene_lozinke_korisnika
//     console.log(selectedDate);
//     let klinika = this.selectedKlinika;
//     this.router.navigate(['/listaLekara'], //bio je konflikt, ne znam sta je tacno od ova dva, osecaj ovo dole
//       {  state: {
//           klinika: klinika,
//           zahtev: this.zahtev,
//           tip: this.searchPregled
//         }
//       });
      let klinika = this.selectedKlinika.klinika;
        this.router.navigate(['/listaLekara'], 
        {  state: {
            klinika: klinika,
            zahtev: this.zahtev,
            tip: this.searchPregled,
            cena:this.selectedKlinika.cena
          }
        });
  }


  profilNavigate(event) {

    let klinika = this.selectedKlinika.klinika;
    this.router.navigate(['/profilKlinike'], {state: {data: klinika}});
  }
}
