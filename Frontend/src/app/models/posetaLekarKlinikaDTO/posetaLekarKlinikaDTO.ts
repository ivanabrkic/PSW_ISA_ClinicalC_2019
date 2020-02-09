import { Lekar } from '../lekar/lekar';

export class posetaLekarKlinikaDTO{
    id:number;
    tipPosete:String;
    lekar:Lekar;
    klinikaId:number;
    klinikaNaziv:String;
    klinikaOcena:number;
    lekarOcena:number;

    posetaLekarKlinikaDTO(){}
}