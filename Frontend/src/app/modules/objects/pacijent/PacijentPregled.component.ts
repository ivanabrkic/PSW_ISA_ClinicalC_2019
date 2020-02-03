import { Component, OnInit } from '@angular/core';
import { Pacijent } from 'src/app/models/pacijent/pacijent';
import { PacijentService } from 'src/app/services/pacijent-service/pacijent.service';

@Component({
  templateUrl: './pacijentPregled.component.html',
  styleUrls: ['./pacijent.component.css']
})
export class PacijentPregledComponent implements OnInit {

 pacijent: Pacijent= new Pacijent();


  constructor(private pacijentService : PacijentService) {
    this.pacijentService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.pacijent= ulogovanKorisnik;
    });
   }

  ngOnInit() {
    this.pacijentService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.pacijent= ulogovanKorisnik;
    });
  }

}
