import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {OpstiIzvestaj} from '../../../../models/opsti-izvestaj/opsti-izvestaj';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class OpstiIzvestajService {

  constructor(private http: HttpClient) { }

  public update(opstiIzvestaj: OpstiIzvestaj) {
    const izvestaj = JSON.stringify(opstiIzvestaj);
    return this.http.post<OpstiIzvestaj>('/server/opstiIzvestaj/update' , izvestaj, httpOptions);
  }
}
