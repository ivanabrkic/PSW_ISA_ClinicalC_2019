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
import {IzvestajDto} from '../../../models/izvestajDTO/izvestaj-dto';
import {OpstiIzvestaj} from '../../../models/opsti-izvestaj/opsti-izvestaj';
import {OpstiIzvestajService} from '../../../services/opsti-izvestaj/opsti-izvestaj.service';
import {MatDialog} from '@angular/material/dialog';
import {PregledIzvestajaComponent} from '../pregled-izvestaja/pregled-izvestaja.component';
import {PregledLekovaComponent} from '../pregled-lekova/pregled-lekova.component';
import {Router} from '@angular/router';
import {MedicinskaSestraService} from '../../../services/medicinska-sestra-service/medicinska-sestra.service';
import {MedicinskaSestra} from '../../../models/medicinskas/medicinskas';
import {PregledIzvestajDTO} from '../../../models/pregledIzvestajDTO/pregled-izvestaj-dto';
import {PregledService} from '../../../services/pregled-service/pregled.service';
import {DatePipe} from '@angular/common';
import {element} from 'protractor';
import {ReceptServiceService} from '../../../services/recept-service/recept-service.service';
import {Recept} from '../../../models/Recept/recept';


@Component({
  selector: 'app-zdravstveni-karton',
  templateUrl: './zdravstveni-kartonComponent.html',
  styleUrls: ['/zdravstveni-kartonComponent.css']
})

export class ZdravstveniKartonComponent implements OnInit {

  private dijagnoze: String[];
  private pacijent: Pacijent = new Pacijent();
  lekar: Lekar;
  medicinskaSestra: MedicinskaSestra;
  pacijentJe = false;
  objekatRecept: any;
  lekarJe = false;
  poceo = false;
  sestraJe = false;
  objekat: any;
  noviPodaci: any;
  uslovPregled = false;
  getovanRecept: Recept = new Recept();
  zakazaniPregledi: PregledIzvestajDTO[] = [];
  izvestaji: IzvestajDto[] = [];
  zdravstveniKarton: ZdravstveniKarton = new ZdravstveniKarton();
  opstiIzvestaj: OpstiIzvestaj;
  alergijeNaLek = false;
  tempPregledi: PregledIzvestajDTO[] = [];

