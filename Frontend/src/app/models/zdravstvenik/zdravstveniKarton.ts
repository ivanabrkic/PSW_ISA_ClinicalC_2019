import {OpstiIzvestaj} from '../opsti-izvestaj/opsti-izvestaj';
import {Dijagnoza} from '../Dijagnoza/dijagnoza';

export class ZdravstveniKarton {
  dijagnoze: Dijagnoza[] = [];
  opstiIzvestaj: OpstiIzvestaj;
  // tslint:disable-next-line:variable-name
  broj_sk: number;
}
