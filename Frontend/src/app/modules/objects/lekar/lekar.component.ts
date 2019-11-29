import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from '@angular/router';
import {MustMatch} from "../../../_helpers/MustMatch";

@Component({
  templateUrl: './lekar.component.html',
  styleUrls: ['./lekar.component.css']
})
export class LekarComponent implements OnInit {

  loading = false;
  lekarForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.lekarForm = this.formBuilder.group({
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
  get f() { return this.lekarForm.controls; }

  onSubmit() {

    this.submitted = true;

    // stop here if form is invalid
    if (this.lekarForm.invalid) {
      return;
    }
  }

}
