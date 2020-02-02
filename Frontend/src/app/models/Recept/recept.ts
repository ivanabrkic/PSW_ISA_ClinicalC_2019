import {Pacijent} from '../pacijent/pacijent';
import {Lekovi} from '../Lekovi/lekovi';

export class Recept {
  id: number;
  broj: number;
  overen: boolean;
  pacijent: Pacijent;
  lekovi: Lekovi[];
}