  constructor( private pacijentService: PacijentService, private zkartonService: ZdravstveniKartonService,
               private korisnikService: KorisnikService, private lekarService: LekarService, public dialog: MatDialog,
               private sessionService: SessionService, private izvestajService: IzvestajService, private router: Router,
               private opstiIzvestajService: OpstiIzvestajService, private medicinskaSestraService: MedicinskaSestraService,
               private pregledService: PregledService, private datePipe: DatePipe, private receptService: ReceptServiceService) {
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
      } else if (ulogovanKorisnik.tipKorisnika === 'Medicinska sestra') {
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
                  this.objekat.forEach( izvestaj => {
                    if(izvestaj.idPacijenta === this.pacijent.id){
                      this.izvestaji.push(izvestaj);
                    }
                  });

                  this.izvestaji = this.objekat;
                  console.log(this.izvestaji);
                }
              );
            }
          );
          }
        );
      } else if (this.lekarJe === true) {
          this.lekarService.getUlogovanKorisnik().subscribe(data => {
              this.lekar = data;
              console.log(this.lekar);
              this.pacijent = this.sessionService.pacijentProfil;

              this.ucitajPreglede();
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
      } else if (this.sestraJe === true) {
        this.medicinskaSestraService.getUlogovanKorisnik().subscribe(data => {
            this.medicinskaSestra = data;
            console.log(this.lekar);
            this.pacijent = this.sessionService.pacijentProfil;
            this.ucitajPreglede();
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
  }

  ucitajPreglede() {
    if (this.lekarJe) {
      this.pregledService.getZakazane(this.lekar.jbo, this.pacijent.jbo).subscribe(pregledi => {
        this.objekat = pregledi;
        this.tempPregledi = this.objekat;

        console.log('Pregledi: ' + this.tempPregledi);
        this.objekat.forEach(pregled => {
          if (pregled.jboPacijenta === this.pacijent.jbo) {
            console.log(pregled.jboPacijenta);
            this.zakazaniPregledi.push(pregled);
          }
        });
        console.log(this.zakazaniPregledi);
        const podaci = [];
        const datum = this.datePipe.transform(new Date(), 'd.M.yyyy.').split('.', 3);
        const vreme = this.datePipe.transform(new Date(), 'H:mm').split(':');

        this.zakazaniPregledi.forEach(nesto => {
          if (this.proveriDatum(this.lekar, datum, vreme, nesto) === true) {
            podaci.push({pregled: nesto, ispunjenUslov: true});
          } else {
            podaci.push({pregled: nesto, ispunjenUslov: false});
          }
        });
        this.noviPodaci = podaci;
        console.log(this.noviPodaci);
        // });
      });
    } else if (this.sestraJe) {
      this.pregledService.getZakazaneSestra(this.pacijent.jbo).subscribe(pregledi => {
        console.log(this.pacijent.jbo)
        this.objekat = pregledi;
        this.tempPregledi = this.objekat;
        console.log('Pregledi: ' + this.tempPregledi);
        this.objekat.forEach(pregled => {
          if (pregled.jboPacijenta === this.pacijent.jbo) {
            console.log(pregled.jboPacijenta);
            this.zakazaniPregledi.push(pregled);
          }
        });
        console.log(this.zakazaniPregledi);
        const podaci = [];
        const datum = this.datePipe.transform(new Date(), 'd.M.yyyy.').split('.', 3);
        const vreme = this.datePipe.transform(new Date(), 'H:mm').split(':');

        this.zakazaniPregledi.forEach(nesto => {
          if (this.proveriDatum(this.lekar, datum, vreme, nesto) === true) {
            podaci.push({pregled: nesto, ispunjenUslov: true});
          } else {
            podaci.push({pregled: nesto, ispunjenUslov: false});
          }
        });
        this.noviPodaci = podaci;
        console.log(this.noviPodaci);
        // });
      });
    }
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
    this.izvestajService.getReceptByIzvestajId(izvestaj.id).subscribe( receptIzBaze => {
      this.objekatRecept = receptIzBaze;
      this.sessionService.receptZaIzmenu = this.objekatRecept;
      this.router.navigate(['/izmenaIzvestaja']);
    });
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

  prikaziLekove(izvestajDTO: IzvestajDto, izvestajId: number) {
    console.log(izvestajDTO);
    this.izvestajService.getReceptByIzvestajId(izvestajDTO.id).subscribe( receptIzBaze => {
      this.objekatRecept = receptIzBaze;
      console.log(this.objekatRecept);
      this.getovanRecept = this.objekatRecept;
      this.sessionService.receptZaIzmenu = this.getovanRecept;
      izvestajDTO.idRecept = this.getovanRecept.id;
      const dialogRef = this.dialog.open(PregledLekovaComponent, {
        data: izvestajDTO.idRecept
      });

      dialogRef.afterClosed().subscribe(
        result => console.log('zatvara se'),
        err => console.log('Neuspesno zatvaranje prozora!'),
        () => console.log('zatvoren')
      );
    });
  }

  zapocniPregled(pacijent: Pacijent, pregled: PregledIzvestajDTO){
    this.sessionService.lekarZaPregled = this.lekar;
    this.sessionService.pacijentZaPregled = pacijent;
    this.sessionService.datumZaPregled = pregled.datum;
    this.sessionService.zkPregled = this.zdravstveniKarton;
    this.sessionService.opstiIzvestaj = this.opstiIzvestaj;
    this.sessionService.pregled = pregled;
    this.router.navigate(['/formaIzvestaj']);
  }

  proveriDatum(ulogovaniLekar, datum, vreme, podatak): boolean {
    this.poceo = false;
    console.log('usao je ovde')
    const sati = +vreme[0];
    const minuti = +vreme[1];

    if(this.lekarJe) {
      if (podatak.jboLekara === ulogovaniLekar.jbo) {
        console.log('taj je lekar');
        const datumPregled = podatak.datum.split('.', 3);
        const vremePocetakPregled = podatak.pocetak.split(':',);
        const vremeKrajPregled = podatak.kraj.split(':');
        console.log(datum);
        console.log(vreme)
        if ((datum[0] === datumPregled[0]) && (datum[1] === datumPregled[1]) && (datum[2] === datumPregled[2])) {
          console.log('taj je datum')
          const satiPocetakPregled = +vremePocetakPregled[0];
          const minutiPocetakPregled = +vremePocetakPregled[1];
          console.log(satiPocetakPregled);
          console.log(minutiPocetakPregled);
          if (sati > satiPocetakPregled) {
            console.log('sati > satiPocetakPregled')
            const satiKrajPregled = +vremeKrajPregled[0];
            const minutiKrajPregled = +vremeKrajPregled[1];
            if (sati < satiKrajPregled) {
              console.log('sati > satiKrajPregled')
              this.poceo = true;
              console.log('sati kraja su ok');
            } else if (sati === satiKrajPregled) {
              console.log('Minuti trenutno: ' + minuti);
              console.log('Minuti kada se zavrsava pregled: ' + minutiKrajPregled);
              if (minuti < minutiKrajPregled) {
                this.poceo = true;
              } else {
                this.poceo = false;
              }
            } else {
              this.poceo = false;
            }
          } else if (sati === satiPocetakPregled) {
            if (minuti >= minutiPocetakPregled) {
              const satiKrajPregled = +vremeKrajPregled[0];
              const minutiKrajPregled = +vremeKrajPregled[1];
              if (sati < satiKrajPregled) {
                this.poceo = true;
              } else if (sati === satiKrajPregled) {
                if (minuti < minutiKrajPregled) {
                  this.poceo = true;
                } else {
                  this.poceo = false;
                }
              } else {
                this.poceo = false;
              }
            } else {
              this.poceo = false;
            }
          } else {
            this.poceo = false;
          }
        } else {
          this.poceo = false;
        }
      } else {
        this.poceo = false;
      }
    } else if(this.sestraJe){
      const datumPregled = podatak.datum.split('.', 3);
      const vremePocetakPregled = podatak.pocetak.split(':',);
      const vremeKrajPregled = podatak.kraj.split(':');
      console.log(datum);
      console.log(vreme)
      if ((datum[0] === datumPregled[0]) && (datum[1] === datumPregled[1]) && (datum[2] === datumPregled[2])) {
        console.log('taj je datum')
        const satiPocetakPregled = +vremePocetakPregled[0];
        const minutiPocetakPregled = +vremePocetakPregled[1];
        console.log(satiPocetakPregled);
        console.log(minutiPocetakPregled);
        if (sati > satiPocetakPregled) {
          console.log('sati > satiPocetakPregled')
          const satiKrajPregled = +vremeKrajPregled[0];
          const minutiKrajPregled = +vremeKrajPregled[1];
          if (sati < satiKrajPregled) {
            console.log('sati > satiKrajPregled')
            this.poceo = true;
            console.log('sati kraja su ok');
          } else if (sati === satiKrajPregled) {
            console.log('Minuti trenutno: ' + minuti);
            console.log('Minuti kada se zavrsava pregled: ' + minutiKrajPregled);
            if (minuti < minutiKrajPregled) {
              this.poceo = true;
            } else {
              this.poceo = false;
            }
          } else {
            this.poceo = false;
          }
        } else if (sati === satiPocetakPregled) {
          if (minuti >= minutiPocetakPregled) {
            const satiKrajPregled = +vremeKrajPregled[0];
            const minutiKrajPregled = +vremeKrajPregled[1];
            if (sati < satiKrajPregled) {
              this.poceo = true;
            } else if (sati === satiKrajPregled) {
              if (minuti < minutiKrajPregled) {
                this.poceo = true;
              } else {
                this.poceo = false;
              }
            } else {
              this.poceo = false;
            }
          } else {
            this.poceo = false;
          }
        } else {
          this.poceo = false;
        }
      } else {
        this.poceo = false;
      }
    }
    return this.poceo;
  }
}
