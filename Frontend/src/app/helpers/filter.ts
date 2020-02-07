import {Pipe, PipeTransform} from '@angular/core';
import { Klinika } from '../models/klinika/klinika';

@Pipe({
    name: 'pretragafilterFilter'
  })

  export class PretragafilterFilter implements PipeTransform{
    transform(klinike: Klinika[],  searchDrzava: string, searchGrad: string, searchOcena:string, searchText:string) {
        if( klinike && klinike.length){
            return klinike.filter(item=>{
              var searchOcena2=searchOcena.replace('+','');
              var searchOcenaNum=Number(searchOcena2);


              if (searchDrzava && item.drzava.toLowerCase().indexOf(searchDrzava.toLowerCase())===-1){
                return false;
              }
              if (searchGrad && item.grad.toLowerCase().indexOf(searchGrad.toLowerCase())===-1){
                return false;
              }
              if (searchText && item.naziv.toLowerCase().indexOf(searchText.toLowerCase())===-1){
                return false;
              }
              if (searchOcena!="Ocena"&& searchOcena!="0" && (item.ocena<searchOcenaNum)){
                console.log(searchOcenaNum);
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
