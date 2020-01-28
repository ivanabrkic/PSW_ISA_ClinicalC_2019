import { Component, OnInit } from '@angular/core';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { MatDialog } from '@angular/material';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';

@Component({
  selector: 'app-zahtevi-operacije-pregledi',
  templateUrl: './zahtevi-operacije-pregledi.component.html',
  styleUrls: ['./zahtevi-operacije-pregledi.component.css']
})
export class ZahteviOperacijePreglediComponent implements OnInit {

  adminKlinike: AdministratorKlinike = new AdministratorKlinike();
  zahtevi: Zahtev[] = []

  constructor(private klinikaService: KlinikaService, private adminkService: AdminKlinikeService) { 
    this.adminkService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.adminKlinike = ulogovanKorisnik;
        this.klinikaService.getZahtevi(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.zahtevi = data;
        }); 
      });


  }

  ngOnInit() {
    this.adminkService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.adminKlinike = ulogovanKorisnik;
      this.klinikaService.getZahtevi(this.adminKlinike.klinika.id)
      .subscribe(data => {
        this.zahtevi = data;
      }); 
    });

  }

  izborSale(zahtev:Zahtev){
    
  }

}
