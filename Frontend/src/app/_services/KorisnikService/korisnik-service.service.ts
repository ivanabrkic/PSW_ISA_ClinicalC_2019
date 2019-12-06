import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Korisnik} from '../../models/korisnik';



const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})

export class KorisnikServiceService {

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
}
