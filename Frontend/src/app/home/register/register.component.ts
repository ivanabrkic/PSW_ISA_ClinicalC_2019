import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

import { MustMatch } from 'src/app/helpers/MustMatch';
import {Router} from '@angular/router';
import { first } from 'rxjs/operators';
import { RegisterService } from 'src/app/services/login-and-register-service/register.service';
import { MatSnackBar } from '@angular/material';

@Component({ templateUrl: 'register.component.html', styleUrls: [ 'register.component.css' ] })
export class RegisterComponent implements OnInit {
  loading = false;
  registerForm: FormGroup;
  submitted = false;

  constructor(private _snackBar: MatSnackBar, private formBuilder: FormBuilder, private router: Router, private registerService: RegisterService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      lozinka: ['', [Validators.required, Validators.minLength(8), Validators.pattern('^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$'
      )]],
      email: ['', [Validators.required, Validators.email]],
      kontaktTelefon: [''],
      ime: ['', Validators.required],
      prezime: ['', Validators.required],
      jbo: ['', [Validators.required, Validators.minLength(13), Validators.pattern('^[0-9]*$')]],
      grad: [''],
      drzava: [''],
      adresa: [''],
      tipKorisnika: ['', Validators.required],

    },  {
      validator: MustMatch('lozinka', 'tipKorisnika')
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {

    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    this.registerService.register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this._snackBar.open("Uspešno ste se registrovali! Molimo Vas ulogujte se kako biste koristili usluge kliničkog centra!", "",  {
            duration: 3000          });
          this.router.navigate(['/login']);
        },
        error => {
          this._snackBar.open("Greška prilikom registracije!", "",  {
            duration: 3000
                    });
          this.loading = false;
        });
  }
}
