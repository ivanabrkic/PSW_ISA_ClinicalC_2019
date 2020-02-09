export class Lekovi {
  id: number;
  sifra: string;
  naziv: string;

  constructor(novaSifra, novNaziv) {
    this.sifra = novaSifra;
    this.naziv = novNaziv;
  }
}
