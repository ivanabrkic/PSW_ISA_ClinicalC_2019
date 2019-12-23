import { Component, OnInit } from '@angular/core';
import { LoginService } from './services/login-and-register-service/login.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{

  private loginServiceApp: LoginService;

  constructor(public loginService: LoginService) { this.loginServiceApp = loginService;}

  title = 'Klinicki centar';

  IsAuthenticated(): boolean {
    return this.loginServiceApp.isLoggedIn();
  }

  getRole(): string {
    return this.loginServiceApp.getRole();
  }

}
