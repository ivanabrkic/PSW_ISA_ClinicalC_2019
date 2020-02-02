import {Component, Inject, OnInit} from '@angular/core';
import {LekoviService} from '../services/LekoviService/lekovi.service';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Lekovi} from '../../../models/Lekovi/lekovi';
import {Recept} from '../../../models/Recept/recept';

@Component({
  selector: 'app-dijalog-kreiranje-recepta',
  templateUrl: './dijalog-kreiranje-recepta.component.html',
  styleUrls: ['./dijalog-kreiranje-recepta.component.css']
})
export class DijalogKreiranjeReceptaComponent implements OnInit {
  lekovi: any;
  noviPodaci: any;
  recept: Recept = new Recept();
  receptLekovi: Lekovi[] = [];
  temp: any[] = [];
  constructor(public dialogRef: MatDialogRef<DijalogKreiranjeReceptaComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, private lekoviService: LekoviService) {
    // tslint:disable-next-line:no-shadowed-variable
    this.lekoviService.get().subscribe(data => {
        this.lekovi = data;
        let podaci = [{
          lek: '',
          cekiran: true,
        }];
        this.lekovi.forEach( (lekovi) => {
            podaci.push({lek: lekovi, cekiran: false});
          }
        );
        podaci.splice(0, 1);
        this.noviPodaci = podaci;
        //ovde je dobro, dobijam niz, ne niz u nizu
      }
    );
  }

  change(lek, event){
    let index = this.noviPodaci.indexOf(lek);
    this.noviPodaci[index].cekiran = !this.noviPodaci[index].cekiran;
    console.log(this.noviPodaci);
  }

  kreirajRecept(){
    this.recept.overen = false;
    this.recept.pacijent = this.data.pacijent;
    this.recept.lekovi = this.receptLekovi;
    this.noviPodaci.forEach( element =>{
      if (element.cekiran === true) {
        this.recept.lekovi.push(element.lek);
      }
    });
    console.log(this.recept.lekovi);
    this.dialogRef.close(this.recept);
  }

  ngOnInit() {
  }



}
