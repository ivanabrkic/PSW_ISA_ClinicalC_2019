import {Pacijent} from '../pacijent/pacijent';
import {Lekovi} from '../Lekovi/lekovi';
import {MedicinskaSestra} from '../medicinskas/medicinskas';
import {Izvestaj} from "../izvestaj/izvestaj";

export class Recept {
  id: number;
  overen: boolean;
  pacijent: Pacijent;
  izvestaj: Izvestaj;
  lekovi: Lekovi[];
  medicinskaSestra: MedicinskaSestra;
}
