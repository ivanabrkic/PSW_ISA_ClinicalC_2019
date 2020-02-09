import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ZahtevOdsustvo} from "../../models/zahtev-odsustvo/zahtev-odsustvo";
import { Message } from 'src/app/models/message/message';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class ZahtevOdsustvoService {

  constructor(private http: HttpClient) { }

  posaljiZahtev(zo: ZahtevOdsustvo){
    const body = JSON.stringify(zo);
    return this.http.post('/server/zahtevOdsustvo/posaljiZahtev', body, httpOptions);
  }

  getAllByKlinikaId(klinikaId : number){
    return this.http.post<ZahtevOdsustvo[]>('/server/zahtevOdsustvo/getAllByKlinikaId', klinikaId, httpOptions);
  }

  odbijZahtevZaOdsustvo(zahtev : ZahtevOdsustvo){
    return this.http.post<Message>('/server/zahtevOdsustvo/odbijZahtevZaOdsustvo', JSON.stringify(zahtev), httpOptions);
  }

  prihvatiZahtevZaOdsustvo(zahtev : ZahtevOdsustvo){
    return this.http.post<Message>('/server/zahtevOdsustvo/prihvatiZahtevZaOdsustvo', JSON.stringify(zahtev), httpOptions);
  }

  checkValidnostZahteva(zahtev : ZahtevOdsustvo){
    return this.http.post<Message>('/server/zahtevOdsustvo/checkValidnostZahteva', JSON.stringify(zahtev), httpOptions);
  }
  
}
