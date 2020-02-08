import { Component, OnInit } from '@angular/core';
import {Klinika} from '../../../../models/klinika/klinika';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {first} from 'rxjs/operators';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-registracija-klinike',
  templateUrl: './registracija-klinike.component.html',
  styleUrls: ['./registracija-klinike.component.css']
})
export class RegistracijaKlinikeComponent implements OnInit {
  klinika: Klinika;
  adminForm: FormGroup;
  submitted = false;
  loading = false;

  constructor(private _snackBar: MatSnackBar,
    private route: ActivatedRoute,
    private router: Router,
    private klinikaService: KlinikaService,
    private formBuilder: FormBuilder
  ) {
    this.klinika = new Klinika();
  }

  ngOnInit() {
    this.adminForm = this.formBuilder.group({
      naziv: ['', Validators.required],
      email: ['', Validators.required],
      grad: [''],
      drzava: [''],
      adresa: [''],
      kontaktTelefon: ['']
    },  {
    });
  }

  onSubmit() {
    console.log(this.klinika)
    if (this.adminForm.invalid) {
      return;
    }

    this.submitted = true;
    // stop here if form is invalid

    this.loading = true;
    this.klinikaService.save(this.klinika).pipe(first()).subscribe(result => {
        alert('Uspešno ste registrovali kliniku!\n\n');
        this.router.navigate(['/administratorKc']);
      },
      error => {
        this.loading = false;
        this.router.navigate(['/administratorKc']);
      });
  }
  get f() { return this.adminForm.controls; }


}
