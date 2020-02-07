import {Lekar} from '../lekar/lekar';
import {MedicinskoOsoblje} from "../medicinsko-osoblje/medicinsko-osoblje";

export class ZahtevOdsustvo {
  id: number;
  datumOd: string;
  datumDo: string;
  brojDana: number;
  opis: string;
  lekar: MedicinskoOsoblje;
  overen: boolean;


  constructor(datumOd: string, datumDo: string, brojDana: number, opis: string, lekar: MedicinskoOsoblje, overen: boolean) {
    this.datumOd = datumOd;
    this.datumDo = datumDo;
    this.brojDana = brojDana;
    this.opis = opis;
    this.lekar = lekar;
    this.overen = overen;
  }
}
