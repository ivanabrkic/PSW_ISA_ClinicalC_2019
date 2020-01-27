import { Component, OnInit } from '@angular/core';
import { Zahtev } from 'src/app/models/zahtev/zahtev';

@Component({
  selector: 'app-zahtevi-operacije-pregledi',
  templateUrl: './zahtevi-operacije-pregledi.component.html',
  styleUrls: ['./zahtevi-operacije-pregledi.component.css']
})
export class ZahteviOperacijePreglediComponent implements OnInit {

  zahtevi: Zahtev[] = []

  constructor() { }

  ngOnInit() {
  }

}
