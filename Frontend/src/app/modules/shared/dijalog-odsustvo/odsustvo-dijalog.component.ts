import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {DatePipe} from '@angular/common';
import { ZahtevOdsustvo} from '../../../models/zahtev-odsustvo/zahtev-odsustvo';
import {SessionService} from '../../../services/SessionService/session.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {first} from 'rxjs/operators';
import {ZahtevOdsustvoService} from '../../../services/zahtev-odsustvo-service/zahtev-odsustvo.service';

@Component({
  selector: 'app-odsustvo-dijalog',
  templateUrl: './odsustvo-dijalog.component.html',
  styleUrls: ['./odsustvo-dijalog.component.css']
})
export class OdsustvoDijalogComponent implements OnInit {
  public od: string;
  public do: string;
  public datumi: string;
  public razlika: number;
  public zo: ZahtevOdsustvo;
  public opis: string;
  public podaci: any;
  public uslov = true;
  public datumOd: number;
  public datumDo: number;

  constructor(private datePipe: DatePipe, private dialogRef: MatDialogRef<OdsustvoDijalogComponent>,
              @Inject(MAT_DIALOG_DATA) private data: any, private sessionService: SessionService,
              private snackBar: MatSnackBar, private zoService: ZahtevOdsustvoService) {
    this.datumi = data;

    this.datumDo = new Date(this.datumi.split('|')[1]).getDate();
    this.datumOd = new Date(this.datumi.split('|')[0]).getDate();

    if (this.datumOd > this.datumDo) {
      const mesecDo = new Date(this.datumi.split('|')[1]).getMonth();
      const godinaDo = new Date(this.datumi.split('|')[1]).getFullYear();
      const brojDanaMeseca = this.izracunajRazliku(mesecDo, godinaDo);

      this.razlika = brojDanaMeseca - this.datumOd + this.datumDo;
      console.log(this.razlika);
    } else {
      this.razlika = new Date(this.datumi.split('|')[1]).getUTCDate() - new Date(this.datumi.split('|')[0]).getUTCDate();
    }
    console.log(this.datumi);
    this.od = this.datePipe.transform(new Date(this.datumi.split('|')[0]), 'd.M.yyyy.');
    this.do = this.datePipe.transform(new Date(this.datumi.split('|')[1]), 'd.M.yyyy.');

    console.log(this.od);
    console.log(this.do);
    console.log(this.razlika);
  }

  ngOnInit() {
  }

  posaljiZahtev() {
    this.sessionService.preglediKalendar.forEach(pregled => {
      console.log(pregled);
      console.log(new Date(pregled.datum).getMonth() + " " + new Date(this.datumi.split('|')[0]).getDate())
      //dobijaju se NaN vrednosti za getDate kod nekih pregleda
      //provera tipa: od 31.01 do 03.02.
      if (this.sessionService.ulogovanLekarBool) {
        if (this.datumOd > this.datumDo) {
          if (!(new Date(pregled.datum).getMonth() < new Date(this.datumi.split('|')[0]).getDate() &&
            new Date(pregled.datum).getMonth() >= new Date(this.datumi.split('|')[1]).getDate())) {

            this.snackBar.open('Postoje zakazani termini pregleda ili operacija u odabranom periodu!', "", {
              duration: 3000,
              verticalPosition: 'bottom'
            });
            this.dialogRef.close(null);
            this.uslov = false;
            return;
          }

          if (new Date(pregled.datum).getMonth() >= new Date(this.datumi.split('|')[0]).getDate() &&
            new Date(pregled.datum).getMonth() < new Date(this.datumi.split('|')[1]).getDate()
          ) {
            this.snackBar.open('Postoje zakazani termini pregleda ili operacija u odabranom periodu!', "",  {
              duration: 3000,
              verticalPosition: 'bottom'
            });
            this.dialogRef.close(null);
            this.uslov = false;
            return;
          }
        }
      }
    });

    if (this.uslov) {
      if (this.opis === 'Dodatni opis') {
        this.zo = new ZahtevOdsustvo(this.od, this.do, this.razlika, '', this.sessionService.ulogovanKorinik.ime,
          // tslint:disable-next-line:max-line-length
          this.sessionService.ulogovanKorinik.prezime, this.sessionService.ulogovanKorinik.jbo, this.sessionService.ulogovanKorinik.tipKorisnika, false);
      } else {
        this.zo = new ZahtevOdsustvo(this.od, this.do, this.razlika, this.opis, this.sessionService.ulogovanKorinik.ime,
          // tslint:disable-next-line:max-line-length
          this.sessionService.ulogovanKorinik.prezime, this.sessionService.ulogovanKorinik.jbo, this.sessionService.ulogovanKorinik.tipKorisnika, false);
      }
      console.log(this.zo);
      this.zoService.posaljiZahtev(this.zo).pipe(first()).subscribe(data => console.log('poslat zahtev!'));
      this.dialogRef.close(null);
    }
  }

  izracunajRazliku(tempMesecDo: number, tempGodinaDo: number) {
    return new Date(tempGodinaDo, tempMesecDo, 0).getDate();
  }
}
