import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AdminKc} from '../../models/adminkc/admin-kc';
import {Observable} from 'rxjs';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class AdminKcService {

  constructor(private http: HttpClient) { }

  public update(adminKlinickogCentra: AdminKc) {
    const admin = JSON.stringify(adminKlinickogCentra);
    return this.http.post<AdminKc>('/server/administrator_kc/update' , admin, httpOptions);
  }

  public getUlogovanKorisnik(): Observable<AdminKc> {
    return this.http.get<AdminKc>('/server/korisnik/ulogovanKorisnik', httpOptions);
  }
}
