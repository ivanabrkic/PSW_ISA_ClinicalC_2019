import { Component, OnInit } from '@angular/core';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { Sala } from 'src/app/models/sala/sala';
import { RegistracijaSalaComponent } from '../registracija-sala/registracija-sala.component';

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
    this.klinikaService.removeSala(event.target.id).subscribe(data => {
        alert(data.naziv)
        this.klinikaService.getSale(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.sale = data;
        });
    },
      error => {
        alert('Sala trenutno ne može biti uklonjena!\n\n');
      });
  }

  showDialogRegister() {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '400px';
    dialogConfig.height = '400px';

    this.registerDialog = this.dialog.open(RegistracijaSalaComponent, dialogConfig);
    this.registerDialog.afterClosed().subscribe(() => {
      this.klinikaService.getSale(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.sale = data;
        });
    });
  }

}
