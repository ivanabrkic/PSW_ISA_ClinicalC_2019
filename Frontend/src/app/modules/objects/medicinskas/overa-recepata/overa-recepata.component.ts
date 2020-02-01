import { Component, OnInit } from '@angular/core';
import {Recept} from '../../../../models/Recept/recept';
import {MatDialog} from '@angular/material/dialog';
import {ReceptServiceService} from '../../../shared/services/recept-service/recept-service.service';


@Component({
  selector: 'app-overa-recepata',
  templateUrl: './overa-recepata.component.html',
  styleUrls: ['./overa-recepata.component.css']
})
export class OveraRecepataComponent implements OnInit {
  recepti: any;
  public izmenjeniRecept: Recept = new Recept();

  constructor(public dialog: MatDialog, private receptService: ReceptServiceService) {
    this.receptService.getNeoverene()
      .subscribe(data => {
          // @ts-ignore
        this.recepti = data;
        console.log(data);
        }
      );
  }


  ngOnInit() {
    this.receptService.getNeoverene()
      .subscribe(data => {
          // @ts-ignore
          this.recepti = data;
        }
      );
  }

  getRecepte(){
    this.receptService.getNeoverene().subscribe(
      podaci => { this.recepti = podaci; },
      err => console.log('Nisu ucitani korisnici'),
      () => console.log(this.recepti)
    );
  }

  Overi(r: Recept) {
    const indexRecepta = this.recepti.findIndex(item => (item.broj === r.broj && item.pacijent === r.pacijent));
    this.izmenjeniRecept = this.recepti.find(item => (item.broj === r.broj && item.pacijent === r.pacijent));
    this.izmenjeniRecept.overen = true;
    this.recepti[indexRecepta] = this.izmenjeniRecept;
    this.receptService.overi(this.izmenjeniRecept).subscribe(
      data => r,
      err => console.log('Neuspesno overen recept'),
      () => this.getRecepte()
    );
  }

  Ponisti(r: Recept){
    const indexRecepta = this.recepti.findIndex(item => (item.broj === r.broj && item.pacijent === r.pacijent));
    this.izmenjeniRecept = this.recepti.find(item => (item.broj === r.broj && item.pacijent === r.pacijent));
    console.log(this.izmenjeniRecept);
    this.receptService.Obrisi(this.izmenjeniRecept).subscribe(
      data => r,
      err => console.log('Neuspesno obrisan recept'),
      () => this.getRecepte()
    );
  }

}
