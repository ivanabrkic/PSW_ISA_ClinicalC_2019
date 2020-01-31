import { Component, OnInit, Inject } from '@angular/core';
import { Lekar } from 'src/app/models/lekar/lekar';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DetaljiComponent } from '../detalji/detalji.component';
import { Sala } from 'src/app/models/sala/sala';

@Component({
  selector: 'app-zakazi-termin',
  templateUrl: './zakazi-termin.component.html',
  styleUrls: ['./zakazi-termin.component.css']
})
export class ZakaziTerminComponent implements OnInit {

  slobodniLekari: Lekar[]
  sala: Sala
  selectedLekar : Lekar
  tip: String
  operator:String

  constructor(private dialogRef: MatDialogRef<ZakaziTerminComponent>,
    @Inject(MAT_DIALOG_DATA) data) {
      this.slobodniLekari = data.lekari
      this.sala = data.sala
      this.selectedLekar = this.slobodniLekari[0]
      this.tip = data.tip
      this.operator = data.jboLekara
  }

  ngOnInit() {
    this.dialogRef.updatePosition({
      top: '10%',
      left: '40%'
    });
  }

  onChange(newValue) {
    this.selectedLekar = newValue;
  }

}
