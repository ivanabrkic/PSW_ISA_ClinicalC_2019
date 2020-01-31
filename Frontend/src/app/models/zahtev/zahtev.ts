export class Zahtev{
    id:number;

    idStavke:number
    stavkaCenovnika:String

    tipPosete:String;

    tipPosiljaoca:String;
    posiljalacJbo:String;
    posiljalacImePrezime:String;

    jboPacijenta:String;

    dodatneInformacije:String;

    idKlinike:number;

    pocetak:String;
    kraj:String;
    datum:String;

    dan:number;
    mesec:number;
    godina:number;
    minut1:number;
    sat1:number;
    sat2:number;
    minut2:number;

    zauzet:boolean;

    public Zahtev(){
    }
}