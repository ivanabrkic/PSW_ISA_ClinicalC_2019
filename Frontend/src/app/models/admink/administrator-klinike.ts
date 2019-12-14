import { Korisnik } from '../korisnik';
import { Klinika } from '../klinika/klinika';

export class AdministratorKlinike extends Korisnik {
  klinika: Klinika;


  get getKlinika(): Klinika {
    return this.klinika;
  }

  set setKlinika(value: Klinika) {
    this.klinika = value;
  }
}
