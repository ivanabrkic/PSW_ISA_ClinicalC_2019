import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Korisnik} from 'src/app/models';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient) {}

  login(user: Korisnik) {
<<<<<<< HEAD
    const user1 = JSON.stringify(user);
    return this.http.post(`/server/login/loginSubmit`, user1);
=======
    const user1 = JSON.stringify(user)
    return this.http.post<Korisnik>(`/server/login/loginSubmit`,  user1, httpOptions);
>>>>>>> b828c1f87692579a75adc5b13ea6216189717ebd
  }
}
