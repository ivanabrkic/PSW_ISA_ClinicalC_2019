import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Lekar } from 'src/app/models/lekar/lekar';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { pretragaDTO } from 'src/app/models/pretragaDTO/pretragaDTO';
import { Message } from 'src/app/models/message/message';
import { Pacijent } from 'src/app/models/pacijent/pacijent';
import { Termin } from 'src/app/models/termin/termin';
import { TipPregleda } from 'src/app/models/tippregleda/tippregleda';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class LekarService {

  constructor(private http: HttpClient) {
  }

  public getUlogovanKorisnik(): Observable<Lekar> {
    return this.http.get<Lekar>('/server/korisnik/ulogovanKorisnik', httpOptions);
  }

  public getLekari(): Observable<Lekar[]> {
    return this.http.get<Lekar[]>('/server/lekar/all', httpOptions);
  }

  public getLekariKlinike(id : number): Observable<Lekar[]> {
    return this.http.post<Lekar[]>('/server/klinika/getLekari', id, httpOptions);
  }

  public getSlobodniLekariTermini(zahtev : pretragaDTO): Observable<Map<String,Lekar>> {
    const pretraga = JSON.stringify(zahtev);
    return this.http.post<Map<String,Lekar>>('/server/lekar/getSlobodniLekariTermini', pretraga, httpOptions);
  }

  public update(lekar: Lekar) {
    const lek = JSON.stringify(lekar);
    return this.http.post<Lekar>('/server/lekar/update' , lek, httpOptions);
  }

  public register(lekar: Lekar) {
    const lek = JSON.stringify(lekar);
    return this.http.post<Message>('/server/lekar/register' , lek, httpOptions);
  }

  public remove(id: number) {
    return this.http.post<Message>('/server/lekar/remove' , id, httpOptions);
  }

  public lekarSlobodan(zahtev : Zahtev) {
    return this.http.post<boolean>('/server/lekar/lekarSlobodan', JSON.stringify(zahtev), httpOptions);
  }

  public findLekarByJbo(jbo : String){
    return this.http.post<Lekar>('/server/lekar/findLekarByJbo', jbo, httpOptions);
  }

  public getBySpecAndKlinika(lekar : Lekar){
    return this.http.post<TipPregleda[]>('/server/lekar/getBySpecAndKlinika', JSON.stringify(lekar), httpOptions);
  }

  public getTerminiLekar(lekar : Lekar, pacijent : Pacijent, trajanje : number){
    const object = {"idLekara" : lekar.id,
                    "idPacijenta" : pacijent.id,
                    "trajanje" : trajanje}
    return this.http.post<Termin[]>('/server/lekar/getTerminiLekar', object, httpOptions);
  }

  public posaljiZahtevAdminu(zahtev : Zahtev){
    return this.http.post<Message>('/server/lekar/posaljiZahtevAdminu', JSON.stringify(zahtev), httpOptions);
  }
}