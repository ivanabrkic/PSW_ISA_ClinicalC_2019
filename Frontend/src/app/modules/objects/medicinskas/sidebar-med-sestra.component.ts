import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login-and-register-service/login.service';

@Component({
  selector: 'app-sidebar-med-sestra',
  templateUrl: './sidebar-med-sestra.component.html',
  styleUrls: ['./sidebar-med-sestra.component.css']
})
export class SidebarMedSestraComponent implements OnInit {

  constructor(private loginService:LoginService) { }

  ngOnInit() {
  }

  odjava(){
      this.loginService.odjava().subscribe(data => {
        alert('Uspešno ste se odjavili!')
      });
  }

}
