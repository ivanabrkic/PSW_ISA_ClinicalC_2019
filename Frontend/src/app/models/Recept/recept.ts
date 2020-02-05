import {Pacijent} from '../pacijent/pacijent';
import {Lekovi} from '../Lekovi/lekovi';
import {MedicinskaSestra} from '../medicinskas/medicinskas';

export class Recept {
  id: number;
  overen: boolean;
  pacijent: Pacijent;
  lekovi: Lekovi[];
  medicinskaSestra: MedicinskaSestra;
}
