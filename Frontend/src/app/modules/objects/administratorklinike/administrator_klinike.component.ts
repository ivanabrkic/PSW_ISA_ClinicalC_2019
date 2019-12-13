import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NavigationStart, Router} from '@angular/router';
import {AdministratorKlinike} from '../../../models/administrator-klinike';
import {AdminKlinikeServiceService} from '../../../_services/AdministratorKlinikeService/admin-klinike-service.service';
import {Observable, Subscription} from 'rxjs';
import {first} from 'rxjs/operators';

@Component({
  templateUrl: './administrator_klinike.component.html',
  styleUrls: ['./administrator_klinike.component.css']
})

export class AdministratorKlinikeComponent implements OnInit {
  loading = false;
  adminForm: FormGroup;
  submitted = false;
  adminKlinike: AdministratorKlinike = new AdministratorKlinike();

  constructor(private formBuilder: FormBuilder, private adminkService: AdminKlinikeServiceService) {
    this.adminkService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.adminKlinike = ulogovanKorisnik;
    });
  }

  ngOnInit() {
    this.adminkService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.adminKlinike = ulogovanKorisnik;
      });

    this.adminForm = this.formBuilder.group({
      adresa: [''],
      kontaktTelefon: [''],
      drzava: [''],
      grad: [''],
      ime: ['', Validators.required],
      prezime: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      lozinka: ['', [Validators.required, Validators.minLength(8), Validators.pattern('^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$'
      )]],
      // tipKorisnika: ['', Validators.required],
      jbo: [''],
      korIme: ['', Validators.required]
    },  {
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.adminForm.controls; }

  onSubmit() {

    this.submitted = true;
    // stop here if form is invalid
    if (this.adminForm.invalid) {
      return;
    }

    this.loading = true;

    this.adminkService.update(this.adminForm.value).pipe(first()).subscribe(result => {
        alert('Uspešno ste izmenili svoj profil!\n\n');
        this.adminKlinike = result;
      },
      error => {
        alert('Neuspešna izmena!\n\n');
        this.loading = false;
      });

  }

}
