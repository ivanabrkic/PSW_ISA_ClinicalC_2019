import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ZahtevOdsustvo} from "../../models/zahtev-odsustvo/zahtev-odsustvo";

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
}
