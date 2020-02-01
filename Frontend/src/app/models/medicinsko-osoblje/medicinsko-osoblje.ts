import { Klinika } from '../klinika/klinika';
import { Korisnik } from '../korisnik/korisnik';

export class MedicinskoOsoblje extends Korisnik{
    brSlobodnihDana : number;
    ocena : number;
    radnoVreme : string;
    klinika: Klinika;
    specijalizacija:String;
}