import { Injectable } from '@angular/core';
import {Pacijent} from '../../models/pacijent/pacijent';
import {Lekar} from '../../models/lekar/lekar';
import {PregledIzvestajDTO} from '../../models/pregledIzvestajDTO/pregled-izvestaj-dto';
import {IzvestajDto} from '../../models/izvestajDTO/izvestaj-dto';
import {ZdravstveniKarton} from '../../models/zdravstvenik/zdravstveniKarton';
import {OpstiIzvestaj} from '../../models/opsti-izvestaj/opsti-izvestaj';
import {Recept} from '../../models/Recept/recept';

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
  zkPregled: ZdravstveniKarton;
  opstiIzvestaj: OpstiIzvestaj;


  receptZaIzmenu: Recept;
  constructor() { }
}
