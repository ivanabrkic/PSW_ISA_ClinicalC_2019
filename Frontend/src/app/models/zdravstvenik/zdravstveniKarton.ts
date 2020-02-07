import {OpstiIzvestaj} from '../opsti-izvestaj/opsti-izvestaj';
import {Dijagnoza} from '../Dijagnoza/dijagnoza';
import {Pacijent} from '../pacijent/pacijent';

export class ZdravstveniKarton {
  id: number;
  dijagnoze: Dijagnoza[] = [];
  opstiIzvestaj: OpstiIzvestaj;
  pacijent: Pacijent;
  // tslint:disable-next-line:variable-name
  broj_zk: number;
}
