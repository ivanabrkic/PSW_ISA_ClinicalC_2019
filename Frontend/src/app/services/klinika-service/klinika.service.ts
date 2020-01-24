import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Klinika} from '../../models/klinika/klinika';
import { Sala } from 'src/app/models/sala/sala';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class KlinikaService {
  
  private klinikaUrl: string;

  constructor(private http: HttpClient) {
    this.klinikaUrl = '/server/klinika/registracijaKlinike';
  }

  public findByNaziv(): Observable<Klinika> {
    return this.http.get<Klinika>(this.klinikaUrl);
  }

  public save(klinika: Klinika) {
    const k = JSON.stringify(klinika)
    return this.http.post<Klinika>(this.klinikaUrl, k, httpOptions);
  }

  public getKlinike() {
    return this.http.get<Klinika>('/server/klinika/all', httpOptions);
  }

  public getSale(id : number): Observable<Sala[]> {
    return this.http.post<Sala[]>('/server/klinika/getSale', id, httpOptions);
  }

  public update(klinika: Klinika, id: number){
    klinika.id = id;
    const k = JSON.stringify(klinika)
    return this.http.post<Klinika>('/server/klinika/update', k, httpOptions);
  }

  public remove(id: number) {
    return this.http.post<Sala>('/server/klinika/remove' , id, httpOptions);
  }
}
