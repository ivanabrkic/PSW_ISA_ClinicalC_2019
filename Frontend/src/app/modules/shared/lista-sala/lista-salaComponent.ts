
import { Component, OnInit, ViewChild } from '@angular/core';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { MatDialog, MatDialogConfig, MatTableDataSource, MatSort, MatSnackBar } from '@angular/material';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { Sala } from 'src/app/models/sala/sala';
import { RadniKalendarSaleComponent } from 'src/app/modules/shared/radni-kalendar-sale/radni-kalendar-sale-component/radni-kalendar-sale.component';
import { first } from 'rxjs/operators';
import { Klinika } from 'src/app/models/klinika/klinika';

@Component({
  templateUrl: './lista-salaComponent.html',
  styleUrls: ['./lista-salaComponent.css']
})
export class ListaSalaComponent implements OnInit {

  sale: Sala[] = []
  registerDialog: any;
  operacije: any;
  pregledi: any;
  klinika:Klinika;
  //displayedColumns: string[] = ['naziv', 'broj', 'Kalendar', ];
  displayedColumns: string[] = ['naziv', 'broj', ];
  dataSource: any

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(private _snackBar: MatSnackBar, public dialog: MatDialog, private klinikaService: KlinikaService, private adminkService: AdminKlinikeService) {

    this.klinika=new Klinika();
    this.klinika=history.state.klinika;
        this.klinikaService.getSale(this.klinika.id)
          .subscribe(data => {
            this.sale = data;
            this.dataSource = new MatTableDataSource(this.sale);
            this.dataSource.sort = this.sort;
          });
  }

  ngOnInit() {

        this.klinika=new Klinika();
        this.klinika=history.state.klinika;
        this.klinikaService.getSale(this.klinika.id)
          .subscribe(data => {
            this.sale = data;
            this.dataSource = new MatTableDataSource(this.sale);
            this.dataSource.sort = this.sort;
          });
  }

 s

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
