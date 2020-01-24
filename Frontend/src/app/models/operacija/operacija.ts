import { Pacijent } from '../pacijent/pacijent';

import { Lekar } from '../lekar/lekar';

import { Sala } from '../sala/sala';

export class Operacija{
    id:number;
    pacijent:Pacijent;
    lekar:Lekar[];
    sala:Sala;
    pocetak:String;
    kraj:String;
    datum:String;
    departman:String;
}