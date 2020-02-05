import { Component, OnInit } from '@angular/core';
import { Pacijent } from 'src/app/models/pacijent/pacijent';
import { PacijentService } from 'src/app/services/pacijent-service/pacijent.service';
import { ZdravstveniKartonService } from 'src/app/services/zdravstveni-karton-service/zdravstveni-karton.service';
import {Lekar} from '../../../models/lekar/lekar';
import {KorisnikService} from '../../../services/korisnik-service/korisnik.service';
import {LekarService} from '../../../services/lekar-service/lekar.service';
import {SessionService} from '../../../services/SessionService/session.service';
import {ZdravstveniKarton} from '../../../models/zdravstvenik/zdravstveniKarton';
import {IzvestajService} from '../../../services/izvestaj-service/izvestaj.service';
import {IzvestajDto} from '../../../helpers/izvestaj-dto';
import {OpstiIzvestaj} from '../../../models/opsti-izvestaj/opsti-izvestaj';
import {OpstiIzvestajService} from '../../../services/opsti-izvestaj/opsti-izvestaj.service';
import {MatDialog} from '@angular/material/dialog';
import {PregledIzvestajaComponent} from '../pregled-izvestaja/pregled-izvestaja.component';
import {PregledLekovaComponent} from '../pregled-lekova/pregled-lekova.component';
import {Router} from '@angular/router';
import {MedicinskaSestraService} from '../../../services/medicinska-sestra-service/medicinska-sestra.service';
import {MedicinskaSestra} from '../../../models/medicinskas/medicinskas';

@Component({
  selector: 'app-zdravstveni-karton',
  templateUrl: './zdravstveni-kartonComponent.html',
  styleUrls: ['/zdravstveni-kartonComponent.css']
})

export class ZdravstveniKartonComponent implements OnInit {
  // private karton: ZdravstveniKarton;
  private dijagnoze: String[];
  private pacijent: Pacijent = new Pacijent();
  lekar: Lekar;
  medicinskaSestra: MedicinskaSestra;
  pacijentJe = false;
  lekarJe = false;
  sestraJe = false;
  objekat: any;
  izvestaji: IzvestajDto[] = [];
  zdravstveniKarton: ZdravstveniKarton = new ZdravstveniKarton();
  opstiIzvestaj: OpstiIzvestaj;
  alergijeNaLek = false;
  constructor( private pacijentService: PacijentService, private zkartonService: ZdravstveniKartonService,
               private korisnikService: KorisnikService, private lekarService: LekarService, public dialog: MatDialog,
               private sessionService: SessionService, private izvestajService: IzvestajService, private router: Router,
               private opstiIzvestajService: OpstiIzvestajService, private medicinskaSestraService: MedicinskaSestraService) {
    this.korisnikService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      if (ulogovanKorisnik.tipKorisnika === 'Pacijent') {
        this.pacijentJe = true;
        this.lekarJe = false;
        this.sestraJe = false;
      } else if (ulogovanKorisnik.tipKorisnika === 'Lekar') {
        this.pacijentJe = false;
        this.lekarJe = true;
        this.sestraJe = false;
      } else {
        this.pacijentJe = false;
        this.lekarJe = false;
        this.sestraJe = true;
      }

      if (this.pacijentJe === true) {
        this.pacijentService.getUlogovanKorisnik().subscribe(data => {
            this.pacijent = data;
            this.zkartonService.get(this.pacijent.id)
              .subscribe(dijag => {
                this.dijagnoze = dijag;
                console.log(this.dijagnoze);
            });
          }
        );
      } else if (this.lekarJe === true) {
          this.lekarService.getUlogovanKorisnik().subscribe(data => {
              this.lekar = data;
              console.log(this.lekar);
              this.pacijent = this.sessionService.pacijentProfil;
              console.log(this.pacijent);
              this.zkartonService.get(this.pacijent.id).subscribe( dijagnoze =>
                this.dijagnoze = dijagnoze
              );

              this.zkartonService.getPacijentovZk(this.pacijent).subscribe( zk => {
                  this.objekat = zk;
                  this.zdravstveniKarton = this.objekat;
                  console.log(this.zdravstveniKarton);

                  this.opstiIzvestajService.getIzvestaj(this.zdravstveniKarton.id).subscribe( opstIzvestaj => {
                    this.objekat = opstIzvestaj;
                    this.opstiIzvestaj = this.objekat;
                    if (this.opstiIzvestaj.alergijeNaLek === '') {
                      this.alergijeNaLek = true;
                    }
                    console.log(this.opstiIzvestaj);
                  }
                );
                  this.izvestajService.getByZk(this.zdravstveniKarton.id).subscribe( izv => {
                      this.objekat = izv;
                      this.izvestaji = this.objekat;
                      console.log(this.izvestaji);
                    }
                  );

                }
              );
            }
          );
      } else {
        this.medicinskaSestraService.getUlogovanKorisnik().subscribe(data => {
            this.medicinskaSestra = data;
            this.pacijent = this.sessionService.pacijentProfil;
            this.zkartonService.get(this.pacijent.id).subscribe( dijagnoze =>
              this.dijagnoze = dijagnoze
            );

            this.zkartonService.getPacijentovZk(this.pacijent).subscribe( zk => {
                this.objekat = zk;
                this.zdravstveniKarton = this.objekat;
                console.log(this.zdravstveniKarton);

                this.opstiIzvestajService.getIzvestaj(this.zdravstveniKarton.id).subscribe( opstIzvestaj => {
                    this.objekat = opstIzvestaj;
                    this.opstiIzvestaj = this.objekat;
                    if (this.opstiIzvestaj.alergijeNaLek === '') {
                      this.alergijeNaLek = true;
                    }
                    console.log(this.opstiIzvestaj);
                  }
                );
                this.izvestajService.getByZk(this.zdravstveniKarton.id).subscribe( izv => {
                    this.objekat = izv;
                    this.izvestaji = this.objekat;
                    console.log(this.izvestaji);
                  }
                );

              }
            );
          }
        );
      }
    });
    // pull izvestaje
    // pull opsti izvestaj
    // pull zdravstveni karton
  }

  ngOnInit() {
    if (this.lekarJe) {
      console.log(this.lekar);
    } else if (this.pacijentJe === true) {
      this.pacijentService.getUlogovanKorisnik()
        .subscribe(ulogovanKorisnik => {
          this.pacijent = ulogovanKorisnik;
          this.zkartonService.get(this.pacijent.id)
            .subscribe(dijag => {
              this.dijagnoze = dijag;
              console.log(this.dijagnoze);
            });
        });
    }
  }

  izmeniIzvestaj(izvestaj: IzvestajDto) {
    this.sessionService.izvestajZaIzmenu = izvestaj;
    this.router.navigate(['/izmenaIzvestaja']);
  }


  prikaziIzvestaj(izvestaj: string) {
    const dialogRef = this.dialog.open(PregledIzvestajaComponent, {
      data: izvestaj
    });

    dialogRef.afterClosed().subscribe(
      result => console.log('zatvara se'),
      err => console.log('Neuspesno zatvaranje prozora!'),
      () => console.log('zatvoren')
    );
  }

  prikaziLekove(idRecepta: number) {
    const dialogRef = this.dialog.open(PregledLekovaComponent, {
      data: idRecepta
    });

    dialogRef.afterClosed().subscribe(
      result => console.log('zatvara se'),
      err => console.log('Neuspesno zatvaranje prozora!'),
      () => console.log('zatvoren')
    );
  }

}
