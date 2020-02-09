import {ZdravstveniKarton} from '../zdravstvenik/zdravstveniKarton';
import {Recept} from '../Recept/recept';
import {Lekar} from '../lekar/lekar';

export class Izvestaj {
  id: number;
  izvestaj: string;
  zdravstveniKarton: ZdravstveniKarton;
  lekar: Lekar;
  datum: String;
  recept: Recept;
}
