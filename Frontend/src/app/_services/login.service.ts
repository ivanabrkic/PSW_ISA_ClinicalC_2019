import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import {Korisnik} from 'src/app/models';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient) {}

  login(user: Korisnik) {
    const user1 = JSON.stringify(user);
    return this.http.post(`/server/login/loginSubmit`, user1);
  }
}
