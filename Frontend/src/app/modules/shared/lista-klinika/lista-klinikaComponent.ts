import {Component, OnInit} from '@angular/core';
import {Klinika} from '../../../models/klinika/klinika';
import {Observable, Subscription} from 'rxjs';
import { ListaKlinikaService } from 'src/app/services/lista-klinika-service/lista-klinika.service';
import { Router } from '@angular/router';
import { FormControl } from '@angular/forms';
import * as moment from 'moment';
import { pretragaDTO } from 'src/app/models/pretragaDTO/pretragaDTO';
import { MatDatepickerInputEvent } from '@angular/material';

@Component({
  selector: 'app-lista-klinika',
  templateUrl: './lista-klinikaComponent.html',
  styleUrls: ['/lista-klinikaComponent.css']
})



export class ListaKlinikaComponent implements OnInit {
  private klinike: Klinika[];
  selectedKlinika: Klinika;
  date=new FormControl(new Date);
  zahtev=new pretragaDTO();
  tipovi:String[];


  constructor(private listaKlinikaService: ListaKlinikaService,private router:Router) {
  }

  getKlinike() {
    this.listaKlinikaService.findAll().subscribe(
      podaci => {this.klinike = podaci; },
      err => console.log('Nisu ucitane klinike'),
      () => console.log('Uspesno ucitane klinike')
    );
  }

  addEvent(type:string,event:MatDatepickerInputEvent<Date>){
    this.zahtev.idKlinike=null;
    var selectedDate=this.date.value;
    selectedDate=moment(selectedDate).format('D.M.YYYY.');
    this.zahtev.datum=selectedDate
    this.zahtev.start='00:00';
    this.zahtev.finis='23:59';
    //this.zahtev.specijalizacija='Kardiolog';
    this.listaKlinikaService.getSlobodneKlinike(this.zahtev).subscribe(
      podaci => {this.klinike = podaci; },
      err => console.log('Nisu ucitane klinike'),
      () => console.log('Uspesno ucitane klinike')
    );

  }

  onSelect(klinika: Klinika): void {
    this.selectedKlinika = klinika;
    this.zahtev.idKlinike=klinika.id;
  }
  ngOnInit() {
    this.getKlinike();
    this.getTipovi();
  }

  getTipovi(){
    this.listaKlinikaService.findTipovi().subscribe(
      podaci => {this.tipovi= podaci; },
      err => console.log('Nisu ucitani tipovi'),
      () => console.log('Uspesno ucitani tipovi')
    );
  }
onChange(selected){
  this.zahtev.specijalizacija=selected;
   this.zahtev.idKlinike=null;
    this.zahtev.start='00:00';
    this.zahtev.finis='23:59';
    //this.zahtev.specijalizacija='Kardiolog';
    this.listaKlinikaService.getSlobodneKlinike(this.zahtev).subscribe(
      podaci => {this.klinike = podaci; },
      err => console.log('Nisu ucitane klinike'),
      () => console.log('Uspesno ucitane klinike')
    );
}
  lekariNavigate(event){
    var testdatum=5; //bice pokupljen iz dejtpikera
    var selectedDate=this.date.value;
    selectedDate=moment(selectedDate).format('D.M.YYYY.');
    console.log(selectedDate);
    let klinika=this.selectedKlinika;{
      this.router.navigate(['/listaLekara'],{state:{klinika:klinika,zahtev:this.zahtev}});
    }
  }


  profilNavigate(event){

    let klinika=this.selectedKlinika;
      this.router.navigate(['/profilKlinike'],{state:{data:klinika}});
  }

}
