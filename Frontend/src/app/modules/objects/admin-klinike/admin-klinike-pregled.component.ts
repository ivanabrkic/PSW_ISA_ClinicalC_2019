import { Component, OnInit } from '@angular/core';
import { AdminKlinikeService } from 'src/app/_services/admin-klinike-service/admin-klinike.service';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';

@Component({
  templateUrl: './admin-klinike-pregled.component.html',
  styleUrls: ['./admin-klinike-pregled.component.css']
})
export class AdminKlinikePregledComponent implements OnInit {

  adminKlinike: AdministratorKlinike = new AdministratorKlinike();

  constructor(private adminkService: AdminKlinikeService) { 
    this.adminkService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.adminKlinike = ulogovanKorisnik;
    });
  }

  ngOnInit() {
    this.adminkService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.adminKlinike = ulogovanKorisnik;
    });
  }

}
