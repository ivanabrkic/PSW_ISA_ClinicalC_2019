import { Component, OnInit } from '@angular/core';
import {KorisnikServiceService} from '../../../../_services/KorisnikService/korisnik-service.service';
import {Korisnik} from '../../../../models/korisnik';

@Component({
  selector: 'app-zahtevi-registracija',
  templateUrl: './zahtevi-registracija.component.html',
  styleUrls: ['./zahtevi-registracija.component.css']
})
export class ZahteviRegistracijaComponent implements OnInit {
  public korisnici;
  public izmenjeniKorisnik: Korisnik;
  constructor(private korisnikService: KorisnikServiceService) {

  }

  ngOnInit() {
    this.getKorisnike();
  }

  getKorisnike() {
    this.korisnikService.getKorisnike().subscribe(
      podaci => { this.korisnici = podaci; },
      err => console.log('Nisu ucitani korisnici'),
      () => console.log(this.korisnici)
    );
  }

  onItemAccepted(korisnik: Korisnik) {
    const indexKorisnika = this.korisnici.findIndex(item => item.jbo === korisnik.jbo);
    this.izmenjeniKorisnik = this.korisnici.find(item => item.jbo === korisnik.jbo);
    this.izmenjeniKorisnik.aktivnostNaloga = true;
    this.korisnici[indexKorisnika] = this.izmenjeniKorisnik;
    this.korisnikService.updateAktivnost(this.izmenjeniKorisnik);
  }

  onItemRejected(index: number) {
    this.korisnici.splice(index, 1);
  }
}
