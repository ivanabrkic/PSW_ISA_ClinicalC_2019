import { Component, OnInit } from '@angular/core';
import {Lekovi} from '../../../../models/Lekovi/lekovi';
import { LekoviService } from 'src/app/services/LekoviService/lekovi.service';

@Component({
  selector: 'app-kreiranje-lekova',
  templateUrl: './kreiranje-lekova.component.html',
  styleUrls: ['./kreiranje-lekova.component.css']
})
export class KreiranjeLekovaComponent implements OnInit {
  sifre = []
  lekovi: any;
  novLek: Lekovi;
  constructor(private lekoviService: LekoviService) { }

  ngOnInit() {
    this.getLekove();
  }

  getLekove(){
    this.lekoviService.get().subscribe(
      result => this.lekovi = result,
      err => console.log('greska'),
      () => console.log('uspeh')
    );
  }

  dodajLek(sifra, naziv) {
    if (this.sifre.indexOf(sifra) !== -1) {
      alert('Vec postoji lek sa sifrom ' + sifra);
      return;
    }

    this.sifre.push(sifra);

    this.novLek = new Lekovi(sifra, naziv);
    console.log(this.novLek);
    this.lekovi.push(this.novLek)
    this.lekoviService.save(this.novLek).subscribe(
      value => alert('Dodavanje dijagnoze'),
      err => console.log('Neuspesno unosenje leka'),
      () => alert('Dodata dijagnoza')
    );
  }

}
