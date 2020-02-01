import { Component, OnInit, Inject } from '@angular/core';
import { Lekar } from 'src/app/models/lekar/lekar';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { MatDialogRef, MAT_DIALOG_DATA, MatListOption, MatTableDataSource } from '@angular/material';
import { DetaljiComponent } from '../detalji/detalji.component';
import { Sala } from 'src/app/models/sala/sala';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { $ } from 'protractor';
import { SelectionModel } from '@angular/cdk/collections';
import { LekarCheckBox } from 'src/app/models/lekar/lekarCheckBox';
import { Operacija } from 'src/app/models/operacija/operacija';

@Component({
  selector: 'app-zakazi-termin',
  templateUrl: './zakazi-termin.component.html',
  styleUrls: ['./zakazi-termin.component.css']
})
export class ZakaziTerminComponent implements OnInit {

  slobodniLekari: Lekar[]
  sala: Sala
  selectedLekar: Lekar
  tip: String
  operator: String

  master_checked: boolean = false;
  master_indeterminate: boolean = false;
  checkbox_list: LekarCheckBox[] = [];

  constructor(private dialogRef: MatDialogRef<ZakaziTerminComponent>,
    @Inject(MAT_DIALOG_DATA) data, private klinikaService: KlinikaService) {

    this.slobodniLekari = data.lekari

    this.checkbox_list = data.lekari

    this.checkbox_list.forEach(element => {
      if (element.jbo === data.jboLekara) {
        element.disabled = true
        element.checked = true
      }
      else {
        element.disabled = false
        element.checked = false
      }
      element.labelPosition = "after"
    });

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

  master_change() {
    for (let value of Object.values(this.checkbox_list)) {
      if (value.jbo != this.operator) {
        value.checked = this.master_checked;
      }
    }
  }

  list_change() {
    let checked_count = 0;
    //Get total checked items
    for (let value of Object.values(this.checkbox_list)) {
      if (value.checked)
        checked_count++;
    }

    if (checked_count > 0 && checked_count < this.checkbox_list.length) {
      // If some checkboxes are checked but not all; then set Indeterminate state of the master to true.
      this.master_indeterminate = true;
    } else if (checked_count == this.checkbox_list.length) {
      //If checked count is equal to total items; then check the master checkbox and also set Indeterminate state to false.
      this.master_indeterminate = false;
      this.master_checked = true;
    } else {
      //If none of the checkboxes in the list is checked then uncheck master also set Indeterminate to false.
      this.master_indeterminate = false;
      this.master_checked = false;
    }
  }

  izabraniLekari : Lekar []

  onSubmit(selected: any) {

    this.izabraniLekari = []

    if (this.tip == 'Operacija') {
      this.checkbox_list.forEach(element => {
        var lekar : Lekar 
        lekar.id = element.id
        this.izabraniLekari.push(lekar)
      });

      var operacija : Operacija
      operacija.
    }
    else {
      
    }

    var zahtev = new Zahtev()
    zahtev.datum = this.sala.datumSlobodna
    zahtev.pocetak = this.sala.pocetakSlobodna
    zahtev.kraj = this.sala.krajSlobodna
    if (this.tip == 'Operacija') {
    }
    else {

    }
    // zahtev.posiljalacJbo = this.operator
    // this.klinikaService.zakaziTermin(zahtev)
    // .subscribe(data => {
    //   this.sale = data;
    // });
  }

}
