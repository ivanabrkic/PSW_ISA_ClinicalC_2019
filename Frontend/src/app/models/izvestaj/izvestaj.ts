import {ZdravstveniKarton} from '../zdravstvenik/zdravstveniKarton';
import {Recept} from '../Recept/recept';
import {Lekar} from '../lekar/lekar';

export class Izvestaj {
  izvestaj: string;
  zdravstveniKarton: ZdravstveniKarton;
  recept: Recept;
  lekar: Lekar;
  datum: string;
}
