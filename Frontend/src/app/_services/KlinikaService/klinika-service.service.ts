import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Klinika} from '../../models/Klinika/klinika';

@Injectable({
  providedIn: 'root'
})
export class KlinikaServiceService {
  private klinikaUrl: string;

  constructor(private http: HttpClient) {
    this.klinikaUrl = 'http://localhost:4200/registracijaKlinike';
  }

  public findByNaziv(): Observable<Klinika>{
    return this.http.get<Klinika>(this.klinikaUrl);
  }

  public save(klinika: Klinika) {
    return this.http.post<Klinika>(this.klinikaUrl, klinika);
  }
}
