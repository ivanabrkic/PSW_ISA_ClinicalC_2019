import { Component, OnInit } from '@angular/core';
import { AdminKlinikeService } from 'src/app/modules/shared/services/admin-klinike-service/admin-klinike.service';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { Lekar } from 'src/app/models/lekar/lekar';
import { MedicinskaSestra } from 'src/app/models/medicinskas/medicinskas';
import { MedicinskaSestraService } from 'src/app/modules/shared/services/medicinska-sestra-service/medicinska-sestra.service';
import { LekarService } from 'src/app/modules/shared/services/lekar-service/lekar.service';
import { MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material';
import { DetaljiComponent } from '../detalji/detalji.component';
import { RegistracijaMedicinskogOsobljaComponent } from '../registracija-medicinskog-osoblja/registracija-medicinskog-osoblja.component';

@Component({
  templateUrl: './tabela-medicinskog-osoblja.component.html',
  styleUrls: ['./tabela-medicinskog-osoblja.component.css']
})
export class TabelaMedicinskogOsobljaComponent implements OnInit {

  adminKlinike: AdministratorKlinike = new AdministratorKlinike();
  lekari: Lekar[] = [];
  medicinskeSestre: MedicinskaSestra[] = [];
  registerDialog: any;

  constructor(public dialog: MatDialog, private lekarService: LekarService, private medSestraService: MedicinskaSestraService, private adminkService: AdminKlinikeService) {
    this.adminkService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.adminKlinike = ulogovanKorisnik;

        this.lekarService.getLekariKlinike(this.adminKlinike.klinika.id)
          .subscribe(data => {
            this.lekari = data;
          });
        this.medSestraService.getMedicinskeSestreKlinike(this.adminKlinike.klinika.id)
          .subscribe(data => {
            this.medicinskeSestre = data;
          });

      });

  }

  ngOnInit() {
    this.adminkService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.adminKlinike = ulogovanKorisnik;

        this.lekarService.getLekariKlinike(this.adminKlinike.klinika.id)
          .subscribe(data => {
            this.lekari = data;
          });
        this.medSestraService.getMedicinskeSestreKlinike(this.adminKlinike.klinika.id)
          .subscribe(data => {
            this.medicinskeSestre = data;
          });
      });

  }

  showDialogLekar(med: Lekar) {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '500px';
    dialogConfig.height = '350px';

    dialogConfig.data = {
      object: med,
      tip: 'Lekar'
    };

    const dialogRef = this.dialog.open(DetaljiComponent, dialogConfig);
  }

  showDialogSestra(med: MedicinskaSestra) {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '500px';
    dialogConfig.height = '350px';
    dialogConfig.data = {
      object: med,
      tip: 'Medicinska sestra'
    };

    const dialogRef = this.dialog.open(DetaljiComponent, dialogConfig);
  }

  showDialogRegister() {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '800px';
    dialogConfig.height = '600px';

    this.registerDialog = this.dialog.open(RegistracijaMedicinskogOsobljaComponent, dialogConfig);
    this.registerDialog.afterClosed().subscribe(() => {
      this.lekarService.getLekariKlinike(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.lekari = data;
        });
      this.medSestraService.getMedicinskeSestreKlinike(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.medicinskeSestre = data;
        });
    });
  }

  removeLekar(event: any) {
    this.lekarService.remove(event.target.id).subscribe(data => {
      if (data == null) {
        alert("Uspešno ste otpustili lekara!")
        this.lekarService.getLekariKlinike(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.lekari = data;
        });
      }
    },
      error => {
        alert('Lekar ne može trenutno biti otpušten!\n\n');
      });
  }

  removeMedSestra(event: any) {
    this.medSestraService.remove(event.target.id).subscribe(data => {
      if (data == null) {
        alert("Uspešno ste otpustili medicinsku sestru!")
        this.medSestraService.getMedicinskeSestreKlinike(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.medicinskeSestre = data;
        });
      }
    },
      error => {
        alert('Medicinska sestra ne može trenutno biti otpuštena!\n\n');
      });
  }

}
