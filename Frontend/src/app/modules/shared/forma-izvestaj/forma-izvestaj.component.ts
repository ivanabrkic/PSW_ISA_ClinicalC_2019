import { Component, OnInit } from '@angular/core';
import {SessionService} from '../services/SessionService/session.service';
import {Pacijent} from '../../../models/pacijent/pacijent';

@Component({
  selector: 'app-forma-izvestaj',
  templateUrl: './forma-izvestaj.component.html',
  styleUrls: ['./forma-izvestaj.component.css']
})
export class FormaIzvestajComponent implements OnInit {
  pacijentZaPregled: Pacijent
  constructor(private sessionService: SessionService) {
    this.pacijentZaPregled = this.sessionService.pacijentZaPregled;
  }

  ngOnInit() {
  }

}
