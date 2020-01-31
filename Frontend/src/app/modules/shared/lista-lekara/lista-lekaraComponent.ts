import {Component, OnInit} from '@angular/core';
import {Klinika} from '../../../models/klinika/klinika';
import {Observable, Subscription} from 'rxjs';
import { ListaKlinikaService } from 'src/app/services/lista-klinika-service/lista-klinika.service';
import { Lekar } from 'src/app/models/lekar/lekar';
import { LekarService } from 'src/app/services/lekar-service/lekar.service';

@Component({
  selector: 'app-lista-lekara',
  templateUrl: './lista-lekaraComponent.html',
  styleUrls: ['/lista-lekaraComponent.css']
})



export class ListaLekaraComponent implements OnInit {
  private klinika: Klinika;
  private lekari: Lekar[];  
  private terminiSakriveni:boolean=false;

  constructor(private listaLekaraService: LekarService) {
  }

  getLekari() {
    this.listaLekaraService.getLekariKlinike(this.klinika.id).subscribe(
      podaci => {this.lekari = podaci; },
      err => console.log('Nisu ucitani lekari'),
      () => console.log('Uspesno ucitani lekari')
    );
  }

  ngOnInit() {
    console.log(history.state.testdatum);
    this.klinika=history.state.klinika;
    if(history.state.testdatum==0)
      this.terminiSakriveni=true;
    else
      this.terminiSakriveni=false;
    console.log(this.klinika);
    this.getLekari();
    console.log(this.lekari);
  }

 
}
