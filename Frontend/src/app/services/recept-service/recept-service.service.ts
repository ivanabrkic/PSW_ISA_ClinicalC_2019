import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Recept} from '../../models/Recept/recept';
import {Izvestaj} from "../../models/izvestaj/izvestaj";

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

  nadjiRecept(idRecept: number) {
    const body = JSON.stringify(idRecept);
    return this.http.post('/server/recept/findById', body, httpOptions);
  }

  Obrisi(r: Recept) {
    const body = JSON.stringify(r);
    console.log(body);
    return this.http.post('/server/recept/neoveren', body, httpOptions);
  }

  update(r: Recept) {
    const body = JSON.stringify(r);
    console.log(body);
    return this.http.post('/server/recept/update', body, httpOptions);
  }

  save(r: Recept) {
    const body = JSON.stringify(r);
    console.log(body);
    return this.http.post('/server/recept/save', body, httpOptions);
  }

  findByIzvestaj(izvestajID: number) {
    const body = JSON.stringify(izvestajID);
    console.log(body);
    return this.http.post('/server/recept/findByIzvestaj', body, httpOptions);
  }
}
