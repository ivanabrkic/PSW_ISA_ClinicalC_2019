import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import {Korisnik} from 'src/app/models';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient) {}

 login(user: Korisnik) {
    return this.http.post(`/loginSubmit`, user);
  }
}
