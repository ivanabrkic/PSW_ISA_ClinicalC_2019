import { Component, OnInit } from '@angular/core';
import {SessionService} from '../../../services/SessionService/session.service';
import {Pacijent} from '../../../models/pacijent/pacijent';
import {IzvestajDto} from '../../../models/izvestajDTO/izvestaj-dto';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {ReceptServiceService} from '../../../services/recept-service/recept-service.service';
import {Recept} from '../../../models/Recept/recept';
import {DijalogKreiranjeReceptaComponent} from '../dijalog-kreiranje-recepta/dijalog-kreiranje-recepta.component';
import {PacijentService} from '../../../services/pacijent-service/pacijent.service';
import {PregledService} from '../../../services/pregled-service/pregled.service';
import {Router} from '@angular/router';
import {IzvestajService} from '../../../services/izvestaj-service/izvestaj.service';
import {DijalogUnosDijagnozaComponent} from '../dijalog-unos-dijagnoza/dijalog-unos-dijagnoza.component';
import {ZdravstveniKarton} from '../../../models/zdravstvenik/zdravstveniKarton';
import {ZdravstveniKartonService} from '../../../services/zdravstveni-karton-service/zdravstveni-karton.service';
import {KorisnikService} from '../../../services/korisnik-service/korisnik.service';

@Component({
  selector: 'app-izmena-izvestaja',
  templateUrl: './izmena-izvestaja.component.html',
  styleUrls: ['./izmena-izvestaja.component.css']
})
export class IzmenaIzvestajaComponent implements OnInit {
  pacijentProfil: Pacijent;
  loading = false;
  izvestajForm: FormGroup;
  submitted = false;
  objekat: any;
  recept: Recept;
  zdravstveniKarton: ZdravstveniKarton;
  izvestajStari: IzvestajDto;
  kreiranRecept = false;
  uneteDijagnoze = false;
  lekarJe = false;
  sestraJe = false;
  constructor(private sessionService: SessionService, private formBuilder: FormBuilder, public dialog: MatDialog,
              private receptService: ReceptServiceService, private pacijentService: PacijentService,
              private pregledService: PregledService, private router: Router, private izvestajService: IzvestajService,
              private zdravstveniKartonService: ZdravstveniKartonService, private korisnikService: KorisnikService) {
    this.korisnikService.getUlogovanKorisnik().subscribe(korisnik => {
      if (korisnik.tipKorisnika === 'Lekar') {
        this.lekarJe = true;
        this.sestraJe = false;
        } else {
        this.lekarJe = false;
        this.sestraJe = true;
        }
      }
    );

    this.recept = this.sessionService.receptZaIzmenu;

    this.pacijentProfil = this.sessionService.pacijentProfil;
    this.izvestajStari = this.sessionService.izvestajZaIzmenu;


    this.zdravstveniKartonService.getPacijentovZk(this.pacijentProfil).subscribe(data => {
      this.objekat = data;
      this.zdravstveniKarton = this.objekat;
      console.log(this.zdravstveniKarton);
      }
    );
  }

  get f() { return this.izvestajForm.controls; }

  ngOnInit() {
    this.izvestajForm = this.formBuilder.group({
       izvestaj: ['', [Validators.required, Validators.minLength(5)]],
    },  {
    });
  }


  unesiDijagnoze() {
    const dialogRef = this.dialog.open(DijalogUnosDijagnozaComponent, {
      data: this.zdravstveniKarton
    });

    dialogRef.afterClosed().subscribe(
      result => this.zdravstveniKarton = result,
      err => console.log('Neuspesno otvaranje prozora!'),
      () => console.log(this.zdravstveniKarton)
    );
  }

  kreirajRecept() {
    this.openDialogRecept(this.recept);
  }

  openDialogRecept(recept: Recept): void {
    const dialogRef = this.dialog.open(DijalogKreiranjeReceptaComponent, {
      data: recept
    });

    dialogRef.afterClosed().subscribe(
      result => {
        if (result !== null) {
          this.recept = result;
          this.kreiranRecept = true;
        }
      },
      err => console.log('Neuspesno otvaranje prozora!'),
      () => console.log(this.recept)
    );
  }

  onSubmit() {
    this.submitted = true;
    if (this.izvestajForm.invalid) {
      return;
    }

    this.loading = true;

    this.izvestajStari.idRecept = this.recept.id;
    console.log(this.izvestajStari);
    this.izvestajService.updateIzvestaj(this.izvestajStari).subscribe(newData =>
      console.log(this.izvestajStari)
    );
    console.log(this.zdravstveniKarton);
    this.zdravstveniKartonService.updateDijagnoze(this.zdravstveniKarton).subscribe(data =>
      console.log(this.zdravstveniKarton)
    );

    this.receptService.update(this.recept).subscribe(data =>
      this.router.navigate(['/zdravstveniKarton'])
    );

  }
}
