import { Component, OnInit } from '@angular/core';
import {Klinika} from '../../../../models/klinika/klinika';
import {ActivatedRoute, Router} from '@angular/router';
import { KlinikaService } from 'src/app/services/klinika-service/klinika.service';

@Component({
  selector: 'app-registracija-klinike',
  templateUrl: './registracija-klinike.component.html',
  styleUrls: ['./registracija-klinike.component.css']
})
export class RegistracijaKlinikeComponent implements OnInit {
  klinika: Klinika;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private klinikaService: KlinikaService
  ) {
    this.klinika = new Klinika();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.klinikaService.save(this.klinika).subscribe(result => this.gotoUserList());
    console.log(this.klinika.naziv);
  }

  gotoUserList() {
    this.router.navigate(['/registracijaKlinike']);
  }

}
