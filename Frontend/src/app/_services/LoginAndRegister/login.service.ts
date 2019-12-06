import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Korisnik} from 'src/app/models/korisnik';
import {Router} from '@angular/router';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient, private router: Router) {}

  static authenticated = false;
  ulogovaniTip = '';
  isLoggedIn() {
    return LoginService.authenticated;
  }
  getRole() {
    return this.ulogovaniTip;
  }

  login(user: Korisnik) {
    const user1 = JSON.stringify(user);
    return this.http.post<Korisnik>(`/server/login/loginSubmit`,  user1, httpOptions);
  }
  odjava() {
    LoginService.authenticated = false;
    this.ulogovaniTip = '';
    this.router.navigate(['/welcome']);
    return this.http.post(`/server/login/logoutSubmit`,   httpOptions);
  }
}
