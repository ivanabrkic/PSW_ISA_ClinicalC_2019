import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TipPregleda } from 'src/app/models/tippregleda/tippregleda';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';
import { AdminKlinikeService } from 'src/app/services/admin-klinike-service/admin-klinike.service';
import { Sala } from 'src/app/models/sala/sala';
import { first } from 'rxjs/operators';
import { Klinika } from 'src/app/models/klinika/klinika';

@Component({
  selector: 'app-registracija-tipova',
  templateUrl: './registracija-tipova.component.html',
  styleUrls: ['./registracija-tipova.component.css']
})
export class RegistracijaTipovaComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;

  tip: TipPregleda;
  idKlinike:number;

  izmena:boolean

  title:String
  buttonTitle:String

  idTipa : number

  selectedSpec: String;
  specijalizacije: String[];


  constructor(private _snackBar: MatSnackBar,private dialogRef: MatDialogRef<RegistracijaTipovaComponent>,
    private formBuilder: FormBuilder, private klinikaService: KlinikaService, private adminkService: AdminKlinikeService, @Inject(MAT_DIALOG_DATA) data) {
      this.izmena = data.izmeni
      if (data.izmeni){
        this.title = 'Izmeni podatke o tipu pregleda'
        this.buttonTitle = 'Izmeni'
        this.tip = data.tip
        this.idTipa = data.tip.id

        this.specijalizacije = ['Zubar', 'Kardiolog', 'Anesteziolog', 'Psihijatar', 'Ginekolog', 'Opšta praksa', 'Nefrolog', 'Urolog', 'Dermatolog', 'Neurolog']
        this.selectedSpec = this.tip.specijalizacija
      }
      else{
        this.title = 'Registruj novi tip pregleda'
        this.buttonTitle = 'Registruj'
        this.tip = new TipPregleda()
        this.tip.naziv = ""
        this.tip.specijalizacija = "Zubar"
        this.tip.cena = 0
      }}

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
      cena: ['', Validators.required],
      specijalizacija: ['', Validators.required]
    }, {
    });

  }

  onChangeSpec(newValue) {
    this.selectedSpec = newValue;
  }

  onSubmit(){

    this.submitted = true;

    this.registerForm.controls['specijalizacija'].setValue(this.selectedSpec)

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;


    this.tip = this.registerForm.value;
    this.tip.klinika = new Klinika()
    this.tip.klinika.id = this.idKlinike;
    this.tip.id = this.idTipa

    if (this.izmena){

      this.klinikaService.updateTip(this.tip).pipe(first()).subscribe(result => {
        alert(result.text);
      },
        error => {
          alert('Neuspešna registracija!\n\n');
          this.loading = false;
        }); 
    }
    else{
      this.klinikaService.registerTip(this.tip).pipe(first()).subscribe(result => {
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
