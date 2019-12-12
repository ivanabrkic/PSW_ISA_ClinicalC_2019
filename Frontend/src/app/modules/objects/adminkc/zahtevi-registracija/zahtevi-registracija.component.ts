import { Component, OnInit } from '@angular/core';
import {KorisnikServiceService} from '../../../../_services/KorisnikService/korisnik-service.service';
import {Korisnik} from '../../../../models/korisnik';
import { PacijentService } from 'src/app/_services/PacijentService/pacijent.service';

@Component({
  selector: 'app-zahtevi-registracija',
  templateUrl: './zahtevi-registracija.component.html',
  styleUrls: ['./zahtevi-registracija.component.css']
})
export class ZahteviRegistracijaComponent implements OnInit {

  public korisnici;
  public izmenjeniKorisnik: Korisnik = new Korisnik();


  constructor(private pacijentService:PacijentService, private korisnikService: KorisnikServiceService) {
      this.getKorisnike();
  }

  ngOnInit() {
    this.getKorisnike();
  }

  getKorisnike() {
    this.pacijentService.getPacijenti().subscribe(
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
    this.korisnikService.updateAktivnost(this.izmenjeniKorisnik).subscribe(data => {
      this.getKorisnike();
    });
  }

  onItemRejected(index: number) {
    this.korisnici.splice(index, 1);
  }
}
