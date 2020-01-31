import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { KlinikaService } from 'src/app/modules/shared/services/klinika-service/klinika.service';
import { first } from 'rxjs/operators';
import { Sala } from 'src/app/models/sala/sala';
import { AdminKlinikeService } from 'src/app/modules/shared/services/admin-klinike-service/admin-klinike.service';

@Component({
  templateUrl: './registracija-sala.component.html',
  styleUrls: ['./registracija-sala.component.css']
})
export class RegistracijaSalaComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;

  sala: Sala;
  idKlinike:number;

  constructor(private dialogRef: MatDialogRef<RegistracijaSalaComponent>,
    private formBuilder: FormBuilder, private klinikaService: KlinikaService, private adminkService: AdminKlinikeService) {
  }

  ngOnInit() {

    this.adminkService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.idKlinike = ulogovanKorisnik.klinika.id;
    });

    this.dialogRef.updatePosition({
      top: '5%',
      left: '20%'
    });


    this.registerForm = this.formBuilder.group({
      naziv: ['', Validators.required],
      broj: ['', Validators.required]
    }, {
    });
  }

  onSubmit() {

    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;

    this.sala = this.registerForm.value;
    this.sala.id = this.idKlinike;

    this.klinikaService.registerSala(this.sala).pipe(first()).subscribe(result => {
      alert(result.naziv);
    },
      error => {
        alert('Neuspešna registracija!\n\n');
        this.loading = false;
      });


    this.dialogRef.close()

  }

  get f() { return this.registerForm.controls; }


}
