import { Klinika } from '../klinika/klinika';
import { Korisnik } from '../korisnik/korisnik';

export class MedicinskoOsoblje extends Korisnik{
    brSlobodnihDana : number;
    radnoVreme : string;
    ocena : number;
    specijalizacija: String;
    klinika: Klinika;
}
