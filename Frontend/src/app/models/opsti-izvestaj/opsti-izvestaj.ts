import {Dijagnoza} from '../Dijagnoza/dijagnoza';

export class OpstiIzvestaj {
  dioptrija: string;
  visina: string;
  tezina: string;
  krvnaGrupa: string;
  alergijeNaLek: string;
  // tslint:disable-next-line:variable-name
  nasledne_bolesti: Dijagnoza[];
}
