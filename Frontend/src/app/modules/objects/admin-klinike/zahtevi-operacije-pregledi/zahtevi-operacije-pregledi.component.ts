import { Component, OnInit, ViewChild } from '@angular/core';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { MatSort, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-zahtevi-operacije-pregledi',
  templateUrl: './zahtevi-operacije-pregledi.component.html',
  styleUrls: ['./zahtevi-operacije-pregledi.component.css']
})
export class ZahteviOperacijePreglediComponent implements OnInit {

  adminKlinike: AdministratorKlinike = new AdministratorKlinike();
  zahtevi: Zahtev[] = []
  izbor: boolean = false
  selectedZahtev:Zahtev;


  displayedColumns: string[] = ['tipPosete', 'stavkaCenovnika', 'tipPosiljaoca', 'posiljalacImePrezime', 'datum', 'jbo','dodatneInformacije','izaberiSalu'];
  dataSource: any
  @ViewChild(MatSort, { static: true }) sort: MatSort;


  constructor(private klinikaService: KlinikaService, private adminkService: AdminKlinikeService) { 
    this.dataSource = new MatTableDataSource(null);
  }

  ngOnInit() {
    this.adminkService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.adminKlinike = ulogovanKorisnik;
      this.klinikaService.getZahtevi(this.adminKlinike.klinika.id)
      .subscribe(data => {
        this.zahtevi = data;
        this.dataSource = new MatTableDataSource(this.zahtevi);
        this.dataSource.sort = this.sort;
      }); 
    });

  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  izborSale(zahtev:Zahtev){
    this.izbor = true
    this.selectedZahtev = zahtev
  }

  zahtevObradjenHandler(obradjen : boolean){
    if (!obradjen){
      this.klinikaService.getZahtevi(this.adminKlinike.klinika.id)
      .subscribe(data => {
          this.zahtevi = data;
          this.dataSource = new MatTableDataSource(this.zahtevi);
          this.dataSource.sort = this.sort;
          this.izbor = obradjen
    });     
    }
  }

}
