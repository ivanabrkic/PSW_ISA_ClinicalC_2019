import { Component, OnInit } from '@angular/core';
import {Pacijent} from '../../../models/pacijent/pacijent';
import {MatDialog} from '@angular/material/dialog';
import {Lekar} from '../../../models/lekar/lekar';
import {LekarService} from '../services/lekar-service/lekar.service';
import {PregledService} from '../services/pregled-service/pregled.service';
import {PacijentService} from '../services/pacijent-service/pacijent.service';
import {Pregled} from '../../../models/pregled/pregled';
import {SessionService} from '../services/SessionService/session.service';
import {Router} from '@angular/router';
import {PregledIzvestajDTO} from "../../../helpers/pregled-izvestaj-dto";

@Component({
  selector: 'app-pretraga-pacijenata',
  templateUrl: './pretraga-pacijenata.component.html',
  styleUrls: ['./pretraga-pacijenata.component.css']
})
export class PretragaPacijenataComponent implements OnInit {
  pregledi: any;
  lekar: Lekar;
  preglediNovi: PregledIzvestajDTO[] = [];
  pacijentiNovi: Pacijent[] = [];
  pacijenti: any;
  datumi: string[] = [];
  datumZaPregled: string;

  constructor(private pregledService: PregledService, public dialog: MatDialog, private lekarService: LekarService,
              private pacijentiService: PacijentService, private sessionService: SessionService,  private router: Router) {
    this.lekarService.getUlogovanKorisnik().subscribe(data => {
        this.lekar = data;
        this.pregledService.getPreglede().subscribe(pregledData =>{
          this.preglediNovi = [];
          this.pregledi = pregledData;
          this.pregledi.forEach( (pregled) => {
            if (pregled.jboLekara === this.lekar.jbo) {
              this.preglediNovi.push(pregled);
            }
          });
          console.log(this.preglediNovi);

          this.pacijentiService.getPacijentiAll().subscribe(pacijentData => {
              this.pacijenti = pacijentData;
              this.datumi = [];
              this.pacijentiNovi = [];
              console.log(this.pacijenti);
              this.pacijenti.forEach( (pacijent) => {
                this.preglediNovi.forEach( (pregled) => {
                  if (pacijent.jbo === pregled.jboPacijenta){
                    console.log(pregled.jboPacijenta)
                    this.pacijentiNovi.push(pacijent);
                    this.datumi.push(pregled.datum);
                  }
                });
              });
              console.log(this.pacijentiNovi);
            }
          );
        }
      );
      }
    );
  }

  ngOnInit() {
  }

  ZapocniPregled(pacijent: Pacijent, index: number) {
    this.sessionService.lekarZaPregled = this.lekar;
    this.sessionService.pacijentZaPregled = pacijent;
    this.sessionService.datumZaPregled = this.datumi[index];
    this.sessionService.pregled = this.pregledi[index];
    this.router.navigate(['/formaIzvestaj']);
  }
}
