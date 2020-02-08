import {Pipe, PipeTransform} from '@angular/core';
import { Klinika } from '../models/klinika/klinika';
import { klinikaCena } from '../models/klinikaCena/klinikaCena';

@Pipe({
    name: 'pretragafilterFilter'
  })

  export class PretragafilterFilter implements PipeTransform{
    transform(klinike: klinikaCena[],  searchDrzava: string, searchGrad: string, searchOcena:string, searchText:string) {
        if( klinike && klinike.length){
            return klinike.filter(item=>{
              var searchOcenaNum=Number(searchOcena);

              if (searchDrzava && item.klinika.drzava.toLowerCase().indexOf(searchDrzava.toLowerCase())===-1){
                return false;
              }
              if (searchGrad && item.klinika.grad.toLowerCase().indexOf(searchGrad.toLowerCase())===-1){
                return false;
              }
              if (searchText && item.klinika.naziv.toLowerCase().indexOf(searchText.toLowerCase())===-1){
                return false;
              }
              if (searchOcena!="Ocena" && searchOcena!="0" && (item.klinika.ocena<searchOcenaNum)){
                return false;
              }

            return true;
            })
        }
        else{
            return klinike;
        }
      }
  }
