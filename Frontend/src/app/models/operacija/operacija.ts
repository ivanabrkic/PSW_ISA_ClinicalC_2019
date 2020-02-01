import { Pacijent } from '../pacijent/pacijent';

import { Lekar } from '../lekar/lekar';

import { Sala } from '../sala/sala';
import { identifierModuleUrl } from '@angular/compiler';

export class Operacija{
    id:number;
    pacijent:String;
    lekari:Lekar[];
    pocetak:String;
    kraj:String;
    datum:String;
    departman:String;

    public Operacija(){
    }
}