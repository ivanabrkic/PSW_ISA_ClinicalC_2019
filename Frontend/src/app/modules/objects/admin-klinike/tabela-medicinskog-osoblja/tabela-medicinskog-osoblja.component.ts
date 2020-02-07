import { Component, OnInit, ViewChild } from '@angular/core';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { Lekar } from 'src/app/models/lekar/lekar';
import { MedicinskaSestra } from 'src/app/models/medicinskas/medicinskas';
import { MedicinskaSestraService } from 'src/app/services/medicinska-sestra-service/medicinska-sestra.service';
import { LekarService } from 'src/app/services/lekar-service/lekar.service';
import { MatDialog, MatDialogConfig, MatDialogRef, MatTableDataSource, MatSort, MatSnackBar } from '@angular/material';
import { DetaljiComponent } from '../detalji/detalji.component';
import { RegistracijaMedicinskogOsobljaComponent } from '../registracija-medicinskog-osoblja/registracija-medicinskog-osoblja.component';
import { MedicinskoOsoblje } from 'src/app/models/medicinsko-osoblje/medicinsko-osoblje';
import { first } from 'rxjs/operators';

@Component({
  templateUrl: './tabela-medicinskog-osoblja.component.html',
  styleUrls: ['./tabela-medicinskog-osoblja.component.css']
})
export class TabelaMedicinskogOsobljaComponent implements OnInit {

  displayedColumns: string[] = ['tipKorisnika', 'specijalizacija', 'ocena', 'ime', 'email', 'kontaktTelefon', 'jbo', 'Ukloni'];
  dataSource: any

  adminKlinike: AdministratorKlinike = new AdministratorKlinike();
  lekari: Lekar[] = [];
  medicinskeSestre: MedicinskaSestra[] = [];
  registerDialog: any;

  medOsoblje: MedicinskoOsoblje[] = []

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(private _snackBar: MatSnackBar, public dialog: MatDialog, private lekarService: LekarService, private medSestraService: MedicinskaSestraService, private adminkService: AdminKlinikeService) {
    this.dataSource = new MatTableDataSource(null);
  }

  ngOnInit() {
    this.adminkService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.adminKlinike = ulogovanKorisnik;

        this.lekarService.getLekariKlinike(this.adminKlinike.klinika.id)
          .subscribe(data => {
            this.lekari = data;
            this.lekari.forEach(element => {
              this.medOsoblje.push(element)
            });
            this.medSestraService.getMedicinskeSestreKlinike(this.adminKlinike.klinika.id)
              .subscribe(data => {
                this.medicinskeSestre = data;
                this.medicinskeSestre.forEach(element => {
                  this.medOsoblje.push(element)
                });
                this.dataSource = new MatTableDataSource(this.medOsoblje);
                this.dataSource.sort = this.sort;
              });
          });
      });

  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
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
    this.registerDialog.afterClosed().subscribe(result => {
      if (result != true) {

        if (result.tipKorisnika == 'Lekar') {
          this.lekarService.register(result).pipe(first()).subscribe(result => {
            alert('Uspešno ste registrovali lekara!\n\n');

            this.lekarService.getLekariKlinike(this.adminKlinike.klinika.id)
              .subscribe(data => {
                this.lekari = data;
                this.medOsoblje = []
                this.lekari.forEach(element => {
                  this.medOsoblje.push(element)
                });
                this.medSestraService.getMedicinskeSestreKlinike(this.adminKlinike.klinika.id)
                  .subscribe(data => {
                    this.medicinskeSestre = data;
                    this.medicinskeSestre.forEach(element => {
                      this.medOsoblje.push(element)
                    });
                    this.dataSource = new MatTableDataSource(this.medOsoblje);
                    this.dataSource.sort = this.sort;
                  });
              });
          },
            error => {
              alert('Neuspešna registracija!\n\n');
            });
        }
        else {
          this.medSestraService.register(result).pipe(first()).subscribe(result => {
            alert('Uspešno ste registrovali medicinsku sestru!\n\n');

            this.lekarService.getLekariKlinike(this.adminKlinike.klinika.id)
              .subscribe(data => {
                this.lekari = data;
                this.medOsoblje = []
                this.lekari.forEach(element => {
                  this.medOsoblje.push(element)
                });
                this.medSestraService.getMedicinskeSestreKlinike(this.adminKlinike.klinika.id)
                  .subscribe(data => {
                    this.medicinskeSestre = data;
                    this.medicinskeSestre.forEach(element => {
                      this.medOsoblje.push(element)
                    });
                    this.dataSource = new MatTableDataSource(this.medOsoblje);
                    this.dataSource.sort = this.sort;
                  });
              });

          },
            error => {
              alert('Neuspešna registracija!\n\n');
            });
        }
      }
      ////////////////////////////////////////////////////
    });
  }

  removeLekar(event: any) {
    this.lekarService.remove(event.target.id).subscribe(data => {
      if (data == null) {
        alert("Uspešno ste otpustili lekara!")
        this.lekarService.getLekariKlinike(this.adminKlinike.klinika.id)
          .subscribe(data => {
            this.lekari = data;
            this.medOsoblje = []
            this.lekari.forEach(element => {
              this.medOsoblje.push(element)
            });
            this.medSestraService.getMedicinskeSestreKlinike(this.adminKlinike.klinika.id)
              .subscribe(data => {
                this.medicinskeSestre = data;
                this.medicinskeSestre.forEach(element => {
                  this.medOsoblje.push(element)
                });
                this.dataSource = new MatTableDataSource(this.medOsoblje);
                this.dataSource.sort = this.sort;
              });
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
        this.lekarService.getLekariKlinike(this.adminKlinike.klinika.id)
          .subscribe(data => {
            this.lekari = data;
            this.medOsoblje = []
            this.lekari.forEach(element => {
              this.medOsoblje.push(element)
            });
            this.medSestraService.getMedicinskeSestreKlinike(this.adminKlinike.klinika.id)
              .subscribe(data => {
                this.medicinskeSestre = data;
                this.medicinskeSestre.forEach(element => {
                  this.medOsoblje.push(element)
                });
                this.dataSource = new MatTableDataSource(this.medOsoblje);
                this.dataSource.sort = this.sort;
              });
          });
      }
    },
      error => {
        alert('Medicinska sestra ne može trenutno biti otpuštena!\n\n');
      });
  }

}
