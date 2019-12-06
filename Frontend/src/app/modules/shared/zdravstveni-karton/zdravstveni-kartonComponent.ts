import {Component, OnInit} from '@angular/core';
import {ZdravstveniKarton} from '../../../models/zdravstvenik/zdravstveniKarton';
import {dijagnoze} from './tempLista';


@Component({
  selector: 'app-zdravstveni-karton',
  templateUrl: './zdravstveni-kartonComponent.html',
  styleUrls: ['/zdravstveni-kartonComponent.css']
})

export class ZdravstveniKartonComponent implements OnInit {
  // private karton: ZdravstveniKarton;
  private dijagnoze: string[];

  ngOnInit(): void {
   // this.karton.dijagnoze = dijagnoze;
    this.dijagnoze = dijagnoze;
  }
}
