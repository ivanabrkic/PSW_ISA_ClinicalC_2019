import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {HelperRejectedMail} from '../../models/HelperRejectedMail/helper-rejected-mail';
import { Korisnik } from 'src/app/models/korisnik/korisnik';



const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})

export class KorisnikService {
  helperRejectedMail: HelperRejectedMail;
  constructor(private http: HttpClient) { }

  getKorisnike() {
    return this.http.get('/server/korisnik/all');
  }

  updateKorisnik(noviKorisnik) {
    const body = JSON.stringify(noviKorisnik);
    return this.http.post('/server/korisnik/update', body, httpOptions);
  }

  updateAktivnost(noviKorisnik: Korisnik) {
    const body = JSON.stringify(noviKorisnik);
    return this.http.post('/server/korisnik/updateAktivnost', body, httpOptions);
  }

  deleteKorisnik(korisnik: Korisnik, razlog: string) {
    console.log(korisnik);
    console.log(razlog);

    this.helperRejectedMail = new HelperRejectedMail(korisnik.jbo.toString(), korisnik.email, razlog);
    const body = JSON.stringify(this.helperRejectedMail);
    // post ne radi dobro
    return this.http.post('/server/korisnik/rejected', body, httpOptions);
  }

  getNeregistrovaneKorisnike() {
    return this.http.get('/server/korisnik/neregistrovani');
  }
}
