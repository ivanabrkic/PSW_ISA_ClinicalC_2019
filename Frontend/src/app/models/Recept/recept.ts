import {Pacijent} from '../pacijent/pacijent';
import {Lekovi} from '../Lekovi/lekovi';
import {MedicinskaSestra} from '../medicinskas/medicinskas';

export class Recept {
  id: number;
  broj: number;
  overen: boolean;
  pacijent: Pacijent;
  lekovi: Array<Lekovi>;
  medicinskaSestra: MedicinskaSestra;
}
