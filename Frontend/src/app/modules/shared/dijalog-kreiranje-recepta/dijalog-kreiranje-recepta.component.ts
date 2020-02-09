import {Component, Inject, OnInit} from '@angular/core';
import { LekoviService} from '../../../services/LekoviService/lekovi.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Lekovi} from '../../../models/Lekovi/lekovi';
import {Recept} from '../../../models/Recept/recept';

@Component({
  selector: 'app-dijalog-kreiranje-recepta',
  templateUrl: './dijalog-kreiranje-recepta.component.html',
  styleUrls: ['./dijalog-kreiranje-recepta.component.css']
})
export class DijalogKreiranjeReceptaComponent implements OnInit {
  lekovi: any;
  izmenaRecepta = false;
  noviPodaci: any;
  keepGoing = true;
  recept: Recept = new Recept();
  receptLekovi: Lekovi[] = [];
  sadrziLek = false;
  temp: any[] = [];
  constructor(public dialogRef: MatDialogRef<DijalogKreiranjeReceptaComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, private lekoviService: LekoviService) {
    this.recept = data;
    if (this.recept.lekovi === undefined) {

      this.recept = new Recept();
      this.recept.lekovi = [];
      // tslint:disable-next-line:no-shadowed-variable
      this.lekoviService.get().subscribe(data => {
          this.lekovi = data;
          let podaci = [{
            lek: '',
            cekiran: true,
          }];
          this.lekovi.forEach((lekovi) => {
              podaci.push({lek: lekovi, cekiran: false});
            }
          );
          podaci.splice(0, 1);
          this.noviPodaci = podaci;
        }
      );
    } else {
      this.izmenaRecepta = true;
      this.lekoviService.get().subscribe( lekovi => {
        this.recept = data;
        this.lekovi = lekovi;
        let podaci = [{
          lek: '',
          cekiran: true
        }];
        console.log(this.lekovi);
        console.log(this.recept.lekovi);
        this.lekovi.forEach( (lek) => {
          this.recept.lekovi.forEach( (lekoviRecept) => {
            if (this.keepGoing) {
              console.log(this.keepGoing)
              if (lek.naziv === lekoviRecept.naziv) {
                this.sadrziLek = true;
                this.keepGoing = false;
              }
            }
          });
          if (podaci.indexOf(lek) === -1) {
            if (this.sadrziLek === true) {
              podaci.push({lek, cekiran: true});
              this.sadrziLek = false;
              this.keepGoing = true;
            } else {
              this.keepGoing = true;
              podaci.push({lek, cekiran: false});
            }
          }
        });
        podaci.splice(0, 1);
        this.noviPodaci = podaci;
        console.log(this.noviPodaci);
        console.log('Izabran je recept koji postoji');
      });
    }
  }

  change(lek, event){
    let index = this.noviPodaci.indexOf(lek);
    this.noviPodaci[index].cekiran = !this.noviPodaci[index].cekiran;
    console.log(this.noviPodaci);
  }

  kreirajRecept(){
    if (this.izmenaRecepta === false) {
      this.recept.overen = false;
      this.recept.pacijent = this.data.pacijent;
      this.noviPodaci.forEach(element => {
        if (element.cekiran === true) {
          this.recept.lekovi.push(element.lek);
        }
      });
      console.log(this.recept.lekovi);
      this.dialogRef.close(this.recept);
    } else {
      this.recept.overen = false;
      this.recept.lekovi = this.receptLekovi;
      this.noviPodaci.forEach(element => {
        if (element.cekiran === true){
          this.recept.lekovi.push(element.lek);
        }
      });
      console.log(this.recept.lekovi);
      this.dialogRef.close(this.recept);
    }
  }

  ngOnInit() {
  }



}
