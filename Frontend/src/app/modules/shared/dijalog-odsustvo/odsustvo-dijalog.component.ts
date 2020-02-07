import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {DatePipe} from '@angular/common';
import {ZahtevOdsustvo} from '../../../models/zahtev-odsustvo/zahtev-odsustvo';
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

  constructor(private datePipe: DatePipe, private dialogRef: MatDialogRef<OdsustvoDijalogComponent>,
              @Inject(MAT_DIALOG_DATA) private data: any, private sessionService: SessionService,
              private snackBar: MatSnackBar, private zoService: ZahtevOdsustvoService) {
    this.datumi = data;
    this.razlika = new Date(this.datumi.split('|')[1]).getDate() - new Date(this.datumi.split('|')[0]).getDate();
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
      console.log(new Date(pregled.datum).getDate());
      console.log(new Date(this.datumi.split('|')[0]))
      if (new Date(pregled.datum).getDate() >= new Date(this.datumi.split('|')[0]).getDate() &&
        new Date(pregled.datum).getDate() < new Date(this.datumi.split('|')[1]).getDate()
      ) {
        this.snackBar.open('Postoje zakazani termini pregleda u odabranom periodu!');
        this.dialogRef.close(null);
      }
    });
    if (this.opis === 'Dodatni opis') {
      this.zo = new ZahtevOdsustvo(this.od, this.do, this.razlika, '', this.sessionService.ulogovanLekar, false);
    } else {
      this.zo = new ZahtevOdsustvo(this.od, this.do, this.razlika, this.opis, this.sessionService.ulogovanLekar, false);
    }
    console.log(this.zo);
    this.zoService.posaljiZahtev(this.zo).pipe(first()).subscribe(data => console.log('poslat zahtev!'));
    this.dialogRef.close(null);
  }
}
