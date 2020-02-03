import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { KorisnikService } from 'src/app/services/korisnik-service/korisnik.service';

@Component({
  selector: 'app-dijalog-odbijanje-zahteva',
  templateUrl: './dijalog-odbijanje-zahteva.component.html',
  styleUrls: ['./dijalog-odbijanje-zahteva.component.css']
})
export class DijalogOdbijanjeZahtevaComponent implements OnInit {
  razlog: string;
  constructor(public dialogRef: MatDialogRef<DijalogOdbijanjeZahtevaComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, private korisnikService: KorisnikService) { }

  ngOnInit() {
  }

  obrisiKorisnika(razlog: string) {
    this.dialogRef.close();
    console.log('Podaci poslati u formu');
    console.log(this.data);
    this.korisnikService.deleteKorisnik(this.data, razlog).subscribe(
      data => console.log('Dijalog: log'),
      err => console.log('Greska u dijalogu'),
      () => console.log('Sve je ok')
    );
  }
}
