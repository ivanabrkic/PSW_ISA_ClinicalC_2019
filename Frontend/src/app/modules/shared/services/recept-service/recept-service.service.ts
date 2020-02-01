import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Recept} from "../../../../models/Recept/recept";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
     'Accept': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})

export class ReceptServiceService {

  constructor(private http: HttpClient) { }

  getNeoverene() {
    return this.http.get('/server/recept/getNeoverene');
  }

  overi(r: Recept) {
    const body = JSON.stringify(r);
    return this.http.post('/server/recept/updateOveren', body, httpOptions);
  }

  Obrisi(r: Recept) {
    const body = JSON.stringify(r);
    console.log(body);
    return this.http.post('/server/recept/neoveren', body, httpOptions);
  }
}
