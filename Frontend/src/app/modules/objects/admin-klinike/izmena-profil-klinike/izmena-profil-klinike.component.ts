import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { first } from 'rxjs/operators';
import { KlinikaService } from 'src/app/modules/shared/services/klinika-service/klinika.service';
import { AdminKlinikeService } from 'src/app/modules/shared/services/admin-klinike-service/admin-klinike.service';

@Component({
  templateUrl: './izmena-profil-klinike.component.html',
  styleUrls: ['./izmena-profil-klinike.component.css']
})
export class IzmenaProfilKlinikeComponent implements OnInit {

  loading = false;
  klinikaForm: FormGroup;
  submitted = false;

  adminKlinike: AdministratorKlinike = new AdministratorKlinike();

  constructor(private formBuilder: FormBuilder, private klinikaService: KlinikaService, private adminkService: AdminKlinikeService) {
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

    this.klinikaForm = this.formBuilder.group({
      adresa: [''],
      kontaktTelefon: [''],
      drzava: [''],
      grad: [''],
      naziv: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      ocena: ['']
    },  {
    });
  }

  get f() { return this.klinikaForm.controls; }

  onSubmit() {

    this.submitted = true;

    // stop here if form is invalid
    if (this.klinikaForm.invalid) {
      return;
    }

    this.loading = true;

    this.klinikaService.update(this.klinikaForm.value, this.adminKlinike.klinika.id).pipe(first()).subscribe(result => {
        alert('Uspešno ste izmenili profil klinike!\n\n');
        this.adminKlinike.klinika = result;
      },
      error => {
        alert('Neuspešna izmena!\n\n');
        this.loading = false;
      });

  }

}
