import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {first} from 'rxjs/operators';
import { Pacijent } from 'src/app/models/pacijent/pacijent';
import { PacijentService } from 'src/app/modules/shared/services/pacijent-service/pacijent.service';

@Component({
  templateUrl: './pacijent.component.html',
  styleUrls: ['./pacijent.component.css']
})

export class PacijentComponent implements OnInit {

  loading = false;
  pacijentForm: FormGroup;
  submitted = false;

  pacijent: Pacijent = new Pacijent();

  constructor(private formBuilder: FormBuilder, private pacijentService: PacijentService) {
    this.pacijentService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.pacijent = ulogovanKorisnik;
    });
  }

  ngOnInit() {
    this.pacijentService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.pacijent = ulogovanKorisnik;
      });

    this.pacijentForm = this.formBuilder.group({
      adresa: [''],
      kontaktTelefon: [''],
      drzava: [''],
      grad: [''],
      ime: ['', Validators.required],
      prezime: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      lozinka: ['', [Validators.required, Validators.minLength(8), Validators.pattern('^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$'
      )]],
      jbo: ['']
      },  {
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.pacijentForm.controls; }

  onSubmit() {

    this.submitted = true;
    // stop here if form is invalid
    if (this.pacijentForm.invalid) {
      return;
    }

    this.loading = true;

    this.pacijentService.update(this.pacijentForm.value).pipe(first()).subscribe(result => {
        alert('Uspešno ste izmenili svoj profil!\n\n');
        this.pacijent = result;
      },
      error => {
        alert('Neuspešna izmena!\n\n');
        this.loading = false;
      });

  }

}
