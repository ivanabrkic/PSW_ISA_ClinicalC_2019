import { Component, OnInit } from '@angular/core';
import { AdministratorKlinike } from 'src/app/models/administrator-klinike';
import { AdminKlinikeServiceService } from 'src/app/_services/AdministratorKlinikeService/admin-klinike-service.service';

@Component({
  templateUrl: './administratorKlinikePregled.component.html',
  styleUrls: ['./administrator_klinike.component.css']
})
export class AdministratorKlinikePregledComponent implements OnInit {

  adminKlinike: AdministratorKlinike = new AdministratorKlinike();

  constructor(private adminkService: AdminKlinikeServiceService) { 
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
