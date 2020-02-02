import {Component, Inject, OnInit} from '@angular/core';
import {Dijagnoza} from '../../../models/Dijagnoza/dijagnoza';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {DijagnozaService} from '../services/DijagnozaService/dijagnoza.service';
import {ZdravstveniKarton} from '../../../models/zdravstvenik/zdravstveniKarton';

@Component({
  selector: 'app-dijalog-unos-dijagnoza',
  templateUrl: './dijalog-unos-dijagnoza.component.html',
  styleUrls: ['./dijalog-unos-dijagnoza.component.css']
})
export class DijalogUnosDijagnozaComponent implements OnInit {
  dijagnoze: any;
  noviPodaci: any;
  zdravstveniKarton: ZdravstveniKarton;
  postoji = false;
  constructor(public dialogRef: MatDialogRef<DijalogUnosDijagnozaComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, private dijagnozaService: DijagnozaService) {
    this.zdravstveniKarton = this.data;
    console.log(this.zdravstveniKarton);
    // tslint:disable-next-line:no-shadowed-variable
    this.dijagnozaService.get().subscribe(data => {
        this.dijagnoze = data;
        const podaci = [{
          dijagnoze: '',
          cekiran: true,
        }];
        this.dijagnoze.forEach( (dijagnoza) => {
            podaci.push({dijagnoze: dijagnoza, cekiran: false});
          }
        );
        podaci.splice(0, 1);
        this.noviPodaci = podaci;
        // ovde je dobro, dobijam niz, ne niz u nizu
      }
    );
  }

  ngOnInit() {
  }

  change(lek, event){
    let index = this.noviPodaci.indexOf(lek);
    this.noviPodaci[index].cekiran = !this.noviPodaci[index].cekiran;
    console.log(this.noviPodaci);
  }

  unesiDijagnoze() {
    this.noviPodaci.forEach( element => {
      console.log(this.zdravstveniKarton.dijagnoze.indexOf(element.dijagnoze));
      this.zdravstveniKarton.dijagnoze.forEach(dijagnoza => {
        console.log(element.dijagnoze.nazivDijagnoze);
        if (element.dijagnoze.nazivDijagnoze === dijagnoza.nazivDijagnoze) {
          this.postoji = true;
          }
        }
      );
      if (this.postoji === false) {
        if (element.cekiran === true) {
          this.zdravstveniKarton.dijagnoze.push(element.dijagnoze);
        }
      } else {
        this.postoji = false;
      }
    });
    console.log(this.noviPodaci)
    this.dialogRef.close(this.zdravstveniKarton);
  }

}
