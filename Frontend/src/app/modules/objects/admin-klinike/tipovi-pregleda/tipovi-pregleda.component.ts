import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatDialogConfig, MatDialog, MatSort } from '@angular/material';
import { TipPregleda } from 'src/app/models/tippregleda/tippregleda';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { RegistracijaSalaComponent } from '../registracija-sala/registracija-sala.component';
import { RegistracijaTipovaComponent } from '../registracija-tipova/registracija-tipova.component';

@Component({
  selector: 'app-tipovi-pregleda',
  templateUrl: './tipovi-pregleda.component.html',
  styleUrls: ['./tipovi-pregleda.component.css']
})
export class TipoviPregledaComponent implements OnInit {

  displayedColumns: string[] = ['naziv', 'specijalizacija', 'cena', 'Izmeni', 'Ukloni'];
  dataSource: any

  tipovi: TipPregleda[];
  adminKlinike: AdministratorKlinike = new AdministratorKlinike()

  registerDialog: any;

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(public dialog: MatDialog, private adminkService: AdminKlinikeService, private klinikaService: KlinikaService) {
          this.dataSource = new MatTableDataSource(null);
  }

  ngOnInit() {
    this.adminkService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.adminKlinike = ulogovanKorisnik;

        this.klinikaService.getTipovi(this.adminKlinike.klinika.id).subscribe(data => {
          this.tipovi = data
          this.dataSource = new MatTableDataSource(this.tipovi);
          this.dataSource.sort = this.sort;
        });
      });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  ukloniTip(tip: TipPregleda) {
    this.klinikaService.removeTip(tip.id).subscribe(data => {
      alert(data.text)
    });
  }

  izmeniTip(tip: TipPregleda) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '500px';
    dialogConfig.height = '500px';

    dialogConfig.data = {
      izmeni: true,
      tip: tip
    };

    this.registerDialog = this.dialog.open(RegistracijaTipovaComponent, dialogConfig);
    this.registerDialog.afterClosed().subscribe(() => {
      this.klinikaService.getTipovi(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.tipovi = data;
          this.dataSource = new MatTableDataSource(this.tipovi);
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

    this.registerDialog = this.dialog.open(RegistracijaTipovaComponent, dialogConfig);
    this.registerDialog.afterClosed().subscribe(() => {
      this.klinikaService.getTipovi(this.adminKlinike.klinika.id)
        .subscribe(data => {
          this.tipovi = data;
          this.dataSource = new MatTableDataSource(this.tipovi);
          this.dataSource.sort = this.sort;
        });
    });
  }

}
