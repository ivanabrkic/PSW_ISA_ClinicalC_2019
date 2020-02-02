import { Injectable } from '@angular/core';
import {Pacijent} from '../../../../models/pacijent/pacijent';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  pacijentZaPregled: Pacijent;
  constructor() { }
}
