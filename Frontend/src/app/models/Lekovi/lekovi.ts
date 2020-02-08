export class Lekovi {
  id: number;
  sifra: string;
  naziv: string;

  constructor(id, novaSifra, novNaziv) {
    this.id = id;
    this.sifra = novaSifra;
    this.naziv = novNaziv;
  }
}
