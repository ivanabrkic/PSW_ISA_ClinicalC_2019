import { Component, OnInit, Inject, ElementRef, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { MedicinskaSestra } from 'src/app/models/medicinskas/medicinskas';
import { Lekar } from 'src/app/models/lekar/lekar';
import { stringify } from 'querystring';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'detalji',
  templateUrl: './detalji.component.html',
  styleUrls: ['./detalji.component.css']
})
export class DetaljiComponent implements OnInit {

  medSestra : MedicinskaSestra;
  lekar : Lekar;
  radnoVreme : string;
  tip : string;

  constructor(private dialogRef: MatDialogRef<DetaljiComponent>,
              @Inject(MAT_DIALOG_DATA) data) 
    {
      if(data.tip == 'Medicinska sestra'){
        this.medSestra = data.object;
        this.radnoVreme = this.medSestra.radnoVreme.replace(/\//ig," <br/> ");
      } 
      else{
        this.lekar = data.object;
        this.radnoVreme = this.lekar.radnoVreme.replace(/\//ig," <br/> ");
      }
      this.tip = data.tip;
    }

  ngOnInit() {
    this.dialogRef.updatePosition({
      top: '10%',
      left: '40%'
    });
  }

}
