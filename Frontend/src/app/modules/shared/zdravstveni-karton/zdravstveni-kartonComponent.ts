import { Component, OnInit } from '@angular/core';
import { Pacijent } from 'src/app/models/pacijent/pacijent';
import { PacijentService } from 'src/app/services/pacijent-service/pacijent.service';
import { ZdravstveniKartonService } from 'src/app/services/zdravstveni-karton-service/zdravstveni-karton.service';

@Component({
  selector: 'app-zdravstveni-karton',
  templateUrl: './zdravstveni-kartonComponent.html',
  styleUrls: ['/zdravstveni-kartonComponent.css']
})

export class ZdravstveniKartonComponent implements OnInit {
  // private karton: ZdravstveniKarton;
  private dijagnoze: String[];
  private pacijent: Pacijent=new Pacijent();

  constructor( private pacijentService: PacijentService, private zkartonService: ZdravstveniKartonService) {
    this.pacijentService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.pacijent = ulogovanKorisnik;
      this.zkartonService.get(this.pacijent.id)
        .subscribe(dijag => {
        this.dijagnoze =dijag;
        console.log(this.dijagnoze);
      });
    });
 
  }

  ngOnInit() {

    this.pacijentService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.pacijent = ulogovanKorisnik;
      this.zkartonService.get(this.pacijent.id)
        .subscribe(dijag => {
        this.dijagnoze =dijag;
        console.log(this.dijagnoze);
      });
    });

  

  }
}
