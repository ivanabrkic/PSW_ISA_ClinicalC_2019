import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import { Pacijent } from 'src/app/models/pacijent/pacijent';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  constructor(private http: HttpClient) {}

  register(user: Pacijent) {
     const user1 = JSON.stringify(user);
     return this.http.post(`/server/register/registrationSubmit`, user1, httpOptions);
   }
}
