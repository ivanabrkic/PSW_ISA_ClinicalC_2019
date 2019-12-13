import { Korisnik } from '../korisnik';
import { Klinika } from '../klinika/klinika';

<<<<<<< HEAD
export class AdministratorKlinike extends Korisnik {
  private klinika: string;


  get getKlinika(): string {
    return this.klinika;
  }

  set setKlinika(value: string) {
    this.klinika = value;
  }
=======
export class AdministratorKlinike extends Korisnik{
    klinika:Klinika;
>>>>>>> develop
}
