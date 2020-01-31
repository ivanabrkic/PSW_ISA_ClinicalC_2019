import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/modules/shared/services/login-and-register-service/login.service';

@Component({
  selector: 'app-sidebar-adminkc',
  templateUrl: './sidebar-adminkc.component.html',
  styleUrls: ['./sidebar-adminkc.component.css']
})
export class SidebarAdminkcComponent implements OnInit {

  constructor(private loginService: LoginService) { }

  ngOnInit() {
  }

  odjava(){
    this.loginService.odjava().subscribe(data => {
      alert("Uspešno ste se odjavili!")
    });
}

}
