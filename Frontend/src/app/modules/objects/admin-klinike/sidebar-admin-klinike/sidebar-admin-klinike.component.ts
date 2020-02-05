import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login-and-register-service/login.service';

@Component({
  selector: 'sidebar-admin-klinike',
  templateUrl: './sidebar-admin-klinike.component.html',
  styleUrls: ['./sidebar-admin-klinike.component.css']
})
export class SidebarAdminKlinikeComponent implements OnInit {

  constructor(private loginService:LoginService) { }

  ngOnInit() {
  }

  odjava(){
    this.loginService.odjava().subscribe(data => {
      alert("Uspešno ste se odjavili!")
    });
}

}
