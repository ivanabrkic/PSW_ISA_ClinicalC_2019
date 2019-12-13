import { Component, OnInit } from '@angular/core';
import { Poseta } from 'src/app/models/poseta/poseta';
import { PoseteService } from 'src/app/_services/PoseteService/posete.service';
import { PacijentService } from 'src/app/_services/PacijentService/pacijent.service';
import { Pacijent } from 'src/app/models/pacijent/pacijent';
import { Korisnik } from 'src/app/models/korisnik';

@Component({
    selector: 'app-lista-poseta',
    templateUrl: './pregledi-operacije-lista.html',
    styleUrls: ['/pregledi-operacije-lista.css']
  })

  export class PoseteComponent implements OnInit {
    private posete: Poseta[];

    private pacijent: Pacijent=new Pacijent();
  
    constructor(private poseteService: PoseteService,private pacijentService: PacijentService) {
      this.pacijentService.getUlogovanKorisnik()
     .subscribe(ulogovanKorisnik => {
      this.pacijent = ulogovanKorisnik;
    });
    }
  
    getPosete() {
     console.log(this.pacijent.id);
     console.log(this.pacijent.kontaktTelefon);
      this.poseteService.findByPatientId(this.pacijent.id).subscribe(
        podaci => {this.posete = podaci; },
        err => console.log('Nisu ucitane posete'),
        () => console.log('Uspesno ucitane posete')
      );
    }
   
    ngOnInit() {
      this.pacijentService.getUlogovanKorisnik()
      .subscribe(
        ulogovanKorisnik => {
       this.pacijent = ulogovanKorisnik;},
       err => console.log('Greska pri ucitavanju korisnika'),
       );
      this.getPosete();
    }
  }
  