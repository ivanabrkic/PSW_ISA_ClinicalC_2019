import {Pipe, PipeTransform} from '@angular/core';
import { Klinika } from '../models/klinika/klinika';
import { Lekar } from '../models/lekar/lekar';

@Pipe({
    name: 'pretragafilterLekari'
  })

  export class PretragafilterLekari implements PipeTransform{
    transform(lekari: Lekar[],  searchIme: string, searchPrezime: string, searchOcena:string, searchTip:string) {
        if( lekari && lekari.length){
            return lekari.filter(item=>{
              
              
              //var searchOcena2=searchOcena.replace('+','');
              var searchOcenaNum=Number(searchOcena);
              

              if (searchIme && item.ime.toLowerCase().indexOf(searchIme.toLowerCase())===-1){
                return false;
              }
              if (searchPrezime && item.prezime.toLowerCase().indexOf(searchPrezime.toLowerCase())===-1){
                return false;
              }
            //   if (searchTip && item.naziv.toLowerCase().indexOf(searchText.toLowerCase())===-1){
            //     return false;
            //   }
              if (searchOcena!="Ocena"&& searchOcena!="0" && (item.ocena<searchOcenaNum)){
                console.log(searchOcenaNum);
                return false;
              }

            return true;
            })   
        }
        else{
            return lekari;
        }
      }
  }