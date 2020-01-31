import {Component, OnInit} from '@angular/core';
import {Klinika} from '../../../models/klinika/klinika';
import {Observable, Subscription} from 'rxjs';
import { ListaKlinikaService } from 'src/app/services/lista-klinika-service/lista-klinika.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lista-klinika',
  templateUrl: './lista-klinikaComponent.html',
  styleUrls: ['/lista-klinikaComponent.css']
})



export class ListaKlinikaComponent implements OnInit {
  private klinike: Klinika[];
  selectedKlinika: Klinika;  

  ocene = [{
    name: '5',
    value: '5'
 }, {
  name: '4+',
  value: '4'
},
{
  name: '3+',
  value: '3'
},
{
  name: '2+',
  value: '2'
},
{
  name: '1+',
  value: '1'
},
{
  name: 'Ocena',
  value: '0'
}
];

  constructor(private listaKlinikaService: ListaKlinikaService,private router:Router) {
  }



  getKlinike() {
    this.listaKlinikaService.findAll().subscribe(
      podaci => {this.klinike = podaci; },
      err => console.log('Nisu ucitani korisnici'),
      () => console.log('Uspesno ucitani korisnici')
    );
  }

  onSelect(klinika: Klinika): void {
    this.selectedKlinika = klinika;
  }
  ngOnInit() {
    this.getKlinike();
  }

  lekariNavigate(event){
    let klinika=this.selectedKlinika;{
      this.router.navigate(['/listaLekara'],{state:{data:klinika}});
    }
  }

}
