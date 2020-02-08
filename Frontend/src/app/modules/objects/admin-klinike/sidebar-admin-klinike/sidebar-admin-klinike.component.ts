import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login-and-register-service/login.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'sidebar-admin-klinike',
  templateUrl: './sidebar-admin-klinike.component.html',
  styleUrls: ['./sidebar-admin-klinike.component.css']
})
export class SidebarAdminKlinikeComponent implements OnInit {

  constructor(private _snackBar: MatSnackBar, private loginService:LoginService) { }

  ngOnInit() {
  }

  odjava(){
    this.loginService.odjava().subscribe(data => {
      this._snackBar.open("Uspešno ste se odjavili!", "",  {
        duration: 3000
      });
    });
}

}
