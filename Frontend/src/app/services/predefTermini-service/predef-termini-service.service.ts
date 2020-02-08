import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { Pregled } from 'src/app/models/pregled/pregled';
import { predefInfo } from 'src/app/models/predefInfoDTO/predefInfo';
import { PredefZahtev } from 'src/app/models/PredefZahtev/PredefZahtev';
import { TamarinPregled } from 'src/app/models/TamarinPregled';
import { klinikaPacDTO } from 'src/app/models/klinikaPacDTO/klinikaPacDTO';

@Injectable({
  providedIn: 'root'
})
export class PredefTerminiServiceService {

  constructor(private http: HttpClient) { }

   public findAll(id:klinikaPacDTO): Observable<predefInfo[]> {
    let httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

    return this.http.post<predefInfo[]>('/server/klinika/getPreglediPredef', id,httpOptions);
  }

  public zakaziTermin(zahtev:PredefZahtev){
    let httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};
    const  z= JSON.stringify(zahtev)
    return this.http.post<Zahtev>('/server/klinika/zakaziTermin', z, httpOptions);
  }

  public zakaziPredefTermin(pregled:TamarinPregled) {
    let httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};
    const lek = JSON.stringify(pregled);
    return this.http.post<Pregled>('/server/klinika/zakaziPredefTerminn' , lek, httpOptions);
  }
}
