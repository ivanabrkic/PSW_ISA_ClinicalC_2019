import { Korisnik } from '../korisnik';

export class AdministratorKlinike extends Korisnik {
  private klinika: string;


  get getKlinika(): string {
    return this.klinika;
  }

  set setKlinika(value: string) {
    this.klinika = value;
  }
}
