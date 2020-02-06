import { Component, OnInit } from '@angular/core';
import {Pacijent} from '../../../models/pacijent/pacijent';
import {MatDialog} from '@angular/material/dialog';
import {Lekar} from '../../../models/lekar/lekar';
import {LekarService} from '../../../services/lekar-service/lekar.service';
import {PregledService} from '../../../services/pregled-service/pregled.service';
import {PacijentService} from '../../../services/pacijent-service/pacijent.service';
import {SessionService} from '../../../services/SessionService/session.service';
import {Router} from '@angular/router';
import {Pregled} from '../../../models/pregled/pregled';
import {ZdravstveniKarton} from '../../../models/zdravstvenik/zdravstveniKarton';
import {KorisnikService} from '../../../services/korisnik-service/korisnik.service';

@Component({
  selector: 'app-pretraga-pacijenata',
  templateUrl: './pretraga-pacijenata.component.html',
  styleUrls: ['./pretraga-pacijenata.component.css']
})
export class PretragaPacijenataComponent implements OnInit {
  pregledi: any;
  lekar: Lekar;
  pacijentiNovi: Pacijent[] = [];
  pacijenti: any;
  datumi: string[] = [];
  selectedPacijent: Pacijent;
  sestraJe = false;
  lekarJe = false;
  odrzaoPregled = false;
  trajePregled = false;
  lekarPreglediPacijent: any;
  pregledTemp: Pregled[] = [];

  constructor(private pregledService: PregledService, public dialog: MatDialog, private lekarService: LekarService,
              private pacijentiService: PacijentService, private sessionService: SessionService,
              private router: Router, private korisnikService: KorisnikService) {
    this.pacijentiService.getPacijentiAll().subscribe(pacijentData => {
      this.pacijenti = pacijentData;
      console.log(this.pacijenti);
      this.lekarPreglediPacijent = this.pregledTemp;

      this.korisnikService.getUlogovanKorisnik().subscribe(korisnik => {
          if (korisnik.tipKorisnika === 'Medicinska sestra') {
            this.sestraJe = true;
            this.lekarJe = false;
          } else if (korisnik.tipKorisnika === 'Lekar') {
            this.lekarJe = true;
            this.sestraJe = false;
          }
        }
      );
    });
  }

  onClick(pacijent) {
    this.selectedPacijent = pacijent;
    this.bioNaPregledu(this.selectedPacijent);
  }

  ngOnInit() {
  }

  bioNaPregledu(odabraniPacijent) {
    this.sessionService.pacijentProfil = odabraniPacijent;
    this.lekarService.getUlogovanKorisnik().subscribe(data => {
      this.lekar = data;

      this.pregledService.getPreglede(this.lekar.id).subscribe( pregledi => {
          this.pregledi = pregledi;

          this.pregledi.forEach( pregled => {
            if (pregled.jboLekara === this.lekar.jbo) {
              if (odabraniPacijent.jbo === pregled.jboPacijenta) {
                this.odrzaoPregled = true;
                this.lekarPreglediPacijent.push(pregled);
              }
            }
          });
        }
      );
    });

  }

  OtvoriZdravstveniKarton(zk: ZdravstveniKarton) {
    this.sessionService.pacijentProfil = this.selectedPacijent;
    console.log(this.sessionService.pacijentProfil);
    this.router.navigate(['/zdravstveniKarton']);
  }
}
