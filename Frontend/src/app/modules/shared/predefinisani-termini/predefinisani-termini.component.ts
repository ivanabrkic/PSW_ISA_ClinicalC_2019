import { Component, OnInit } from '@angular/core';
import { PredefTerminiServiceService } from 'src/app/services/predefTermini-service/predef-termini-service.service';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { Pregled } from 'src/app/models/pregled/pregled';
import { predefInfo } from 'src/app/models/predefInfoDTO/predefInfo';
import {MatGridListModule} from '@angular/material/grid-list';

@Component({
  selector: 'app-predefinisani-termini',
  templateUrl: './predefinisani-termini.component.html',
  styleUrls: ['./predefinisani-termini.component.css']
})
export class PredefinisaniTerminiComponent implements OnInit {

  predefTermService:PredefTerminiServiceService;
  termini:predefInfo[];

  constructor(p:PredefTerminiServiceService) { 
    this.predefTermService=p;
  }

  ngOnInit() {
    this.getTermini();
  }

  getTermini(){
    this.predefTermService.findAll(history.state.klinika).subscribe(
      podaci => {this.termini = podaci; },
      err => console.log('Nisu ucitani'),
      () => console.log('Uspesno ucitani')
    );
  }

}
