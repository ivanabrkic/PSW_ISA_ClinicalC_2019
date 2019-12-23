import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {first} from 'rxjs/operators';
import { Lekar } from 'src/app/models/lekar/lekar';
import { LekarService } from 'src/app/services/lekar-service/lekar.service';

@Component({
  templateUrl: './lekar.component.html',
  styleUrls: ['./lekar.component.css']
})

export class LekarComponent implements OnInit {

  loading = false;
  lekarForm: FormGroup;
  submitted = false;

  lekar: Lekar = new Lekar();

  constructor(private formBuilder: FormBuilder, private lekarService: LekarService) {
    this.lekarService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.lekar = ulogovanKorisnik;
    });
  }

  ngOnInit() {
    this.lekarService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.lekar = ulogovanKorisnik;
      });

    this.lekarForm = this.formBuilder.group({
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

  // convenience getter for easy access to form fields
  get f() { return this.lekarForm.controls; }

  onSubmit() {

    this.submitted = true;
    // stop here if form is invalid
    if (this.lekarForm.invalid) {
      return;
    }

    this.loading = true;

    this.lekarService.update(this.lekarForm.value).pipe(first()).subscribe(result => {
        alert('Uspešno ste izmenili svoj profil!\n\n');
        this.lekar = result;
      },
      error => {
        alert('Neuspešna izmena!\n\n');
        this.loading = false;
      });

  }

}
