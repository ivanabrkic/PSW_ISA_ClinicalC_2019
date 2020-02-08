import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Klinika} from '../../models/Klinika/klinika';
import { Sala } from 'src/app/models/sala/sala';
import { Pregled } from 'src/app/models/pregled/pregled';
import { Operacija } from 'src/app/models/operacija/operacija';
import { Lekar } from 'src/app/models/lekar/lekar';
import { Zahtev } from 'src/app/models/zahtev/zahtev';
import { Message } from 'src/app/models/message/message';
import { TipPregleda } from 'src/app/models/tippregleda/tippregleda';
import { Termin } from 'src/app/models/termin/termin';
import { LekarTrajanje } from 'src/app/models/termin/lekartrajanje';
import { PregledId } from 'src/app/models/pregled/PregledId';
import { pretragaDTO } from 'src/app/models/pretragaDTO/pretragaDTO';
import {OperacijaKalendarDTO} from '../../models/OperacijaKalendarDTO/operacija-kalendar-dto';
import { Email } from 'src/app/models/email/email';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class KlinikaService {

  constructor(private http: HttpClient) {
  }

  ///////////////////////// KLINIKA ////////////////////////////////////////////////////////////////////////////
  public findByNaziv(): Observable<Klinika> {
    return this.http.get<Klinika>('/server/klinika/registracijaKlinike');
  }

  public save(klinika: Klinika) {
    const k = JSON.stringify(klinika)
    return this.http.post<Klinika>('/server/klinika/registracijaKlinike', k, httpOptions);
  }

  public getKlinike() {
    return this.http.get<Klinika>('/server/klinika/all', httpOptions);
  }

  public update(klinika: Klinika, id: number){
    klinika.id = id;
    const k = JSON.stringify(klinika)
    return this.http.post<Klinika>('/server/klinika/update', k, httpOptions);
  }
  ////////////////////////////////// SALA  ////////////////////////////////////////////////////////////////////
  public getSale(id : number): Observable<Sala[]> {
    return this.http.post<Sala[]>('/server/sala/getSale', id, httpOptions);
  }

  public removeSala(id: number) {
    return this.http.post<Message>('/server/sala/removeSala' , id, httpOptions);
  }

  public registerSala(sala: Sala) {
    const s = JSON.stringify(sala)
    return this.http.post<Message>('/server/sala/registerSala' , s, httpOptions);
  }

  public updateSala(sala: Sala) {
    const s = JSON.stringify(sala)
    return this.http.post<Message>('/server/sala/updateSala' , s, httpOptions);
  }
  /////////////////////////////////////// PREGLEDI / OPERACIJE //////////////////////////////////////////////////
  public getOperacije(sala: Sala) {
    return this.http.post<Operacija>('/server/klinika/getOperacije' , sala.id, httpOptions);
  }

  public getPregledi(sala: Sala) {
    return this.http.post<Pregled>('/server/klinika/getPregledi' , sala.id, httpOptions);
  }

  public getLekariOperacije(op : Operacija) {
    return this.http.post<Lekar[]>('/server/klinika/getLekariOperacije', JSON.stringify(op), httpOptions);
  }

  public getSlobodniLekari(zahtev : Zahtev) {
    return this.http.post<Lekar[]>('/server/klinika/getSlobodniLekari', JSON.stringify(zahtev), httpOptions);
  }

  public getSlobodneSale(zahtev : Zahtev): Observable<Sala[]> {
    return this.http.post<Sala[]>('/server/klinika/getSlobodneSale', JSON.stringify(zahtev), httpOptions);
  }

  getDrugiTermin(passZahtev: Zahtev) {
    return this.http.post<Sala[]>('/server/klinika/getDrugiTermin', JSON.stringify(passZahtev), httpOptions);
  }
  ///////////////////////////////////////// ZAKAZI OPERACIJU/PREGLED ///////////////////////////////////////////////
  public zakaziOperaciju(operacija : Operacija) {
    return this.http.post<Message>('/server/klinika/zakaziOperaciju', JSON.stringify(operacija), httpOptions);
  }

  public zakaziPregled(pregled : Pregled) {
    return this.http.post<Message>('/server/klinika/zakaziPregled', JSON.stringify(pregled), httpOptions);
  }
  ///////////////////////////////////////// ZAHTEVI //////////////////////////////////////////////////////////////////
  public getZahtevi(id : number) {
    return this.http.post<Zahtev[]>('/server/klinika/getZahtevi', id, httpOptions);
  }

  public removeZahtev(id : number) {
    return this.http.post<Boolean>('/server/klinika/removeZahtev', id, httpOptions);
  }

  public sendEmail(email : Email){
    return this.http.post<Message>('/server/klinika/obavestiMejlomZahtevPrihvacen', email, httpOptions)
  }
  ///////////////////////////////////////// TIPOVI PREGLEDA /////////////////////////////////////////////////////////
  public getTipovi(idKlinike : number) {
    return this.http.post<TipPregleda[]>('/server/cenovnik/getTipovi', idKlinike, httpOptions);
  }

  public removeTip(idTipa : number) {
    return this.http.post<Message>('/server/cenovnik/removeTip', idTipa, httpOptions);
  }

  public registerTip(tip : TipPregleda){
    return this.http.post<Message>('/server/cenovnik/registerTip', JSON.stringify(tip), httpOptions);
  }

  public updateTip(tip : TipPregleda){
    return this.http.post<Message>('/server/cenovnik/updateTip', JSON.stringify(tip), httpOptions);
  }
  ///////////////////////////////////PREDEFINISANI DODAVANJE/////////////////////////////////////////////
  public getLekariForTip(idTipa : number){
    return this.http.post<Lekar[]>('/server/klinika/getLekariForTip', idTipa, httpOptions);
  }

  public getTermini(lekarTrajanje : LekarTrajanje){
    return this.http.post<Termin[]>('/server/klinika/getTermini', JSON.stringify(lekarTrajanje), httpOptions);
  }

  public zakaziTermin(termin : PregledId){
    return this.http.post<Message>('/server/klinika/dodajTermin', JSON.stringify(termin), httpOptions);
  }
  //////////////////////////////////Nikolino/////////////////////////////////////////////////////////////

  public findOperacijeByLekar(lekar: Lekar): Observable<OperacijaKalendarDTO[]>{
    const body = JSON.stringify(lekar);
    return this.http.post<OperacijaKalendarDTO[]>('/server/klinika/findOperacijeByLekar', body, httpOptions);
  }

  public getZakazaneOperacije(): Observable<OperacijaKalendarDTO[]> {
    return this.http.get<OperacijaKalendarDTO[]>('/server/klinika/getZakazaneOperacije', httpOptions);
  }

}
