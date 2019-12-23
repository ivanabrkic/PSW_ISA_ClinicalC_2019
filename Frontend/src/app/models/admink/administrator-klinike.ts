import { Klinika } from '../klinika/klinika';
import { Korisnik } from '../korisnik/korisnik';

export class AdministratorKlinike extends Korisnik {
  klinika: Klinika;


  get getKlinika(): Klinika {
    return this.klinika;
  }

  set setKlinika(value: Klinika) {
    this.klinika = value;
  }
}
