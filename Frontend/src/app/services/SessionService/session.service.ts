import { Injectable } from '@angular/core';
import {Pacijent} from '../../models/pacijent/pacijent';
import {Lekar} from '../../models/lekar/lekar';
import {PregledIzvestajDTO} from '../../helpers/pregled-izvestaj-dto';
import {IzvestajDto} from '../../helpers/izvestaj-dto';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  pacijentZaPregled: Pacijent;
  pacijentProfil: Pacijent;
  lekarZaPregled: Lekar;
  datumZaPregled: string;
  pregled: PregledIzvestajDTO;
  izvestajZaIzmenu: IzvestajDto;
  constructor() { }
}
