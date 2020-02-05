import {Component, Inject, OnInit} from '@angular/core';
import {Dijagnoza} from '../../../models/Dijagnoza/dijagnoza';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { DijagnozaService } from '../../../services/DijagnozaService/dijagnoza.service';
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
  postojiDijagnoza = false;
  noveDijagnoze: Dijagnoza[] = [];
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
          this.zdravstveniKarton.dijagnoze.forEach( (dijagnozaKarton) =>{
            if(dijagnoza.nazivDijagnoze === dijagnozaKarton.nazivDijagnoze){
              this.postojiDijagnoza = true;
            }
          });
            if (this.postojiDijagnoza === true){
              podaci.push({dijagnoze: dijagnoza, cekiran: true});
              this.postojiDijagnoza = false;
            } else {
            podaci.push({dijagnoze: dijagnoza, cekiran: false});
              this.postojiDijagnoza = false;
            }
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
    this.zdravstveniKarton.dijagnoze = this.noveDijagnoze;
    this.noviPodaci.forEach(element => {
      if (element.cekiran === true) {
          this.zdravstveniKarton.dijagnoze.push(element.dijagnoze);
          }
        });
    console.log(this.zdravstveniKarton.dijagnoze);
    this.dialogRef.close(this.zdravstveniKarton);
  }

}
