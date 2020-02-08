import { Component, OnInit } from '@angular/core';
import { Lekar } from 'src/app/models/lekar/lekar';
import { LekarService } from 'src/app/services/lekar-service/lekar.service';

@Component({
  templateUrl: './lekarPregled.component.html',
  styleUrls: ['./lekarPregled.component.css']
})
export class LekarPregledComponent implements OnInit {

  lekar: Lekar = new Lekar();

  constructor(private lekarService : LekarService) {
    this.lekarService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.lekar = ulogovanKorisnik;
    });
  }

  ngOnInit() {
    this.lekarService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.lekar = ulogovanKorisnik;
    });
  }

}
