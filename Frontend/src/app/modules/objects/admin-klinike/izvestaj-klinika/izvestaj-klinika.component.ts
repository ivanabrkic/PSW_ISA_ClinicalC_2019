import { Component, OnInit, ViewChild } from '@angular/core';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { Lekar } from 'src/app/models/lekar/lekar';
import { MatSort, MatSnackBar, MatDialog, MatTableDataSource } from '@angular/material';
import { LekarService } from 'src/app/services/lekar-service/lekar.service';
import { MedicinskaSestraService } from 'src/app/services/medicinska-sestra-service/medicinska-sestra.service';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { Prihod } from 'src/app/models/prihodi/prihodi';

@Component({
  selector: 'app-izvestaj-klinika',
  templateUrl: './izvestaj-klinika.component.html',
  styleUrls: ['./izvestaj-klinika.component.css']
})
export class IzvestajKlinikaComponent implements OnInit {

  displayedColumns: string[] = ['specijalizacija', 'ocena', 'ime', 'jbo'];
  dataSource: any

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  adminKlinike: AdministratorKlinike = new AdministratorKlinike();
  lekari: Lekar[] = [];

  public od: string;
  public do: string;

  public primaryXAxis: Object;
  public chartData: Object[] = [];
  public title: string;
  public primaryYAxis: Object;

  public zoom: Object;


  constructor(private _snackBar: MatSnackBar, public dialog: MatDialog, private klinikaService: KlinikaService, private lekarService: LekarService, private medSestraService: MedicinskaSestraService, private adminkService: AdminKlinikeService) {
    this.dataSource = new MatTableDataSource(null);
  }

  ngOnInit() {
    this.adminkService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.adminKlinike = ulogovanKorisnik;

        this.lekarService.getLekariKlinike(this.adminKlinike.klinika.id)
          .subscribe(data => {
            this.lekari = data;
            this.dataSource = new MatTableDataSource(this.lekari);
            this.dataSource.sort = this.sort;
          });


        this.klinikaService.getDnevniBroj(this.adminKlinike.klinika.id).subscribe(data => {
          var i = 0
          this.chartData = []
          for (i = 0; i < data.length; i++) {
            var godina = parseInt(data[i].vreme.split('.')[2])
            var mesec = parseInt(data[i].vreme.split('.')[1])
            var dan = parseInt(data[i].vreme.split('.')[0])
            this.chartData.push({ x: new Date(godina, mesec - 1, dan), y: data[i].vrednost })
          }

          this.primaryXAxis = {
            valueType: 'DateTime',
            title: 'Dan',
            labelFormat: 'dd-MM-yyyy',
            intervalType: 'Days'
          };
          this.primaryYAxis = {
            title: 'Broj pregleda'
          };
          this.title = 'Na dnevnom nivou';
          this.zoom = {
            enableMouseWheelZooming: true,
            enablePinchZooming: true,
            enableSelectionZooming: true
          }

        })

      });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  prihod: number

  getPrihod() {
    var datumOd = new Date(this.od)
    var mesec = datumOd.getMonth() + 1
    var stringOd = datumOd.getDate() + "." + mesec + "." + datumOd.getFullYear() + "."

    var datumDo = new Date(this.do)
    var mesecDo = datumDo.getMonth() + 1
    var stringDo = datumDo.getDate() + "." + mesecDo + "." + datumDo.getFullYear() + "."

    var prihodDTO = new Prihod()
    prihodDTO.pocetak = stringOd
    prihodDTO.kraj = stringDo
    prihodDTO.idKlinike = this.adminKlinike.klinika.id

    this.klinikaService.getPrihodi(prihodDTO).subscribe(data => {
      this.prihod = data.prihod
    })

  }

  dnevniNivo(){

    this.klinikaService.getDnevniBroj(this.adminKlinike.klinika.id).subscribe(data => {
      var i = 0
      this.chartData = []
      for (i = 0; i < data.length; i++) {
        var godina = parseInt(data[i].vreme.split('.')[2])
        var mesec = parseInt(data[i].vreme.split('.')[1])
        var dan = parseInt(data[i].vreme.split('.')[0])
        this.chartData.push({ x: new Date(godina, mesec - 1, dan), y: data[i].vrednost })
      }

      this.primaryXAxis = {
        valueType: 'DateTime',
        title: 'Dan',
        labelFormat: 'dd-MM-yyyy',
        intervalType: 'Days'
      };
      this.primaryYAxis = {
        title: 'Broj pregleda'
      };
      this.title = 'Na dnevnom nivou';
      this.zoom = {
        enableMouseWheelZooming: true,
        enablePinchZooming: true,
        enableSelectionZooming: true
      }

    })

  }

  nedeljniNivo(){

    this.klinikaService.getNedeljniBroj(this.adminKlinike.klinika.id).subscribe(data => {
      var i = 0
      this.chartData = []
      for (i = 0; i < data.length; i++) {
        var godina = parseInt(data[i].vreme.split('.')[2])
        var mesec = parseInt(data[i].vreme.split('.')[1])
        var dan = parseInt(data[i].vreme.split('.')[0])
        this.chartData.push({ x: new Date(godina, mesec - 1, dan), y: data[i].vrednost })
      }

      this.primaryXAxis = {
        valueType: 'DateTime',
        title: 'Nedelja',
        labelFormat: 'dd-MM-yyyy',
        intervalType: 'Weeks'
      };
      this.primaryYAxis = {
        title: 'Broj pregleda'
      };
      this.title = 'Na nedeljnom nivou';
      this.zoom = {
        enableMouseWheelZooming: true,
        enablePinchZooming: true,
        enableSelectionZooming: true
      }

    })

  }

  mesecniNivo(){

    this.klinikaService.getMesecniBroj(this.adminKlinike.klinika.id).subscribe(data => {
      var i = 0
      this.chartData = []
      for (i = 0; i < data.length; i++) {
        var godina = parseInt(data[i].vreme.split('.')[2])
        var mesec = parseInt(data[i].vreme.split('.')[1])
        var dan = parseInt(data[i].vreme.split('.')[0])
        this.chartData.push({ x: new Date(godina, mesec - 1, dan), y: data[i].vrednost })
      }

      this.primaryXAxis = {
        valueType: 'DateTime',
        title: 'Mesec',
        labelFormat: 'MMM',
        intervalType: 'Months'
      };
      this.primaryYAxis = {
        title: 'Broj pregleda'
      };
      this.title = 'Na mesečnom nivou';
      this.zoom = {
        enableMouseWheelZooming: true,
        enablePinchZooming: true,
        enableSelectionZooming: true
      }

    })

  }

}
