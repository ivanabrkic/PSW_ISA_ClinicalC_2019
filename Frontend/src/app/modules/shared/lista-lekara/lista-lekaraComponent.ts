import {Component, OnInit} from '@angular/core';
import {Klinika} from '../../../models/klinika/klinika';
import {Observable, Subscription} from 'rxjs';
import { ListaKlinikaService } from 'src/app/services/lista-klinika-service/lista-klinika.service';
import { Lekar } from 'src/app/models/lekar/lekar';
import { LekarService } from 'src/app/services/lekar-service/lekar.service';
import { pretragaDTO } from 'src/app/models/pretragaDTO/pretragaDTO';
import { LekarPregledComponent } from '../../objects/lekar/lekarPregled.component';
import { PacijentService } from 'src/app/services/pacijent-service/pacijent.service';
import { PredefZahtev } from 'src/app/models/PredefZahtev/PredefZahtev';
import { predefInfo } from 'src/app/models/predefInfoDTO/predefInfo';
import { PredefTerminiServiceService } from 'src/app/services/predefTermini-service/predef-termini-service.service';

@Component({
  selector: 'app-lista-lekara',
  templateUrl: './lista-lekaraComponent.html',
  styleUrls: ['/lista-lekaraComponent.css']
})



export class ListaLekaraComponent implements OnInit {
  private klinika: Klinika;
  private lekari: Lekar[];  
  private terminiSakriveni:boolean=false;
  private lekaritermini:Map<String,Lekar>=new Map();
  private termini: String[];
  private datum :String;
  private zahtev: pretragaDTO;
  private izabraniLekar:Lekar;
  private dodatneInfo:string;
  private selectedTermin:string;

  constructor(private listaLekaraService: LekarService,private pacijentService: PacijentService,private predefTermService:PredefTerminiServiceService) {

  }

  getLekari() {
    // var pretraga=new pretragaDTO();
    // pretraga.start='00:01';
    // pretraga.finis='23:59';
    // pretraga.datum='30/1/2020.';
    // pretraga.specijalizacija='Kardiolog';
    // pretraga.idKlinike=this.klinika.id;
    this.listaLekaraService.getSlobodniLekariTermini(this.zahtev).subscribe(
      podaci => {this.lekaritermini = podaci; 
        //var distinctId=Object.values(this.lekaritermini).map(item=>item.id).filter((value,index,self)=>self.indexOf(value)===index);
        this.lekari=Object.values(this.lekaritermini).filter((thing,i,arr)=>arr.findIndex(t=>t.id===thing.id)===i);
   
        console.log(this.lekari);
        this.termini=Object.keys(this.lekaritermini);
        this.termini=Object.keys(this.lekaritermini).sort((n1,n2)=>{
          if(n1>n2){
            return 1;
          }
          if(n1<n2){
            return -1;
          }
          return 0;
        });
        
      },
      err => console.log('Nisu ucitani lekari'),
      () => console.log(this.lekaritermini)
      
    );
    
  }

  zakaziNavigate(event){
    
    //zahtev.dodatneInformacije=
    this.pacijentService.getUlogovanKorisnik().subscribe(
      podaci => {
        var zahtev=new PredefZahtev;
        // zahtev.datum=this.izabraniTermin.datum;
        // zahtev.pocetak=this.selectedTermin;
        // zahtev.kraj=this.izabraniTermin.kraj;
        // zahtev.idKlinike=this.izabraniTermin.idKlinike;
        // zahtev.jboPacijenta= podaci.jbo;
        // zahtev.posiljalacImePrezime=podaci.ime+" "+podaci.prezime;
        // zahtev.posiljalacJbo=podaci.jbo;
        // zahtev.tipPosete='Pregled';
        // zahtev.stavkaCenovnika=this.izabraniTermin.tipPregleda;
        // zahtev.dodatneInformacije=this.dodatneInfo;
        // zahtev.tipPosiljaoca='Pacijent';
        // zahtev.jboLekara=this.izabraniLekar.jbo;

        this.predefTermService.zakaziTermin(zahtev).subscribe( data=>{alert('Termin uspesno zakazan');})
       
     },
    );
  }

  onSelect(lekar){
    this.izabraniLekar=lekar;
  }
 

  ngOnInit() {
    //console.log(history.state)
    //console.log(history.state.datum);
    this.klinika=history.state.klinika;
    this.zahtev=history.state.zahtev;
    
    if(this.zahtev.datum==null)
      this.terminiSakriveni=true;
    else
      this.terminiSakriveni=false;
    //console.log(this.klinika);
    this.getLekari();

   
  }

 
}
