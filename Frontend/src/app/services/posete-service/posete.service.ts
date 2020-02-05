import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Poseta } from 'src/app/models/poseta/poseta';
import { Pregled } from 'src/app/models/pregled/pregled';
import { Operacija } from 'src/app/models/operacija/operacija';
import { PosetaIdTipDTO } from 'src/app/models/PosetaIdTipDTO/PosetaIdTipDTO';
import { predefInfo } from 'src/app/models/predefInfoDTO/predefInfo';
import { posetaLekarKlinikaDTO } from 'src/app/models/posetaLekarKlinikaDTO/posetaLekarKlinikaDTO';

@Injectable({
  providedIn: 'root'
})

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

export class PoseteService {
  constructor(private http: HttpClient) {
  }

  // public findAll(): Observable<Poseta[]> {
  //   let httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

  //   return this.http.get<Poseta[]>('/server/poseta/all', httpOptions);
  // }

  // public findByPatientId(id:number): Observable<Poseta[]> {

  //   let params=new HttpParams().set("id",id.toString());
  //   let httpOptions2 = {params: params,headers: new HttpHeaders({'Content-Type' : 'application/json'})};
    
  //   return this.http.get<Poseta[]>('/server/poseta/pacijent_id', httpOptions2);
  // }

  public findPreglediByPatientId(id:number): Observable<Pregled[]> {
    
     return this.http.post<Pregled[]>('/server/pacijent/getPregledi', id,httpOptions);
   }
   public findOperacijeByPatientId(id:number): Observable<Operacija[]> {

 
    return this.http.post<Operacija[]>('/server/pacijent/getOperacije',id, httpOptions);
  }

  public getMoreInfo(dto:PosetaIdTipDTO):Observable<posetaLekarKlinikaDTO>{
    
    var d=JSON.stringify(dto)
  
    return this.http.post<posetaLekarKlinikaDTO>('/server/pacijent/viseInfo',d, httpOptions);
  }

}