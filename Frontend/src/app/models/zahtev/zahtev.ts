export class Zahtev{
    id:number;

    idStavke:number
    stavkaCenovnika:String

    tipPosete:String;

    tipPosiljaoca:String;
    posiljalacJbo:String;
    posiljalacImePrezime:String;

    jboPacijenta:String;
    jboLekara:String;

    dodatneInformacije:String;

    idKlinike:number;

    pocetak:String;
    kraj:String;
    datum:String;

    zauzet:boolean;

    public Zahtev(){
    }
}