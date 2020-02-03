import {Pacijent} from '../pacijent/pacijent';
import {Lekovi} from '../Lekovi/lekovi';

export class Recept {
  id: number;
  overen: boolean;
  pacijent: Pacijent;
  lekovi: Lekovi[];
}
