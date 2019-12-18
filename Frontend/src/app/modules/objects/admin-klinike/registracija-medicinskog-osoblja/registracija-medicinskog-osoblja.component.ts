import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { Vreme } from 'src/app/models/vreme/vreme';
import { MedicinskaSestra } from 'src/app/models/medicinskas/medicinskas';
import { Lekar } from 'src/app/models/lekar/lekar';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { LekarService } from 'src/app/services/lekar-service/lekar.service';
import { MedicinskaSestraService } from 'src/app/services/medicinska-sestra-service/medicinska-sestra.service';
import { first } from 'rxjs/operators';
import { AdministratorKlinike } from 'src/app/models/admink/administrator-klinike';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { TabelaMedicinskogOsobljaComponent } from '../tabela-medicinskog-osoblja/tabela-medicinskog-osoblja.component';


@Component({
  templateUrl: './registracija-medicinskog-osoblja.component.html',
  styleUrls: ['./registracija-medicinskog-osoblja.component.css']
})
export class RegistracijaMedicinskogOsobljaComponent implements OnInit {

  selectedValue: Vreme;
  radnoVreme: Vreme[];
  radnoVremeString: string;

  selectedTip: string;

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

  public MakeVreme(dan: string): Vreme {

    var r = new Vreme()

    r.dan = dan
    r.H1 = null
    r.H2 = null
    r.M1 = null
    r.M2 = null
    r.slobodan = true

    return r
  }

  ngOnInit() {
    this.adminkService.getUlogovanKorisnik()
    .subscribe(ulogovanKorisnik => {
      this.adminKlinike = ulogovanKorisnik;
    });

    this.radnoVreme = [this.MakeVreme("Ponedeljak"), this.MakeVreme("Utorak"), this.MakeVreme("Sreda"),
    this.MakeVreme("Četvrtak"), this.MakeVreme("Petak"), this.MakeVreme("Subota"),
    this.MakeVreme("Nedelja")]

    this.selectedValue = this.radnoVreme[0]

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
      H1: ['', [Validators.required, Validators.pattern('^(0[0-9]|1[0-9]|2[0-3]|[0-9])')]],
      H2: ['', [Validators.required, Validators.pattern('^(0[0-9]|1[0-9]|2[0-3]|[0-9])')]],
      M1: ['', [Validators.required, Validators.pattern('[0-5][0-9]$')]],
      M2: ['', [Validators.required, Validators.pattern('[0-5][0-9]$')]],
      radnoVreme: ['']
    }, {
    });
  }

  validateSeconds(h1: number, h2: number, m1: number, m2: number): boolean {

    let sekunde1 = 0
    let sekunde2 = 0

    sekunde1 += h1 * 60 * 60 + m1 * 60
    sekunde2 += h2 * 60 * 60 + m2 * 60

    if (sekunde1 == sekunde2) {
      return false
    }
    return true
  }

  onChange(newValue) {
    this.selectedValue = newValue;
  }

  onChangeTip(newValue) {
    this.selectedTip = newValue;
  }

  get f() { return this.registerForm.controls; }

  onSubmit() {

    this.submitted = true;

    this.radnoVreme.forEach(element => {

      if (!this.validateSeconds(element.H1, element.H2, element.M1, element.M2)) {
        this.registerForm.controls['radnoVreme'].setValidators([Validators.email]);
      }
      else {
        this.registerForm.controls['radnoVreme'].setValidators(null);
      }
      if(element.slobodan == true){
        this.registerForm.controls['H1'].setErrors(null);
        this.registerForm.controls['H2'].setErrors(null);
        this.registerForm.controls['M1'].setErrors(null);
        this.registerForm.controls['M2'].setErrors(null);
        this.registerForm.controls['radnoVreme'].setValidators(null);
      }
    });
    const controls = this.registerForm.controls;
    for (const name in controls) {
      if (controls[name].invalid) {
        if (name == "radnoVreme") {
          alert("Neispravno uneto radno vreme! Molimo Vas popunite sva polja!")
        }
      }
    }
    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.registerForm.removeControl('H1')
    this.registerForm.removeControl('H2')
    this.registerForm.removeControl('M1')
    this.registerForm.removeControl('M2')

    this.radnoVremeString = ""

    this.radnoVreme.forEach(element => {
      if(element.slobodan){
        this.radnoVremeString += element.dan + " - " + "slobodan/"
      }
      else{
        this.radnoVremeString += element.dan + " - " + element.H1 + ":" + element.M1 + " - " + element.H2 + ":" + element.M2 + "/"
      }
    });

    this.registerForm.controls['radnoVreme'].setValue(this.radnoVremeString)
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
