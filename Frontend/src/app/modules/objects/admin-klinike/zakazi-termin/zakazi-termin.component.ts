import { Component, OnInit, Inject } from '@angular/core';
import { Lekar } from 'src/app/models/lekar/lekar';
import { MatDialogRef, MAT_DIALOG_DATA, MatListOption, MatTableDataSource } from '@angular/material';
import { Sala } from 'src/app/models/sala/sala';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { LekarCheckBox } from 'src/app/models/lekar/lekarCheckBox';
import { Operacija } from 'src/app/models/operacija/operacija';
import { Pregled } from 'src/app/models/pregled/pregled';

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
  pacijent: String
  tipId: number
  tipPregleda: String
  idZahteva: number

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
    this.pacijent = data.jboPacijenta
    this.tipPregleda = data.tipPregleda
    this.tipId = data.tipId
    this.idZahteva = data.idZahteva
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

  izabraniLekari: String[]

  obradjen: boolean = true

  onSubmit(selected: any) {

    this.izabraniLekari = []

    if (this.tip == 'Operacija') {
      this.checkbox_list.forEach(element => {
        var lekarJbo: string
        lekarJbo = element.jbo
        this.izabraniLekari.push(lekarJbo)
      });

      var operacija: Operacija = new Operacija()
      operacija.datum = this.sala.datumSlobodna
      operacija.pocetak = this.sala.pocetakSlobodna
      operacija.kraj = this.sala.krajSlobodna
      operacija.jboLekara = this.izabraniLekari
      operacija.tipPregleda = this.tipPregleda
      operacija.tipId = this.tipId
      operacija.jboPacijenta = this.pacijent
      operacija.salaId = this.sala.id
    }
    else {
      var pregled: Pregled = new Pregled()
      pregled.datum = this.sala.datumSlobodna
      pregled.pocetak = this.sala.pocetakSlobodna
      pregled.kraj = this.sala.krajSlobodna
      pregled.jboLekara = this.selectedLekar.jbo
      pregled.tipPregleda = this.tipPregleda
      pregled.tipId = this.tipId
      pregled.jboPacijenta = this.pacijent
      pregled.salaId = this.sala.id
    }

    if (this.tip == 'Operacija') {
      this.klinikaService.zakaziOperaciju(operacija)
        .subscribe(data => {
          alert(data.text)
          this.klinikaService.removeZahtev(this.idZahteva)
            .subscribe(data => {

            });
        });
    }
    else {
      this.klinikaService.zakaziPregled(pregled)
        .subscribe(data => {
          alert(data.text)
          this.klinikaService.removeZahtev(this.idZahteva)
            .subscribe(data => {

            });
        });
    }
    this.obradjen = false


    this.dialogRef.close(this.obradjen);
  }

}
