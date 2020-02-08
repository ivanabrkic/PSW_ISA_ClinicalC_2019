import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Injectable } from '@angular/core';
import {Pacijent} from '../../models/pacijent/pacijent';
import {ZdravstveniKarton} from '../../models/zdravstvenik/zdravstveniKarton';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})

export class ZdravstveniKartonService {
  constructor(private http: HttpClient) {

  }

  public get(id:number): Observable<String[]> {
    let params=new HttpParams().set("id",id.toString());
    let httpOptions2 = {params: params,headers: new HttpHeaders({'Content-Type' : 'application/json'})};
    return this.http.get<String[]>('/server/dijagnoze/get', httpOptions2);
  }

  public getPacijentovZk(pacijent: Pacijent): Observable<ZdravstveniKarton> {
    const body = JSON.stringify(pacijent);
    return this.http.post<ZdravstveniKarton>('/server/zk/getPacijentovZk', body, httpOptions);
  }


  public updateDijagnoze(zk: ZdravstveniKarton) {
    const body = JSON.stringify(zk);
    return this.http.post('/server/zk/updateDijagnoze', body, httpOptions);
  }

  public update(zk: ZdravstveniKarton) {
    const body = JSON.stringify(zk);
    return this.http.post('/server/zk/update', body, httpOptions);
  }
}
