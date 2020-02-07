import { Component, OnInit } from '@angular/core';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';

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
