import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  templateUrl: './administrator_klinike.component.html',
  styleUrls: ['./administrator_klinike.component.css']
})
export class AdministratorKlinikeComponent implements OnInit {

  loading = false;
  adminForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.adminForm = this.formBuilder.group({
      adresa: ['', Validators.required],
      kontaktTelefon: ['', Validators.required],
      drzava: ['', Validators.required],
      grad: ['', Validators.required],
      ime: ['', Validators.required],
      prezime: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      lozinka: ['', [Validators.required, Validators.minLength(8)]],
      tipKorisnika: ['', Validators.required],
      jbo: ['', Validators.required],
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
  }

}
