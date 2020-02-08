export class OperacijaKalendarDTO {
  jboPacijenta: string;
  datum: string;
  pocetak: string;
  kraj: string;
  imePacijenta: string;
  prezimePacijenta: string;


  constructor(jboPacijenta: string, datum: string, pocetak: string, kraj: string, imePacijenta: string, prezimePacijenta: string) {
    this.jboPacijenta = jboPacijenta;
    this.datum = datum;
    this.pocetak = pocetak;
    this.kraj = kraj;
    this.imePacijenta = imePacijenta;
    this.prezimePacijenta = prezimePacijenta;
  }
}
