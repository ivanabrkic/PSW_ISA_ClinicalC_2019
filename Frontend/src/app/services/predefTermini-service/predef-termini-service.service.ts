import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { Pregled } from 'src/app/models/pregled/pregled';
import { predefInfo } from 'src/app/models/predefInfoDTO/predefInfo';

@Injectable({
  providedIn: 'root'
})
export class PredefTerminiServiceService {

  constructor(private http: HttpClient) { }

  public findAll(id:number): Observable<predefInfo[]> {
    let httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

    return this.http.post<predefInfo[]>('/server/klinika/getPreglediPredef', id,httpOptions);
  }

}
