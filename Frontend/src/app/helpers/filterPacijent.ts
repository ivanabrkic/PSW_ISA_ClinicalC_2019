import {Pipe, PipeTransform} from '@angular/core';
import {Pacijent} from '../models/pacijent/pacijent';

@Pipe({
  name: 'pretragafilterPacijent'
})

export class PretragaFilterPacijent implements PipeTransform{
  transform(pacijenti: Pacijent[],  searchIme: string, searchPrezime: string, searchJbo: string) {
    if ( pacijenti && pacijenti.length) {
      return pacijenti.filter(item => {
        if (searchIme && item.ime.toLowerCase().indexOf(searchIme.toLowerCase()) === -1) {
          return false;
        }

        if (searchPrezime && item.prezime.toLowerCase().indexOf(searchPrezime.toLowerCase()) === -1) {
          return false;
        }

        if (searchJbo && item.jbo.toLowerCase().indexOf(searchJbo.toLowerCase()) === -1) {
          return false
        }

        return true;
      });
    } else {
      return pacijenti;
    }
  }
}
