import { Component, OnInit } from '@angular/core';
import {SessionService} from '../../../services/SessionService/session.service';
import {Pacijent} from '../../../models/pacijent/pacijent';
import {Izvestaj} from '../../../models/izvestaj/izvestaj';
import {ZdravstveniKarton} from '../../../models/zdravstvenik/zdravstveniKarton';
import {OpstiIzvestaj} from '../../../models/opsti-izvestaj/opsti-izvestaj';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Recept} from '../../../models/Recept/recept';
import {Lekovi} from '../../../models/Lekovi/lekovi';
import {DijalogKreiranjeReceptaComponent} from '../dijalog-kreiranje-recepta/dijalog-kreiranje-recepta.component';
import {MatDialog} from '@angular/material/dialog';
import {PregledService} from '../../../services/pregled-service/pregled.service';
import {DijalogUnosDijagnozaComponent} from '../dijalog-unos-dijagnoza/dijalog-unos-dijagnoza.component';
import {OpstiIzvestajService} from '../../../services/opsti-izvestaj/opsti-izvestaj.service';
import {ZdravstveniKartonService} from '../../../services/zdravstveni-karton-service/zdravstveni-karton.service';
import {PacijentService} from '../../../services/pacijent-service/pacijent.service';
import {Router} from '@angular/router';
import {IzvestajService} from '../../../services/izvestaj-service/izvestaj.service';
import {ReceptServiceService} from '../../../services/recept-service/recept-service.service';
import {IzvestajDto} from '../../../models/izvestajDTO/izvestaj-dto';

@Component({
  selector: 'app-forma-izvestaj',
  templateUrl: './forma-izvestaj.component.html',
  styleUrls: ['./forma-izvestaj.component.css']
})
export class FormaIzvestajComponent implements OnInit {
  pacijentZaPregled: Pacijent;
  noviIzvestaj: Izvestaj;
  opstiIzvestaj: OpstiIzvestaj;
  kreiranRecept = false;
  uneteDijagnoze = false;
  objekat: any;
  alergijeNaLek = false;
  noviIzvestajDTO: IzvestajDto;

  zdravstveniKarton: ZdravstveniKarton;
  loading = false;
  izvestajForm: FormGroup;
  submitted = false;
  lekovi: Lekovi[] = [];
  recept: Recept;

  constructor(private sessionService: SessionService, private formBuilder: FormBuilder, public dialog: MatDialog,
              private pregledService: PregledService, private opstiIzvestajService: OpstiIzvestajService,
              private zdravstveniKartonService: ZdravstveniKartonService, private pacijentService: PacijentService,
              private router: Router, private izvestajService: IzvestajService, private receptService: ReceptServiceService) {
    this.pacijentZaPregled = this.sessionService.pacijentProfil;
    this.zdravstveniKarton = this.sessionService.zkPregled;
    this.opstiIzvestaj = this.sessionService.opstiIzvestaj;
    this.noviIzvestaj = new Izvestaj();
    this.recept = new Recept();
    console.log(this.sessionService.pregled);
  }

  get f() { return this.izvestajForm.controls; }

  ngOnInit() {

    this.pacijentZaPregled = this.sessionService.pacijentProfil;
    this.zdravstveniKarton = this.sessionService.zkPregled;
    this.opstiIzvestaj = this.sessionService.opstiIzvestaj;
    this.noviIzvestaj = new Izvestaj();
    this.recept = new Recept();

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
    this.openDialogDijagnoza(this.zdravstveniKarton);
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
          this.zdravstveniKarton = result;
          this.uneteDijagnoze = true;
        }
      },
      err => console.log('Neuspesno otvaranje prozora!'),
      () => console.log(this.zdravstveniKarton)
    );
  }

  onSubmit() {
    this.submitted = true;
    if (this.izvestajForm.invalid) {
      return;
    }

    this.loading = true;

    this.noviIzvestaj.zdravstveniKarton = this.zdravstveniKarton;
    this.noviIzvestaj.lekar = this.sessionService.lekarZaPregled;
    this.noviIzvestaj.datum = this.sessionService.datumZaPregled;
    console.log(this.sessionService.datumZaPregled);
    this.recept.pacijent = this.sessionService.pacijentProfil;

    console.log(this.noviIzvestaj);
    console.log(this.opstiIzvestaj);
    console.log(this.recept);
    console.log(this.zdravstveniKarton);

    this.opstiIzvestajService.update(this.opstiIzvestaj).subscribe( data =>
      console.log(data)
    );
    console.log('Izvestaj je: ' + this.noviIzvestaj);
    this.recept.izvestaj = this.noviIzvestaj;
    console.log('Recept je: ' + this.recept);

    this.receptService.save(this.recept).subscribe( data => console.log('sacuvan recept'));
    this.zdravstveniKartonService.updateDijagnoze(this.zdravstveniKarton).subscribe( data =>
    console.log('sacuvane dijagnoze'));
    this.pregledService.zavrsenPregled(this.sessionService.pregled).subscribe(data =>{
      if(!this.sessionService.fromKalendar) {
        this.router.navigate(['/zdravstveniKarton']);
      } else{
        this.router.navigate(['/radniKalendarLekar']);
      }
    });
  }

  zakaziSledeci : boolean = false

  sledeciPregledOperacija(){
    this.zakaziSledeci = true
  }

  nazad(){
    this.zakaziSledeci = false
  }
}
