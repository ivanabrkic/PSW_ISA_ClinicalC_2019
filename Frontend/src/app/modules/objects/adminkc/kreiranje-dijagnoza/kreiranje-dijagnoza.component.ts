import { Component, OnInit } from '@angular/core';
import {Dijagnoza} from '../../../../models/Dijagnoza/dijagnoza';

@Component({
  selector: 'app-kreiranje-dijagnoza',
  templateUrl: './kreiranje-dijagnoza.component.html',
  styleUrls: ['./kreiranje-dijagnoza.component.css']
})
export class KreiranjeDijagnozaComponent implements OnInit {
  dijagnoze = []
  novaDijagnoza: Dijagnoza
  constructor() { }

  ngOnInit() {
  }

  dodajDijagnozu(sifra, naziv) {
    this.novaDijagnoza = new Dijagnoza(sifra, naziv);
    console.log(sifra, naziv)
    this.dijagnoze.push(this.novaDijagnoza);
  }
}
