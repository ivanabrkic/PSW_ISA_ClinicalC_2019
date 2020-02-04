import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Klinika} from '../../models/klinika/klinika';
import { pretragaDTO } from 'src/app/models/pretragaDTO/pretragaDTO';
import { Cenovnik } from 'src/app/models/Cenovnik/cenovnik';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})

export class ListaKlinikaService {
  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Klinika[]> {
    return this.http.get<Klinika[]>('/server/klinika/all', httpOptions);
  }

  public getSlobodneKlinike(zahtev:pretragaDTO){
    const  z= JSON.stringify(zahtev)
    return this.http.post<Klinika[]>('/server/klinika/slobodneKlinike', z, httpOptions);
  }

  public findTipovi():Observable<Cenovnik[]>{
    return this.http.get<Cenovnik[]>('/server/cenovnik/all', httpOptions);
  }

  public getCena(zahtev:pretragaDTO):Observable<number>{
    return this.http.get<number>('/server/cenovnik/getCena', httpOptions);
  }

}
