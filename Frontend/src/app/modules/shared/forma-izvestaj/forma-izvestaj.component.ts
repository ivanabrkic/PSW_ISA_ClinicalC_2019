import { Component, OnInit } from '@angular/core';
import {SessionService} from '../services/SessionService/session.service';
import {Pacijent} from '../../../models/pacijent/pacijent';
import {Izvestaj} from '../../../models/izvestaj/izvestaj';
import {ZdravstveniKarton} from '../../../models/zdravstvenik/zdravstveniKarton';
import {OpstiIzvestaj} from '../../../models/opsti-izvestaj/opsti-izvestaj';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Recept} from '../../../models/Recept/recept';
import {Lekovi} from '../../../models/Lekovi/lekovi';
import {DijalogKreiranjeReceptaComponent} from '../dijalog-kreiranje-recepta/dijalog-kreiranje-recepta.component';
import {MatDialog} from '@angular/material/dialog';
import {first} from 'rxjs/operators';
import {PregledService} from '../services/pregled-service/pregled.service';
import {DijalogUnosDijagnozaComponent} from '../dijalog-unos-dijagnoza/dijalog-unos-dijagnoza.component';
import {OpstiIzvestajService} from '../services/opsti-izvestaj/opsti-izvestaj.service';
import {ZdravstveniKartonService} from '../services/zdravstveni-karton-service/zdravstveni-karton.service';
import {PacijentService} from '../services/pacijent-service/pacijent.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-forma-izvestaj',
  templateUrl: './forma-izvestaj.component.html',
  styleUrls: ['./forma-izvestaj.component.css']
})
export class FormaIzvestajComponent implements OnInit {
  pacijentZaPregled: Pacijent;
  noviIzvestaj: Izvestaj = new Izvestaj();
  opstiIzvestaj: OpstiIzvestaj;
  kreiranRecept = false;
  uneteDijagnoze = false;

  loading = false;
  izvestajForm: FormGroup;
  submitted = false;
  lekovi: Lekovi[] = [];
  recept: Recept = new Recept();

  constructor(private sessionService: SessionService, private formBuilder: FormBuilder, public dialog: MatDialog,
              private pregledService: PregledService, private opstiIzvestajService: OpstiIzvestajService,
              private zdravstveniKartonService: ZdravstveniKartonService, private pacijentService: PacijentService,
              private router: Router) {
    this.pacijentZaPregled = this.sessionService.pacijentZaPregled;
    console.log(this.pacijentZaPregled);
    console.log(this.pacijentZaPregled.zdravstveniKarton);
    this.opstiIzvestaj = this.pacijentZaPregled.zdravstveniKarton.opstiIzvestaj;
    this.noviIzvestaj = new Izvestaj();
    console.log(this.sessionService.pregled);
  }

  get f() { return this.izvestajForm.controls; }

  ngOnInit() {
    this.izvestajForm = this.formBuilder.group({
      dioptrija: ['', [Validators.required, Validators.minLength(1)]],
      visina: ['', [Validators.required, Validators.minLength(1)]],
      tezina: ['', [Validators.required, Validators.minLength(1)]],
      krvnaGrupa: ['', [Validators.required, Validators.minLength(1)]],
      alergijeNaLek: [''],
      izvestaj: ['', [Validators.required, Validators.minLength(5)]],
    },  {
    });
  }

  kreirajRecept() {
    this.openDialogRecept(this.recept);
  }

  unesiDijagnoze() {
    this.openDialogDijagnoza(this.pacijentZaPregled.zdravstveniKarton);
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

  openDialogDijagnoza(zdravstveniKarton: ZdravstveniKarton): void {
    const dialogRef = this.dialog.open(DijalogUnosDijagnozaComponent, {
      data: zdravstveniKarton
    });

    dialogRef.afterClosed().subscribe(
      result => {
        if (result !== null) {
          this.pacijentZaPregled.zdravstveniKarton = result;
          this.uneteDijagnoze = true;
        }
      },
      err => console.log('Neuspesno otvaranje prozora!'),
      () => console.log(this.pacijentZaPregled.zdravstveniKarton)
    );
  }

  onSubmit() {
    this.submitted = true;
    if (this.izvestajForm.invalid) {
      return;
    }

    this.loading = true;

    this.noviIzvestaj.zdravstveniKarton = this.pacijentZaPregled.zdravstveniKarton;
    this.noviIzvestaj.lekar = this.sessionService.lekarZaPregled;
    this.noviIzvestaj.recept = this.recept;
    this.noviIzvestaj.datum = this.sessionService.datumZaPregled;

    this.opstiIzvestajService.update(this.opstiIzvestaj).subscribe( data =>
      console.log(data)
    );

    this.pacijentService.update(this.pacijentZaPregled).subscribe(data =>
      console.log(data)
    );

    this.pregledService.zavrsenPregled(this.sessionService.pregled).subscribe(data =>
      this.router.navigate(['/pretragaPacijenata'])
    );


    // posalji opstiIzvestaj na opstiIzvestajService
    // posalji zdravstveniKarton na zdravstveniKartonService
    // posalji noviIzvestaj na izvestaj service
    // posalji pregled na pregledService da se apdejtuje da je zavrsen


  }
}
