import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from 'rxjs';
import { ZdravstveniKarton } from 'src/app/models/zdravstvenik/zdravstveniKarton';
import { Dijagnoza } from 'src/app/models/Dijagnoza/dijagnoza';

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
