import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AdministratorKlinike} from '../../models/AdministratorKlinike/administrator-klinike';

@Injectable({
  providedIn: 'root'
})
export class AdminKlinikeServiceService {
  private adminKlinikeUrl: string;
  constructor(private http: HttpClient) {
    this.adminKlinikeUrl = 'http://localhost:4200/registracijaAdminKlinike';
  }

  public findAll(): Observable<AdministratorKlinike[]> {
    return this.http.get<AdministratorKlinike[]>(this.adminKlinikeUrl);
  }

  public save(adminKlinike: AdministratorKlinike) {
    return this.http.post<AdministratorKlinike>(this.adminKlinikeUrl , adminKlinike);
  }
}
