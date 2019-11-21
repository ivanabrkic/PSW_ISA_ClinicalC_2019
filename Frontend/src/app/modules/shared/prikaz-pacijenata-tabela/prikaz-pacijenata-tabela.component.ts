import { Component, OnInit } from '@angular/core';
import {IKorisnik} from '../../../models/korisnik';
import {KorisnikService} from '../../../models/korisnik.service';

@Component({
  selector: 'app-prikaz-pacijenata-tabela',
  templateUrl: './prikaz-pacijenata-tabela.component.html',
  styleUrls: ['./prikaz-pacijenata-tabela.component.css']
})
export class PrikazPacijenataTabelaComponent implements OnInit {

  errorMessage = '';
  lekari: IKorisnik[] = [];

  constructor(private korisnikService: KorisnikService) { }

  ngOnInit() {
    this.korisnikService.getLekari().subscribe({
      next: lekari => {
        this.lekari = lekari;
      },
      error: err => this.errorMessage = err
    });
  }

}
