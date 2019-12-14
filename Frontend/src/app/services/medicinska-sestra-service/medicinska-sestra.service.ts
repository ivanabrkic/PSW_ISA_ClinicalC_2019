import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MedicinskaSestra } from 'src/app/models/medicinskas/medicinskas';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class MedicinskaSestraService {

  constructor(private http: HttpClient) {
  }

  public getUlogovanKorisnik(): Observable<MedicinskaSestra> {
    return this.http.get<MedicinskaSestra>('/server/korisnik/ulogovanKorisnik', httpOptions);
  }

  public getMedicinskeSestre(): Observable<MedicinskaSestra[]> {
    return this.http.get<MedicinskaSestra[]>('/server/medicinska_sestra/all', httpOptions);
  }

  public update(medSes: MedicinskaSestra) {
    const med = JSON.stringify(medSes);
    return this.http.post<MedicinskaSestra>('/server/medicinska_sestra/update' , med, httpOptions);
  }
}