import { TipPregleda } from '../tippregleda/tippregleda';
import { Pacijent } from '../pacijent/pacijent';
import { Lekar } from '../lekar/lekar';
import { Sala } from '../sala/sala';

export class PregledId {

    pacijent:Pacijent;
    lekar:Lekar;
    sala:Sala;
    cenovnik : TipPregleda;

    pocetak:String;
    kraj:String;
    datum:String;

    popust:number;
    status:String;

    public PregledId(){
    }
}