import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import {Korisnik} from 'src/app/models';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  constructor(private http: HttpClient) {}

  register(user: Korisnik) {
     const user1 = JSON.stringify(user)
     return this.http.post(`/server/register/registrationSubmit`, user1);
   }
}
