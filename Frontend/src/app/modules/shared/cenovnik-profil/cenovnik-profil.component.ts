import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { TipPregleda } from 'src/app/models/tippregleda/tippregleda';
import { Klinika } from 'src/app/models/klinika/klinika';
import { MatSort, MatSnackBar, MatTableDataSource } from '@angular/material';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';

@Component({
  selector: 'app-cenovnik-profil',
  templateUrl: './cenovnik-profil.component.html',
  styleUrls: ['./cenovnik-profil.component.css']
})
export class CenovnikProfilComponent implements OnInit {

  displayedColumns: string[] = ['naziv', 'specijalizacija', 'cena'];
  dataSource: any

  tipovi: TipPregleda[];

  @Input() public klinika : Klinika

  registerDialog: any;

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(private _snackBar: MatSnackBar,  private klinikaService: KlinikaService) { 
    this.dataSource = new MatTableDataSource(null);
  }

  ngOnInit() {
    this.klinikaService.getTipovi(this.klinika.id).subscribe(data => {
      this.tipovi = data
      this.dataSource = new MatTableDataSource(this.tipovi);
      this.dataSource.sort = this.sort;
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }


}
