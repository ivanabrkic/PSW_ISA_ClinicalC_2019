import { Component, OnInit } from '@angular/core';
import {Dijagnoza} from '../../../../models/Dijagnoza/dijagnoza';
import { DijagnozaService } from 'src/app/services/DijagnozaService/dijagnoza.service';

@Component({
  selector: 'app-kreiranje-dijagnoza',
  templateUrl: './kreiranje-dijagnoza.component.html',
  styleUrls: ['./kreiranje-dijagnoza.component.css']
})
export class KreiranjeDijagnozaComponent implements OnInit {
  dijagnoze: any;
  sifre = [];
  novaDijagnoza: Dijagnoza;
  constructor(
    private dijagnozaService: DijagnozaService) { }

  ngOnInit() {
    this.getDijagnoze();
  }

  getDijagnoze(){
    this.dijagnozaService.get().subscribe(
      result => this.dijagnoze = result,
      err => console.log('Nema'),
      () => console.log('Uspeh')
    );


  }

  dodajDijagnozu(sifra, naziv) {
    if (this.sifre.indexOf(sifra) !== -1) {
      alert('Vec postoji dijagnoza sa sifrom ' + sifra);
      return;
    }

    this.sifre.push(sifra);

    this.novaDijagnoza = new Dijagnoza(sifra, naziv);
    console.log(this.novaDijagnoza);
    this.dijagnoze.push(this.novaDijagnoza)
    this.dijagnozaService.save(this.novaDijagnoza).subscribe(
      value => alert('Dodavanje dijagnoze'),
      err => alert('Neuspesno unosenje dijagnoze'),
      () => alert('Dodata dijagnoza')
    );
  }
}
