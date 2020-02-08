export class PregledIzvestajDTO {
  id: number;
  jboPacijenta: string;
  datum: string;
  pocetak: string;
  kraj: string;
  jboLekara: string;
  imeLekara: string;
  prezimeLekara: string;


  constructor(id: number, jboPacijenta: string, datum: string, pocetak: string, kraj: string, jboLekara: string, imeLekara: string, prezimeLekara: string) {
    this.id = id;
    this.jboPacijenta = jboPacijenta;
    this.datum = datum;
    this.pocetak = pocetak;
    this.kraj = kraj;
    this.jboLekara = jboLekara;
    this.imeLekara = imeLekara;
    this.prezimeLekara = prezimeLekara;
  }
}
