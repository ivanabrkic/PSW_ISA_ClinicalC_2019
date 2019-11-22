import { Component, OnInit } from '@angular/core';
import {AdministratorKlinike} from '../../../../models/AdministratorKlinike/administrator-klinike';
import {ActivatedRoute, Router} from '@angular/router';
import {AdminKlinikeServiceService} from '../../../../_services/AdministratorKlinikeService/admin-klinike-service.service';

@Component({
  selector: 'app-registracija-administratora-klinike',
  templateUrl: './registracija-administratora-klinike.component.html',
  styleUrls: ['./registracija-administratora-klinike.component.css']
})
export class RegistracijaAdministratoraKlinikeComponent implements OnInit {

  adminKlinike: AdministratorKlinike;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private AdminService: AdminKlinikeServiceService
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
