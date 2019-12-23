import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Poseta } from 'src/app/models/poseta/poseta';

@Injectable({
  providedIn: 'root'
})

export class PoseteService {
  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Poseta[]> {
    let httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

    return this.http.get<Poseta[]>('/server/poseta/all', httpOptions);
  }

  public findByPatientId(id:number): Observable<Poseta[]> {

    let params=new HttpParams().set("id",id.toString());
    let httpOptions2 = {params: params,headers: new HttpHeaders({'Content-Type' : 'application/json'})};
    
    return this.http.get<Poseta[]>('/server/poseta/pacijent_id', httpOptions2);
  }


}