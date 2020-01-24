import { Component, OnInit } from '@angular/core';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { MatDialog } from '@angular/material';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { Sala } from 'src/app/models/sala/sala';

@Component({
  templateUrl: './pregled-sala.component.html',
  styleUrls: ['./pregled-sala.component.css']
})
export class PregledSalaComponent implements OnInit {

  adminKlinike: AdministratorKlinike = new AdministratorKlinike();
  sale: Sala[] = []
  registerDialog: any;

  constructor(public dialog: MatDialog, private klinikaService: KlinikaService, private adminkService: AdminKlinikeService) { 
    this.adminkService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.adminKlinike = ulogovanKorisnik;

      this.klinikaService.getSale(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.sale = data;
        });

    });
  }

  ngOnInit() {
    this.adminkService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.adminKlinike = ulogovanKorisnik;

      this.klinikaService.getSale(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.sale = data;
        });

    });
  }

  removeSala(event: any) {
    this.klinikaService.remove(event.target.id).subscribe(data => {
      if (data == null) {
        alert("Uspešno ste uklonili salu!")
        this.klinikaService.getSale(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.sale = data;
        });
      }
    },
      error => {
        alert('Sala trenutno ne može biti uklonjena!\n\n');
      });
  }

}
