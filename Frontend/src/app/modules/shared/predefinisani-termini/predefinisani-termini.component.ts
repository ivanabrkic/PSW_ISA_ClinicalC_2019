import { Component, OnInit } from '@angular/core';
import { PredefTerminiServiceService } from 'src/app/services/predefTermini-service/predef-termini-service.service';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { Pregled } from 'src/app/models/pregled/pregled';
import { predefInfo } from 'src/app/models/predefInfoDTO/predefInfo';
import {MatGridListModule} from '@angular/material/grid-list';
import { PredefZahtev } from 'src/app/models/PredefZahtev/PredefZahtev';
import { PacijentService } from 'src/app/services/pacijent-service/pacijent.service';
import { TamarinPregled } from 'src/app/models/TamarinPregled';
import { klinikaPacDTO } from 'src/app/models/klinikaPacDTO/klinikaPacDTO';
import { InfoWindowManager } from '@agm/core';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-predefinisani-termini',
  templateUrl: './predefinisani-termini.component.html',
  styleUrls: ['./predefinisani-termini.component.css']
})
export class PredefinisaniTerminiComponent implements OnInit {

  termini:predefInfo[];
  izabraniTermin:predefInfo;
  dodatneInfo:String;
  
  constructor(private _snackBar: MatSnackBar, private predefService: PredefTerminiServiceService , private pacService:PacijentService) {
  }

  onSelect(selected:predefInfo): void {
    this.izabraniTermin=selected;
    console.log(selected);
  }


  ngOnInit() {
     this.getTermini();
  }

  getTermini(){
    var info=new klinikaPacDTO;
    info.idKlin=history.state.klinika;
    console.log(info.idKlin)
    this.pacService.getUlogovanKorisnik().subscribe(data=>{
      info.idPac=data.id
      console.log(info)
      this.predefService.findAll(info).subscribe(
        podaci => {this.termini = podaci;
          console.log(podaci)
        },
        err => console.log('Nisu ucitani'),
        () => console.log(this.termini)
      );})
   
  }

  zakaziNavigate(event){

    //zahtev.dodatneInformacije=
    this.pacService.getUlogovanKorisnik().subscribe(
      podaci => {
        var zahtev=new TamarinPregled;
        zahtev.id=this.izabraniTermin.id;
        zahtev.pacijent_id=podaci.id;
        this.predefService.zakaziPredefTermin(zahtev).subscribe(data=>{
          console.log(data);
          alert('Termin uspesno zakazan');})

      },
    );
  }

}
