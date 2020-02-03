import { Injectable } from '@angular/core';
import {Pacijent} from '../../../../models/pacijent/pacijent';
import {Lekar} from '../../../../models/lekar/lekar';
import {PregledIzvestajDTO} from '../../../../helpers/pregled-izvestaj-dto';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  pacijentZaPregled: Pacijent;
  lekarZaPregled: Lekar;
  datumZaPregled: string;
  pregled: PregledIzvestajDTO;
  constructor() { }
}
