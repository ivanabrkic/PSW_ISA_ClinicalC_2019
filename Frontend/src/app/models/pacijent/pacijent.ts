import { Korisnik } from '../korisnik/korisnik';
import {ZdravstveniKarton} from '../zdravstvenik/zdravstveniKarton';

export class Pacijent extends Korisnik {
    zdravstveniKarton: ZdravstveniKarton;
}
