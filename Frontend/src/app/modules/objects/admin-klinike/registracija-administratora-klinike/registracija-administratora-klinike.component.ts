import { Component, OnInit } from '@angular/core';
import {AdministratorKlinike} from '../../../../models/admink/administrator-klinike';
import {ActivatedRoute, Router} from '@angular/router';
import { AdminKlinikeService } from 'src/app/_services/admin-klinike-service/admin-klinike.service';

@Component({
  selector: 'app-registracija-administratora-klinike',
  templateUrl: './registracija-administratora-klinike.component.html',
  styleUrls: ['./registracija-administratora-klinike.component.css']
})
export class RegistracijaAdministratoraKlinikeComponent implements OnInit {

  adminKlinike: AdministratorKlinike;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private AdminService: AdminKlinikeService
  ) {
    this.adminKlinike = new AdministratorKlinike();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.AdminService.save(this.adminKlinike).subscribe(result => this.gotoUserList());
    console.log(this.adminKlinike.korIme);
  }

  gotoUserList() {
    this.router.navigate(['/registracijaAdminKlinike']);
  }

}
