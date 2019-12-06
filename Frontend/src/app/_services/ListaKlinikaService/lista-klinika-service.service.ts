import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Klinika} from '../../models/klinika/klinika';

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


}
