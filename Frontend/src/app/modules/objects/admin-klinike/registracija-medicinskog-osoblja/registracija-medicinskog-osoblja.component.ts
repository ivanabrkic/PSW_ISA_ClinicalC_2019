import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { Vreme } from 'src/app/models/vreme/vreme';
import { MedicinskaSestra } from 'src/app/models/medicinskas/medicinskas';
import { Lekar } from 'src/app/models/lekar/lekar';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { LekarService } from 'src/app/modules/shared/services/lekar-service/lekar.service';
import { MedicinskaSestraService } from 'src/app/modules/shared/services/medicinska-sestra-service/medicinska-sestra.service';
import { first } from 'rxjs/operators';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { AdminKlinikeService } from 'src/app/modules/shared/services/admin-klinike-service/admin-klinike.service';
import { TabelaMedicinskogOsobljaComponent } from '../tabela-medicinskog-osoblja/tabela-medicinskog-osoblja.component';


@Component({
  templateUrl: './registracija-medicinskog-osoblja.component.html',
  styleUrls: ['./registracija-medicinskog-osoblja.component.css']
})
export class RegistracijaMedicinskogOsobljaComponent implements OnInit {

  selectedValue: String;
  selectedSpec: String;
  selectedTip: string;

  radnoVreme: String[];
  specijalizacije: String[];

  registerForm: FormGroup;
  loading = false;
  submitted = false;

  lekar: Lekar = new Lekar();
  medSestra: MedicinskaSestra = new MedicinskaSestra();
  adminKlinike: AdministratorKlinike = new AdministratorKlinike();

  constructor(private dialogRef: MatDialogRef<RegistracijaMedicinskogOsobljaComponent>,
    private formBuilder: FormBuilder, private adminkService:AdminKlinikeService, private lekarService: LekarService, private medSestraService: MedicinskaSestraService
  ) {
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

    this.radnoVreme = ['Prva smena od 8:00 - 16:00', 'Druga smena od 16:00 do 00:00', 'Treca smena od 00:00 do 8:00']
    this.specijalizacije = ['Zubar', 'Kardiolog', 'Anesteziolog', 'Psihijatar', 'Ginekolog', 'Opšta praksa', 'Nefrolog', 'Urolog', 'Dermatolog', 'Neurolog']

    this.selectedValue = this.radnoVreme[0]
    this.selectedSpec = this.specijalizacije[0]

    this.selectedTip = "Lekar"

    this.dialogRef.updatePosition({
      top: '5%',
      left: '20%'
    });

    this.registerForm = this.formBuilder.group({
      adresa: [''],
      kontaktTelefon: [''],
      drzava: [''],
      grad: [''],
      ime: ['', Validators.required],
      prezime: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      tipKorisnika: [''],
      klinika: [''],
      jbo: ['', [Validators.required, Validators.min(100000000), Validators.pattern('^[0-9]*$')]],
      radnoVreme: [''],
      specijalizacija: ['']
    }, {
    });
  }

  onChange(newValue) {
    this.selectedValue = newValue;
  }

  onChangeTip(newValue) {
    this.selectedTip = newValue;
  }

  onChangeSpec(newValue) {
    this.selectedSpec = newValue;
  }

  get f() { return this.registerForm.controls; }

  onSubmit() {

    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.registerForm.controls['radnoVreme'].setValue(this.selectedValue)
    this.registerForm.controls['specijalizacija'].setValue(this.selectedSpec)
    this.registerForm.controls['tipKorisnika'].setValue(this.selectedTip)
    this.registerForm.controls['klinika'].setValue(this.adminKlinike.klinika)

    this.loading = true;

    if (this.selectedTip == "Lekar") {

      this.lekarService.register(this.registerForm.value).pipe(first()).subscribe(result => {
        alert('Uspešno ste registrovali lekara!\n\n');
        this.lekar = result;
      },
        error => {
          alert('Neuspešna registracija!\n\n');
          this.loading = false;
        });
    }
    else {
      this.medSestraService.register(this.registerForm.value).pipe(first()).subscribe(result => {
        alert('Uspešno ste registrovali medicinsku sestru!\n\n');
        this.medSestra = result;
      },
        error => {
          alert('Neuspešna registracija!\n\n');
          this.loading = false;
        });
    }

    this.dialogRef.close()

  }

}
