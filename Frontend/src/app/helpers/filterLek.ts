import {Pipe, PipeTransform} from '@angular/core';
import {Lekovi} from '../models/Lekovi/lekovi';

@Pipe({
  name: 'pretragaFilterLek'
})

export class PretragaFilterLek implements PipeTransform{
  transform(lekovi: Lekovi[],  searchNaziv: string, searchSifra: string) {
    if ( lekovi && lekovi.length) {
      return lekovi.filter(item => {
        if (searchNaziv && item.naziv.toLowerCase().indexOf(searchNaziv.toLowerCase()) === -1) {
          return false;
        }

        if (searchSifra && item.sifra.toLowerCase().indexOf(searchSifra.toLowerCase()) === -1) {
          return false;
        }

        return true;
      });
    } else {
      return lekovi;
    }
  }
}
