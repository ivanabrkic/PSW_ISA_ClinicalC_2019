import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {LoginService} from '../../../services/login-and-register-service/login.service';
import {KorisnikService} from '../../../services/korisnik-service/korisnik.service';
import {Korisnik} from '../../../models/korisnik/korisnik';
import {first} from 'rxjs/operators';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-promena-sifre',
  templateUrl: './promena-sifre.component.html',
  styleUrls: ['./promena-sifre.component.css']
})
export class PromenaSifreComponent implements OnInit {
  loading = false;
  loginForm: FormGroup;
  submitted = false;
  lozinka: string;
  potvrda: string;
  korisnik: Korisnik;

  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService,
              private korisnikService: KorisnikService, private snackBar: MatSnackBar) {
    this.korisnikService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.korisnik = ulogovanKorisnik;
        console.log(this.korisnik);
      });
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      lozinka: ['', [Validators.required, Validators.minLength(8)]],
      potvrda: ['', [Validators.required, Validators.minLength(8)]],
    }, );
  }

  get f() { return this.loginForm.controls; }

  public onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;

    if (this.lozinka !== this.potvrda) {
      this.snackBar.open('Lozinke nisu iste!', "",  {
        duration: 3000,
        verticalPosition: 'bottom'
      });
      return;
    }

    console.log(this.korisnik);
    this.korisnik.lozinka = this.lozinka;
    this.korisnik.prvoLogovanje = false;
    this.korisnikService.updateKorisnik(this.korisnik).pipe(first()).subscribe(result =>{
      if (this.korisnik.tipKorisnika === 'Pacijent') {
        this.router.navigate(['/pacijentPregled']);
      } else if (this.korisnik.tipKorisnika === 'Lekar') {
        this.router.navigate(['/lekarPregled']);
      } else if (this.korisnik.tipKorisnika === 'Medicinska sestra') {
        this.router.navigate(['/medicinskaSestra']);
      } else if (this.korisnik.tipKorisnika === 'Administrator klinike') {
        this.router.navigate(['/administratorklinikepregled']);
      } else if (this.korisnik.tipKorisnika === 'Administrator klinickog centra') {
        this.router.navigate(['/administratorKc']);
      } else {
        this.router.navigate(['/welcome']);
      }
    });
  }

}
