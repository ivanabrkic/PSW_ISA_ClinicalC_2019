import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Pregled} from '../../../../models/pregled/pregled';
import {Lekar} from '../../../../models/lekar/lekar';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class PregledService {

  constructor(private http: HttpClient) { }

  getPreglede() {
    return this.http.get('/server/pregled/allPreglede', httpOptions);
  }

  zavrsenPregled(p: Pregled) {
    const pregled = JSON.stringify(p);
    return this.http.post('/pregled/zavrsenPregled', pregled, httpOptions);
  }
}
