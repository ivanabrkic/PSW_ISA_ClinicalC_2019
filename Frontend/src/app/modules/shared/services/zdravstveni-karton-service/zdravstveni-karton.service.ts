import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from 'rxjs';
import { Injectable } from '@angular/core';

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


}
