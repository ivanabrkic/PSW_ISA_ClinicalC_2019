import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {first} from 'rxjs/operators';
import { MedicinskaSestra } from 'src/app/models/medicinskas/medicinskas';
import { MedicinskaSestraService } from 'src/app/services/medicinska-sestra-service/medicinska-sestra.service';

@Component({
  templateUrl: './medsestra-izmena.component.html',
  styleUrls: ['./medsestra-izmena.component.css']
})

export class MedSestraIzmenaComponent implements OnInit {

  loading = false;
  medsForm: FormGroup;
  submitted = false;

  medSestra: MedicinskaSestra = new MedicinskaSestra();

  constructor(private formBuilder: FormBuilder, private medsService: MedicinskaSestraService) {
    this.medsService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.medSestra = ulogovanKorisnik;
    });
  }

  ngOnInit() {
    this.medsService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.medSestra = ulogovanKorisnik;
      });

    this.medsForm = this.formBuilder.group({
      adresa: [''],
      kontaktTelefon: [''],
      drzava: [''],
      grad: [''],
      ime: ['', Validators.required],
      prezime: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      lozinka: ['', [Validators.required, Validators.minLength(8), Validators.pattern('^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$'
      )]],
      jbo: [''],
      korIme: ['', Validators.required]
    },  {
    });
  }

  get f() { return this.medsForm.controls; }

  onSubmit() {

    this.submitted = true;
    // stop here if form is invalid
    if (this.medsForm.invalid) {
      return;
    }

    this.loading = true;

    this.medsService.update(this.medsForm.value).pipe(first()).subscribe(result => {
        alert('Uspešno ste izmenili svoj profil!\n\n');
        this.medSestra = result;
      },
      error => {
        alert('Neuspešna izmena!\n\n');
        this.loading = false;
      });

  }

}
