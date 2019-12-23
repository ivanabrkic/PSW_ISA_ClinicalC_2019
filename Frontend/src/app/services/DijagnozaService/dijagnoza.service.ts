import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Dijagnoza} from '../../models/Dijagnoza/dijagnoza';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class DijagnozaService {

  constructor(private http: HttpClient) { }

  public save(dijagnoza: Dijagnoza) {
    const novaDijagnoza = JSON.stringify(dijagnoza);
    return this.http.post<Dijagnoza>('/server/dijagnoze/add' , novaDijagnoza, httpOptions);
  }

  public get() {
    return this.http.get('/server/dijagnoze/getAll', httpOptions);
  }
}
