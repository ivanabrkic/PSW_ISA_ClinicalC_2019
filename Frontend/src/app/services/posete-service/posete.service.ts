import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Poseta } from 'src/app/models/poseta/poseta';
import { Pregled } from 'src/app/models/pregled/pregled';
import { Operacija } from 'src/app/models/operacija/operacija';
import { PosetaIdTipDTO } from 'src/app/models/PosetaIdTipDTO/PosetaIdTipDTO';
import { predefInfo } from 'src/app/models/predefInfoDTO/predefInfo';
import { posetaLekarKlinikaDTO } from 'src/app/models/posetaLekarKlinikaDTO/posetaLekarKlinikaDTO';


const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};


@Injectable({
  providedIn: 'root'
})

export class PoseteService {

  getOcenaKlinika(pacijentId:number, idKlinike:number) {
    let params=new HttpParams().set("pacijentId",pacijentId.toString()).set("idKlinike",idKlinike.toString())
    let httpOptions2 = {params: params,headers: new HttpHeaders({'Content-Type' : 'application/json'})};
    return  this.http.get<number>('/server/pacijent/getOcenaKlinike', httpOptions2);
  }

  getOcenaLekar(pacijentId:number, idLekara:number) {
    let params=new HttpParams().set("pacijentId",pacijentId.toString()).set("idLekara",idLekara.toString())
    let httpOptions2 = {params: params,headers: new HttpHeaders({'Content-Type' : 'application/json'})};
    return this.http.get<number>('/server/pacijent/getOcenaLekara', httpOptions2);
  }
  
  oceniLekara(currentRateLekar: number, jboLekara: String, id: number):Observable<String> {
    
    console.log(jboLekara)
    let params=new HttpParams().set("ocena",currentRateLekar.toString()).set("jboLekara",jboLekara.toString()).set("idPacijenta",id.toString())
    let httpOptions2 = {params: params,headers: new HttpHeaders({'Content-Type' : 'application/json'})};
    
    return this.http.get<String>('/server/pacijent/oceniLekara', httpOptions2);
    
  }
  oceniKliniku(currentRateKlinika: number, idKlinike: number, id: number) {

    let params=new HttpParams().set("ocena",currentRateKlinika.toString()).set("idKlinike",idKlinike.toString()).set("idPacijenta",id.toString());
    let httpOptions2 = {params: params,headers: new HttpHeaders({'Content-Type' : 'application/json'})};
    
    return this.http.get<String>('/server/pacijent/oceniKliniku', httpOptions2);
    
  }
  constructor(private http: HttpClient) {
  }


  public findPreglediByPatientId(id:number): Observable<Poseta[]> {
    
     return this.http.post<Poseta[]>('/server/pacijent/getPregledi', id,httpOptions);
   }
   public findOperacijeByPatientId(id:number): Observable<Poseta[]> {

 
    return this.http.post<Poseta[]>('/server/pacijent/getOperacije',id, httpOptions);
  }

}