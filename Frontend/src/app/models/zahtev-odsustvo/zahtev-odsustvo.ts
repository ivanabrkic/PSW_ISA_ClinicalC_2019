import {Lekar} from '../lekar/lekar';
import {MedicinskoOsoblje} from "../medicinsko-osoblje/medicinsko-osoblje";

export class ZahtevOdsustvo {
  id: number;
  datumOd: string;
  datumDo: string;
  brojDana: number;
  opis: string;
  korisnikIme: string;
  korisnikPrezime: string;
  korisnikJbo: string;
  korisnikUloga: string
  overen: boolean;

  razlog : String
  klinikaId : number

  constructor(datumOd: string, datumDo: string, brojDana: number, opis: string, korisnikIme: string, korisnikPrezime: string, korisnikJbo: string, korisnikUloga: string, overen: boolean, klinikaId:number) {
    this.datumOd = datumOd;
    this.datumDo = datumDo;
    this.brojDana = brojDana;
    this.opis = opis;
    this.korisnikIme = korisnikIme;
    this.korisnikPrezime = korisnikPrezime;
    this.korisnikJbo = korisnikJbo;
    this.korisnikUloga = korisnikUloga;
    this.overen = overen;
    this.klinikaId = klinikaId
  }
}
