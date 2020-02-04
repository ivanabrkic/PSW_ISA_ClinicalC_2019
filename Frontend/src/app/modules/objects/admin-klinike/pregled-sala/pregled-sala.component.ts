import { Component, OnInit, ViewChild } from '@angular/core';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { MatDialog, MatDialogConfig, MatTableDataSource, MatSort, MatSnackBar } from '@angular/material';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { Sala } from 'src/app/models/sala/sala';
import { RegistracijaSalaComponent } from '../registracija-sala/registracija-sala.component';
import { RadniKalendarSaleComponent } from 'src/app/modules/shared/radni-kalendar-sale/radni-kalendar-sale-component/radni-kalendar-sale.component';

@Component({
  templateUrl: './pregled-sala.component.html',
  styleUrls: ['./pregled-sala.component.css']
})
export class PregledSalaComponent implements OnInit {

  adminKlinike: AdministratorKlinike = new AdministratorKlinike();
  sale: Sala[] = []
  registerDialog: any;
  operacije: any;
  pregledi: any;

  displayedColumns: string[] = ['naziv', 'broj', 'Kalendar', 'Izmeni', 'Ukloni'];
  dataSource: any

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(private _snackBar: MatSnackBar, public dialog: MatDialog, private klinikaService: KlinikaService, private adminkService: AdminKlinikeService) {
    this.adminkService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.adminKlinike = ulogovanKorisnik;

        this.klinikaService.getSale(this.adminKlinike.klinika.id)
          .subscribe(data => {
            this.sale = data;
            this.dataSource = new MatTableDataSource(this.sale);
            this.dataSource.sort = this.sort;
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
            this.dataSource = new MatTableDataSource(this.sale);
            this.dataSource.sort = this.sort;
          });

      });
  }

  removeSala(event: any) {
    this.klinikaService.removeSala(event.target.id).subscribe(data => {
      alert(data.text)
      this.klinikaService.getSale(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.sale = data;
          this.dataSource = new MatTableDataSource(this.sale);
          this.dataSource.sort = this.sort;
        });
    },
      error => {
        alert('Sala trenutno ne može biti uklonjena!\n\n');
      });
  }

  updateSala(sala: Sala) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '500px';
    dialogConfig.height = '500px';

    dialogConfig.data = {
      izmeni: true,
      sala: sala
    };

    this.registerDialog = this.dialog.open(RegistracijaSalaComponent, dialogConfig);
    this.registerDialog.afterClosed().subscribe(() => {
      this.klinikaService.getSale(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.sale = data;
          this.dataSource = new MatTableDataSource(this.sale);
          this.dataSource.sort = this.sort;
        });
    });
  }


  showDialogRegister() {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '500px';
    dialogConfig.height = '500px';

    dialogConfig.data = {
      izmeni: false,
    };

    this.registerDialog = this.dialog.open(RegistracijaSalaComponent, dialogConfig);
    this.registerDialog.afterClosed().subscribe(() => {
      this.klinikaService.getSale(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.sale = data;
          this.dataSource = new MatTableDataSource(this.sale);
          this.dataSource.sort = this.sort;
        });
    });
  }

  openCalendar(id: Sala) {

    this.klinikaService.getOperacije(id)
      .subscribe(data => {
        this.operacije = data;
        this.klinikaService.getPregledi(id)
          .subscribe(data => {
            this.pregledi = data;
            const dialogConfig = new MatDialogConfig();

            dialogConfig.disableClose = true;
            dialogConfig.autoFocus = true;
            dialogConfig.width = '800px';
            dialogConfig.height = '600px';

            dialogConfig.data = {
              operacije: this.operacije,
              pregledi: this.pregledi
            };

            this.registerDialog = this.dialog.open(RadniKalendarSaleComponent, dialogConfig);
          });
      });
  }

  
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }


}
