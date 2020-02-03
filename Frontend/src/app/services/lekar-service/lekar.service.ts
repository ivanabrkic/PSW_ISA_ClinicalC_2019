import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Lekar } from 'src/app/models/lekar/lekar';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { pretragaDTO } from 'src/app/models/pretragaDTO/pretragaDTO';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class LekarService {

  constructor(private http: HttpClient) {
  }

  public getUlogovanKorisnik(): Observable<Lekar> {
    return this.http.get<Lekar>('/server/korisnik/ulogovanKorisnik', httpOptions);
  }

  public getLekari(): Observable<Lekar[]> {
    return this.http.get<Lekar[]>('/server/lekar/all', httpOptions);
  }

  public getLekariKlinike(id : number): Observable<Lekar[]> {
    return this.http.post<Lekar[]>('/server/klinika/getLekari', id, httpOptions);
  }

  public getSlobodni(zahtev : Zahtev): Observable<Lekar[]> {
    return this.http.post<Lekar[]>('/server/lekar/getSlobodniLekari', JSON.stringify(zahtev), httpOptions);
  }

  public getSlobodniLekariTermini(zahtev : pretragaDTO): Observable<Map<String,Lekar>> {
    const pretraga = JSON.stringify(zahtev);
    return this.http.post<Map<String,Lekar>>('/server/lekar/getSlobodniLekariTermini', pretraga, httpOptions);
  }

  public update(lekar: Lekar) {
    const lek = JSON.stringify(lekar);
    return this.http.post<Lekar>('/server/lekar/update' , lek, httpOptions);
  }

  public register(lekar: Lekar) {
    const lek = JSON.stringify(lekar);
    return this.http.post<Lekar>('/server/lekar/register' , lek, httpOptions);
  }

  public remove(id: number) {
    return this.http.post<Lekar>('/server/lekar/remove' , id, httpOptions);
  }

  public lekarSlobodan(zahtev : Zahtev) {
    return this.http.post<boolean>('/server/lekar/lekarSlobodan', JSON.stringify(zahtev), httpOptions);
  }
}