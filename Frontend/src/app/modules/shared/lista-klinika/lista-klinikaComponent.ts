import {Klinike} from './tempLista';
import {Component, OnInit} from '@angular/core';
import {Klinika} from '../../../models/klinika/klinika';
import {ListaKlinikaService} from '../../../_services/ListaKlinikaService/lista-klinika-service.service';
import {Observable, Subscription} from 'rxjs';

@Component({
  selector: 'app-lista-klinika',
  templateUrl: './lista-klinikaComponent.html',
  styleUrls: ['/lista-klinikaComponent.css']
})

export class ListaKlinikaComponent implements OnInit {
  private klinike: Klinika[];
  selectedKlinika: Klinika;

  constructor(private listaKlinikaService: ListaKlinikaService) {
  }

  getKlinike() {
    this.listaKlinikaService.findAll().subscribe(
      podaci => {this.klinike = podaci; },
      err => console.log('Nisu ucitani korisnici'),
      () => console.log('Uspesno ucitani korisnici')
    );
  }

  onSelect(klinika: Klinika): void {
    this.selectedKlinika = klinika;
  }
  ngOnInit() {
    this.getKlinike();
  }
}
