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

@Component({
  selector: 'app-predefinisani-termini',
  templateUrl: './predefinisani-termini.component.html',
  styleUrls: ['./predefinisani-termini.component.css']
})
export class PredefinisaniTerminiComponent implements OnInit {

// <<<<<<< 2_2_Dodavanje_predefinisanih_termina          VIDI OVO
//   predefTermService:PredefTerminiServiceService;
//   termini:predefInfo[];

//   constructor(p:PredefTerminiServiceService) { 
//     this.predefTermService=p;
// =======
  termini:predefInfo[];
  izabraniTermin:predefInfo;
  dodatneInfo:String;

  constructor(private predefService: PredefTerminiServiceService , private pacService:PacijentService) { 
  }

  onSelect(selected:predefInfo): void {
    this.izabraniTermin=selected;
    console.log(selected);
  }

  ngOnInit() {
    this.getTermini();
  }

  getTermini(){
// <<<<<<< 2_2_Dodavanje_predefinisanih_termina
//     this.predefTermService.findAll(history.state.klinika).subscribe(
//       podaci => {this.termini = podaci; },
//       err => console.log('Nisu ucitani'),
//       () => console.log('Uspesno ucitani')
// =======   VIDI OVO
    var info=new klinikaPacDTO;
    info.idKlin=history.state.klinika;
    this.pacService.getUlogovanKorisnik().subscribe(data=>{
      info.idPac=data.id
      this.predefService.findAll(info).subscribe(
        podaci => {this.termini = podaci; },
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
