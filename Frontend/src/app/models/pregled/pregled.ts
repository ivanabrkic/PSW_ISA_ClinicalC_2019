import { Pacijent } from '../pacijent/pacijent';

import { Lekar } from '../lekar/lekar';

import { Sala } from '../sala/sala';

export class Pregled{
    id:number;
    pacijent:Pacijent;
    lekar:Lekar;
    sala:Sala;
    pocetak:String;
    kraj:String;
    datum:String;
    departman:String;
}