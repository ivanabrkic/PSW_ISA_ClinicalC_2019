import { Pacijent } from '../pacijent/pacijent';

import { Lekar } from '../lekar/lekar';

import { Sala } from '../sala/sala';
import { identifierModuleUrl } from '@angular/compiler';

export class Operacija{
    id:number;
    pacijent:number;
    lekari:number[];
    sala:Sala;
    pocetak:String;
    kraj:String;
    datum:String;
    departman:String;
    dan:number;
    mesec:number;
    godina:number;
    minut1:number;
    sat1:number;
    sat2:number;
    minut2:number;

    public Operacija(){
    }
}