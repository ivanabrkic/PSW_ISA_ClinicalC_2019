import {Component, OnInit} from '@angular/core';
import { PacijentService } from 'src/app/_services/PacijentService/pacijent.service';
import { Pacijent } from 'src/app/models/pacijent/pacijent';
import { ZdravstveniKartonService } from 'src/app/_services/ZdravstveniKartonService/zdravstveni-karton-service.service';


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
