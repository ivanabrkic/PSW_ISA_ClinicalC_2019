import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {first} from 'rxjs/operators';
import {AdminKc} from '../../../../models/adminkc/admin-kc';
import { AdminKcService } from 'src/app/services/admin-kc-service/admin-kc.service';

@Component({
  selector: 'app-adminkc-izmena',
  templateUrl: './adminkc-izmena.component.html',
  styleUrls: ['./adminkc-izmena.component.css']
})
export class AdminkcIzmenaComponent implements OnInit {

  loading = false;
  adminForm: FormGroup;
  submitted = false;

  adminKc: AdminKc = new AdminKc();

  constructor(private formBuilder: FormBuilder, private adminKcService: AdminKcService) {
    this.adminKcService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.adminKc = ulogovanKorisnik;
      });
  }

  ngOnInit() {
    this.adminKcService.getUlogovanKorisnik()
      .subscribe(ulogovanKorisnik => {
        this.adminKc = ulogovanKorisnik;
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

    this.adminKcService.update(this.adminForm.value).pipe(first()).subscribe(result => {
        this.adminKc = result;
      },
      error => {
        this.loading = false;
      });

  }

}
