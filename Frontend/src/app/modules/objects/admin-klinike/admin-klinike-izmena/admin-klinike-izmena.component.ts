import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AdministratorKlinike} from '../../../../models/admink/administrator-klinike';
import {first} from 'rxjs/operators';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { MatSnackBar } from '@angular/material';

@Component({
  templateUrl: './admin-klinike-izmena.component.html',
  styleUrls: ['./admin-klinike-izmena.component.css']
})

export class AdminKlinikeIzmenaComponent implements OnInit {

  loading = false;
  adminForm: FormGroup;
  submitted = false;
  adminKlinike: AdministratorKlinike = new AdministratorKlinike();

  constructor(private _snackBar: MatSnackBar,private formBuilder: FormBuilder, private adminkService: AdminKlinikeService) {
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
      jbo: ['']
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
      this._snackBar.open("Uspešno ste izmenili svoj profil!", "",  {
        duration: 2000,
        verticalPosition : 'top'
      });
        this.adminKlinike = result;
      },
      error => {
        this._snackBar.open("Neuspešna izmena!", "",  {
          duration: 2000,
          verticalPosition : 'top'
        });
        this.loading = false;
      });

  }

}
