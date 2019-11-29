import {Klinike} from './tempLista';
import {Component, OnInit} from '@angular/core';
import {Klinika} from '../../../models/Klinika/klinika';

@Component({
  selector: 'app-lista-klinika',
  templateUrl: './lista-klinikaComponent.html',
  styleUrls: ['/lista-klinikaComponent.css']
})

export class ListaKlinikaComponent implements OnInit{
  private klinike: Klinika[];
  selectedKlinika: Klinika;
  onSelect(klinika: Klinika): void {
    this.selectedKlinika = klinika;
  }
  ngOnInit(): void {
    this.klinike = Klinike;
  }
}
