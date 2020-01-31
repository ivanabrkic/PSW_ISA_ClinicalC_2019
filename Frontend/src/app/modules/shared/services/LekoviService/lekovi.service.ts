import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Lekovi} from '../../../../models/Lekovi/lekovi';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class LekoviService {

  constructor(private http: HttpClient) { }

  public save(lek: Lekovi) {
    const novLek = JSON.stringify(lek);
    console.log(novLek);
    return this.http.post<Lekovi>('/server/lekovi/add' , novLek, httpOptions);
  }

  public get() {
    return this.http.get('/server/lekovi/getAll', httpOptions);
  }
}
