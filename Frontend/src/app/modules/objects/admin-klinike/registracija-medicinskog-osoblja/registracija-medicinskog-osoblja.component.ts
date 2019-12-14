import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { Vreme } from 'src/app/models/vreme/vreme';

@Component({
  templateUrl: './registracija-medicinskog-osoblja.component.html',
  styleUrls: ['./registracija-medicinskog-osoblja.component.css']
})
export class RegistracijaMedicinskogOsobljaComponent implements OnInit {
  
  selected : Vreme = new Vreme();

  constructor(private dialogRef: MatDialogRef<RegistracijaMedicinskogOsobljaComponent>) { 
    this.selected.dan = "Ponedeljak";
  }

  ngOnInit() {
    this.selected.dan = "Ponedeljak";

    this.dialogRef.updatePosition({
      top: '5%',
      left: '20%'
    });
  }

}
