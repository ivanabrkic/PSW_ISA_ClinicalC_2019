import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Pregled} from '../../models/pregled/pregled';
import {Lekar} from '../../models/lekar/lekar';
import {PregledIzvestajDTO} from '../../helpers/pregled-izvestaj-dto';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class PregledService {

  constructor(private http: HttpClient) { }

  getPreglede(jboLekara: number) {
    const jbo = JSON.stringify(jboLekara);
    return this.http.post('/server/pregled/allPregledeByLekar', jbo, httpOptions);
  }

  zavrsenPregled(p: PregledIzvestajDTO) {
    const pregled = JSON.stringify(p);
    console.log(pregled);
    return this.http.post('/server/pregled/zavrsenPregled', pregled, httpOptions);
  }
}
