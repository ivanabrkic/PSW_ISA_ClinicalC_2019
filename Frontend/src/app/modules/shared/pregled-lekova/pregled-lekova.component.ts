import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ReceptServiceService} from '../../../services/recept-service/recept-service.service';
import {Recept} from '../../../models/Recept/recept';

@Component({
  selector: 'app-pregled-lekova',
  templateUrl: './pregled-lekova.component.html',
  styleUrls: ['./pregled-lekova.component.css']
})
export class PregledLekovaComponent implements OnInit {
  idRecepta: number;
  objekat: any;
  recept: Recept;
  constructor(public dialogRef: MatDialogRef<PregledLekovaComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, private receptService: ReceptServiceService) {
    this.idRecepta = data;
    console.log(data);
    this.receptService.nadjiRecept(this.idRecepta).subscribe(recept => {
        this.objekat = recept;
        this.recept = this.objekat;
        console.log(this.recept);
      }
    );
  }

  ngOnInit() {
  }

}
