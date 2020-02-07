import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-pregled-izvestaja',
  templateUrl: './pregled-izvestaja.component.html',
  styleUrls: ['./pregled-izvestaja.component.css']
})
export class PregledIzvestajaComponent implements OnInit {
  izvestaj: string;
  constructor(public dialogRef: MatDialogRef<PregledIzvestajaComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.izvestaj = data;
    console.log(data);
  }

  ngOnInit() {
  }

}
