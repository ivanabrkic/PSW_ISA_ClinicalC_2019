import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login-and-register-service/login.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-sidebar-adminkc',
  templateUrl: './sidebar-adminkc.component.html',
  styleUrls: ['./sidebar-adminkc.component.css']
})
export class SidebarAdminkcComponent implements OnInit {

  constructor(private _snackBar: MatSnackBar,private loginService: LoginService) { }

  ngOnInit() {
  }

  odjava(){
    this.loginService.odjava().subscribe(data => {
      alert("Uspešno ste se odjavili!")
    });
}

}
