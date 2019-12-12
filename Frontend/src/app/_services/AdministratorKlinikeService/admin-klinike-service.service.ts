import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AdministratorKlinike} from '../../models/administrator-klinike';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class AdminKlinikeServiceService {

  constructor(private http: HttpClient) {
  }

  public getUlogovanKorisnik(): Observable<AdministratorKlinike> {
    return this.http.get<AdministratorKlinike>('/server/korisnik/ulogovanKorisnik', httpOptions);
  }

  public save(adminKlinike: AdministratorKlinike) {
    const admin = JSON.stringify(adminKlinike);
    return this.http.post<AdministratorKlinike>('/server/administrator_k/registrationSubmitAdmin' , admin, httpOptions);
  }

  public update(adminKlinike: AdministratorKlinike) {
    const admin = JSON.stringify(adminKlinike);
    return this.http.post<AdministratorKlinike>('/server/administrator_k/update' , admin, httpOptions);
  }
}
