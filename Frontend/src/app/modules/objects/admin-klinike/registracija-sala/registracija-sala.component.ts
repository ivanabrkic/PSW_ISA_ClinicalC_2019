import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { first } from 'rxjs/operators';
import { Sala } from 'src/app/models/sala/sala';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { Klinika } from 'src/app/models/klinika/klinika';

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

  izmena:boolean

  title:String
  buttonTitle:String

  idSale : number

  constructor(private _snackBar: MatSnackBar,private dialogRef: MatDialogRef<RegistracijaSalaComponent>,
    private formBuilder: FormBuilder, private klinikaService: KlinikaService, private adminkService: AdminKlinikeService, @Inject(MAT_DIALOG_DATA) data) {
      this.izmena = data.izmeni
      if (data.izmeni){
        this.title = 'Izmeni podatke o sali'
        this.buttonTitle = 'Izmeni'
        this.sala = data.sala
        this.idSale = data.sala.id
      }
      else{
        this.title = 'Registruj novu salu'
        this.buttonTitle = 'Registruj'
        this.sala = new Sala()
        this.sala.naziv = ""
        this.sala.broj = ""
      }
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
    this.sala.klinika = new Klinika()
    this.sala.klinika.id = this.idKlinike;
    this.sala.id = this.idSale

    if (this.izmena){

      this.klinikaService.updateSala(this.sala).pipe(first()).subscribe(result => {
        alert(result.text);
      },
        error => {
          alert('Neuspešna registracija!\n\n');
          this.loading = false;
        }); 
    }
    else{

      this.klinikaService.registerSala(this.sala).pipe(first()).subscribe(result => {
        alert(result.text);
      },
        error => {
          alert('Neuspešna registracija!\n\n');
          this.loading = false;
        });
    }

  
    this.dialogRef.close()

  }

  get f() { return this.registerForm.controls; }


}
