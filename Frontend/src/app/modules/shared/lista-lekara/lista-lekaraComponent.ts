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
import { FormControl, FormBuilder, FormGroup } from '@angular/forms';
import * as moment from 'moment';
import { MatDatepickerInputEvent } from '@angular/material';
import { Cenovnik } from 'src/app/models/Cenovnik/cenovnik';
import { DatePipe } from '@angular/common';

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

//   constructor(private listaLekaraService: LekarService) { VIDI OVO !!!!

//   }

//   getLekari() {
//     // var pretraga=new pretragaDTO();
//     // pretraga.start='00:01';
//     // pretraga.finis='23:59';
//     // pretraga.datum='30/1/2020.';
//     // pretraga.specijalizacija='Kardiolog';
//     // pretraga.idKlinike=this.klinika.id;
//     this.listaLekaraService.getSlobodniLekariTermini(this.zahtev).subscribe(
//       podaci => {this.lekaritermini = podaci; 
//         //var distinctId=Object.values(this.lekaritermini).map(item=>item.id).filter((value,index,self)=>self.indexOf(value)===index);
  private izabraniLekar:Lekar;
  private selectedTermin:string;
  private selectedDate:String;
  private date=new FormControl(new Date);
  private dodatneInfo:String="";
  private tipovi:Cenovnik[];
  private searchOcena: FormGroup;
  private izabraniTip:Cenovnik;


  constructor(private tp:DatePipe,private listaLekaraService: LekarService,private pacijentService: PacijentService,private predefTermService:PredefTerminiServiceService,private listaklSer:ListaKlinikaService) {

  }

  getLekari() {

    this.listaLekaraService.getSlobodniLekariTermini(this.zahtev).subscribe(
      podaci => {this.lekaritermini = podaci;

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

  addEvent(type:string,event:MatDatepickerInputEvent<Date>){
    var selectedD=this.date.value;
    this.selectedDate=moment(selectedD).format('D.M.YYYY.');
  }

  getSelectedTermin(id:number){
    let value = (<HTMLOptionElement>document.getElementById(String(id))).value;
    return value;
  }
  zakaziNavigate(event){
    this.pacijentService.getUlogovanKorisnik().subscribe(
      podaci => {
        var zahtev=new PredefZahtev;
        zahtev.datum=this.selectedDate;
        zahtev.pocetak=this.getSelectedTermin(this.izabraniLekar.id)

        let [hours,minutes] = zahtev.pocetak.split(':');  //svaki termin traje 30 min po defaultu
        console.log(hours,minutes);
        if(parseInt(minutes)<30){
          var mins=parseInt(minutes)+30
          var hrs=parseInt(hours);}
        else {
          if(parseInt(hours)==23)
            var hrs=0;
          else{
            var hrs=parseInt(hours)+1;
          }
          var mins=parseInt(minutes)-30;

        }

        var hrString=String(hrs);
        var minString=String(mins);
        if(hrString.length!=2)
          hrString='0'+hrString;
        if(minString.length!=2){
          minString='0'+minString;
        }

        zahtev.kraj=hrString+':'+minString;
        console.log(String(zahtev.kraj));

        zahtev.idKlinike=this.klinika.id;
        zahtev.jboPacijenta= podaci.jbo;
        zahtev.posiljalacImePrezime=podaci.ime+" "+podaci.prezime;
        zahtev.posiljalacJbo=podaci.jbo;
        zahtev.tipPosete='Pregled';
        zahtev.idStavke=this.izabraniTip.id;
        if(this.zahtev.specijalizacija!=null)
          zahtev.stavkaCenovnika=this.zahtev.specijalizacija; //ili ce biti pokupljeno iz prosle stranice ili izmenjeno na onchange,u suprotnom error
        else {
          alert("Morate izabrati tip pregleda");
          return; }
        if (this.dodatneInfo.length>=1)
          zahtev.dodatneInformacije=this.dodatneInfo;
        else
          zahtev.dodatneInformacije="Nema dodatnih informacija";
        zahtev.tipPosiljaoca='Pacijent';
        zahtev.jboLekara=this.izabraniLekar.jbo;
        this.predefTermService.zakaziTermin(zahtev).subscribe( data=>{alert('Termin uspesno zakazan');})

      },
    );
  }

  onChange(selected){
    this.zahtev.specijalizacija=selected.naziv;
    this.izabraniTip=selected;
  }

  getTipovi(){
    this.listaklSer.findTipovi().subscribe(
      podaci => {this.tipovi= podaci; },
      err => console.log('Nisu ucitani tipovi'),
      () => console.log('Uspesno ucitani tipovi')
    );
    
  }
 

//   ngOnInit() { I OVO
//     //console.log(history.state)
//     //console.log(history.state.datum);
//     this.klinika=history.state.klinika;
//     this.zahtev=history.state.zahtev;
    
//     if(this.zahtev.datum==null)
//       this.terminiSakriveni=true;
//     else
//       this.terminiSakriveni=false;
//     //console.log(this.klinika);
//     this.getLekari();

  
  onSelect(lekar){
    this.izabraniLekar=lekar;
    this.zahtev.start=this.getSelectedTermin(this.izabraniLekar.id)
  }


  ngOnInit() {

    var selectedD=this.date.value;
    this.selectedDate=moment(selectedD).format('D.M.YYYY.');
    this.klinika=history.state.klinika;
    this.zahtev=history.state.zahtev;
    this.izabraniTip=history.state.tip;
    console.log(history.state.tip)
    if(history.state.tip.idStavke==null)
      this.terminiSakriveni=true;
    else
      this.terminiSakriveni=false;
    this.getLekari();
    this.getTipovi();

  }


}
