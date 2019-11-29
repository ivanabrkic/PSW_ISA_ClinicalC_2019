import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

import { MustMatch } from 'src/app/_helpers/MustMatch';
import {Router} from '@angular/router';
import { first } from 'rxjs/operators';
import {RegisterService} from 'src/app/_services';

@Component({ templateUrl: 'register.component.html', styleUrls: [ 'register.component.css' ] })
export class RegisterComponent implements OnInit {
  loading = false;
  registerForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private registerService: RegisterService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      korIme: ['', Validators.required],
      lozinka: ['', [Validators.required, Validators.minLength(8)]],
      email: ['', [Validators.required, Validators.email]],
      kontaktTelefon: ['', Validators.required],
      ime: ['', Validators.required],
      prezime: ['', Validators.required],
      jbo: ['', Validators.required],
      grad: ['', Validators.required],
      drzava: ['', Validators.required],
      adresa: ['', Validators.required],
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
          alert('Uspešno ste se registrovali!! :-)');
          this.router.navigate(['/login']);
        },
        error => {
          alert('Pokušajte ponovo!!')
          this.loading = false;
        });

    alert(JSON.stringify(this.registerForm.value));

  }
}
