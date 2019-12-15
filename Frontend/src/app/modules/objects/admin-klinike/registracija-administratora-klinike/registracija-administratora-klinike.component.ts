import { Component, OnInit } from '@angular/core';
import {AdministratorKlinike} from '../../../../models/administrator-klinike';
import {ActivatedRoute, Router} from '@angular/router';
import {Form, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {first} from 'rxjs/operators';
import {Klinika} from '../../../../models/Klinika/klinika';
import {HelperUserClass} from '../../../../_helpers/helper-user-class';
import {AdminKlinikeService} from '../../../../_services/admin-klinike-service/admin-klinike.service';
import {KlinikaService} from '../../../../_services/KlinikaService/klinika.service';

@Component({
  selector: 'app-registracija-administratora-klinike',
  templateUrl: './registracija-administratora-klinike.component.html',
  styleUrls: ['./registracija-administratora-klinike.component.css']
})
export class RegistracijaAdministratoraKlinikeComponent implements OnInit {
  submitted = false;
  helperClass: HelperUserClass;
  loading = false;
  adminForm: FormGroup;
  klinike = null;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private AdminService: AdminKlinikeService,
              private klinikaService: KlinikaService,
              private formBuilder: FormBuilder
  ) {
    this.helperClass = new HelperUserClass();
    this.getKlinike();
  }

  getKlinike() {
    this.klinikaService.getKlinike().subscribe(
      data => this.klinike = data,
      err => console.log('Neuspesno ucitani podaci'),
      () => console.log('Podaci uspesno ucitani')
    );
  }

  ngOnInit() {
    this.adminForm = this.formBuilder.group({
      adresa: [''],
      kontaktTelefon: [''],
      drzava: [''],
      grad: [''],
      ime: ['', [Validators.required, Validators.required]],
      prezime: ['', [Validators.required, Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      lozinka: ['', [Validators.required, Validators.minLength(8), Validators.pattern('^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$'
      )]],
      // tipKorisnika: ['', Validators.required],
      jbo: ['', [Validators.required,  Validators.minLength(9), Validators.maxLength(9)]],
      korIme: ['', [Validators.required, Validators.required]],
      klinika: ['']
    },  {
    });
  }

  onSubmit() {
    console.log(this.adminForm.value);
    if (this.adminForm.invalid) {
      return;
    }

    this.submitted = true;
    // stop here if form is invalid

    this.loading = true;
    this.AdminService.save(this.helperClass).pipe(first()).subscribe(result => {
        alert('Prosledjivanje zahteva');
      },
      error => {
        this.router.navigate(['administratorKc'])
        this.loading = false;
      },
      () => console.log('Korisnik registrovan!'));
  }

  get f() { return this.adminForm.controls; }

}
