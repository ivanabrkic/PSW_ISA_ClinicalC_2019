import { Klinika } from '../klinika/klinika';
import { Operacija } from '../operacija/operacija';
import { Pregled } from '../pregled/pregled';

export class Sala{
    id:number;
    operacije: Operacija[];
    pregledi: Pregled[];
    klinika: Klinika;
    
}