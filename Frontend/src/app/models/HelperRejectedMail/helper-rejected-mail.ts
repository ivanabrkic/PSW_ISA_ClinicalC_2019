export class HelperRejectedMail {
  // tslint:disable-next-line:variable-name
  private email: string;
  // tslint:disable-next-line:variable-name
  private jbo: string;
  // tslint:disable-next-line:variable-name
  private poruka: string;
  constructor(jbo: string, email: string, poruka: string) {
    this.jbo = jbo;
    this.email = email;
    this.poruka = poruka;
  }

  get getJbo(): string {
    return this.jbo;
  }

  set setJbo(value: string) {
    this.jbo = value;
  }

  get getEmail(): string {
    return this.email;
  }

  set setEmail(value: string) {
    this.email = value;
  }

  get getPoruka(): string {
    return this.poruka;
  }

  set setPoruka(value: string) {
    this.poruka = value;
  }
}
