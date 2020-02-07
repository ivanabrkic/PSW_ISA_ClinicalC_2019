import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { Poseta } from 'src/app/models/poseta/poseta';
import { PoseteService } from 'src/app/services/posete-service/posete.service';
import { PacijentService } from 'src/app/services/pacijent-service/pacijent.service';
import { Pacijent } from 'src/app/models/pacijent/pacijent';
import { Pregled } from 'src/app/models/pregled/pregled';
import { Operacija } from 'src/app/models/operacija/operacija';
import { predefInfo } from 'src/app/models/predefInfoDTO/predefInfo';
import { PosetaIdTipDTO } from 'src/app/models/PosetaIdTipDTO/PosetaIdTipDTO';
import { Lekar } from 'src/app/models/lekar/lekar';
import * as moment from 'moment';

@Component({
    selector: 'app-lista-poseta',
    templateUrl: './pregledi-operacije-lista.html',
    styleUrls: ['/pregledi-operacije-lista.css']
  })

  export class PoseteComponent implements OnInit {
    private pregledi: Poseta[];
    private operacije: Poseta[];
    private selected:Poseta;
    private currentRateLekar:number=0; 
    private currentRateKlinika:number=0;
    private dozvoljenoOcenjivanjeLekara:boolean;
    private dozvoljenoOcenjivanjeKlinike:boolean;
    private pacijent: Pacijent=new Pacijent();
    private tipSort:boolean=true;
    private sortDatum:boolean=true;
  
    constructor(private poseteService: PoseteService,private pacijentService: PacijentService) {

      this.getPosete()
    }
  
    getPosete(){
      this.pacijentService.getUlogovanKorisnik()
      .subscribe(
        ulogovanKorisnik => {
          this.pacijent = ulogovanKorisnik;
          this.poseteService.findPreglediByPatientId(this.pacijent.id).subscribe(
            podaci => {
                      this.pregledi = podaci;
                      this.poseteService.findOperacijeByPatientId(this.pacijent.id).subscribe(data=>{
                      this.operacije=data;
                      this.pregledi[0].tipPosete="b";
                     
              })
            },
            err => console.log('Nisu ucitane posete'),
            () => console.log('Uspesno ucitane posete')
          );},
          err => console.log('Greska pri ucitavanju korisnika'),
       );
    }
 
    ngOnInit() {
      this.getPosete;
    }


    //provera da li je dozvoljeno ocenjivanje lekara i klinike selektovanog pregleda
    dozvoljenoOcenjivanje(idKlinikeSelected,jboLekaraSelected){

      this.dozvoljenoOcenjivanjeLekara=false;
      for(let p of this.pregledi){

        var tmp=JSON.parse(JSON.stringify(p)); 

        if(tmp.lekar.jbo===jboLekaraSelected){
          console.log(p)
          console.log(jboLekaraSelected)
          this.dozvoljenoOcenjivanjeLekara=true;
          console.log(this.dozvoljenoOcenjivanjeLekara)
          break;
        }
      }

      this.dozvoljenoOcenjivanjeKlinike=false;
      for(let p of this.pregledi){
        if(p.klinikaId===idKlinikeSelected){
          this.dozvoljenoOcenjivanjeKlinike=true;
          break;
        }
      }
    }

    onSelectOp(op:Poseta){
      this.selected=op;
      var tmp=JSON.parse(JSON.stringify(op)); 
      console.log(tmp.lekar.jbo)
      this.dozvoljenoOcenjivanje(this.selected.klinikaId,tmp.lekar.jbo)
      if(this.dozvoljenoOcenjivanjeKlinike)
        this.getOcenaKlinika()
      if(this.dozvoljenoOcenjivanjeKlinike)
        this.getOcenaLekar()  
    }

    onSelectPr(pr:Poseta){
      this.selected=pr;
      var tmp=JSON.parse(JSON.stringify(pr)); 
      console.log(tmp.lekar.jbo)
      this.dozvoljenoOcenjivanje(pr.klinikaId,tmp.lekar.jbo)
      if(this.dozvoljenoOcenjivanjeKlinike)
        this.getOcenaKlinika()
      if(this.dozvoljenoOcenjivanjeKlinike)
        this.getOcenaLekar()     
    }

    oceniKliniku($event){
      this.poseteService.oceniKliniku(this.currentRateKlinika,this.selected.klinikaId,this.pacijent.id).subscribe(
        data=>{console.log(data)}
      )
    }

    oceniLekara($event){
      console.log("Ocenjivanje lekara pozvano")
      var tmp=JSON.parse(JSON.stringify(this.selected)); 
      this.poseteService.oceniLekara(this.currentRateLekar,tmp.lekar.jbo,this.pacijent.id).subscribe(
        data=>{console.log(data)}
      )
    }

    getOcenaLekar(){
      var tmp=JSON.parse(JSON.stringify(this.selected)); 
      this.poseteService.getOcenaLekar(this.pacijent.id,tmp.lekar.id).subscribe(data=>{
        this.currentRateLekar=data
      })
    }

    getOcenaKlinika(){
      this.poseteService.getOcenaKlinika(this.pacijent.id,this.selected.klinikaId).subscribe(data=>{
        this.currentRateKlinika=data;
      })

    }

    
    sortByTip(){
            
      if(this.tipSort){
        this.pregledi.sort(function(a, b)  {
          if (a.tipPosete < b.tipPosete)
            return -1;
          if (a.tipPosete > b.tipPosete)
            return 1;
          return 0;
        });
        this.operacije.sort(function(a, b)  {
          if (a.tipPosete < b.tipPosete)
            return -1;
          if (a.tipPosete > b.tipPosete)
            return 1;
          return 0;
        });
        this.tipSort=false;
      }
      else{
      this.pregledi.sort(function(a, b)  {
        if (a.tipPosete < b.tipPosete)
          return 1;
        if (a.tipPosete > b.tipPosete)
          return -1;
        return 0;
      });
      this.operacije.sort(function(a, b)  {
        if (a.tipPosete < b.tipPosete)
          return 1;
        if (a.tipPosete > b.tipPosete)
          return -1;
        return 0;
      });
      this.tipSort=true;
    }
    }
    
    sortByDatum(){
      if(this.sortDatum){
        this.pregledi.sort(function(a, b)  {
          return <any>new Date(moment(a.datum, "D.M.YYYY.").format('YYYY-MM-DD')) - <any>new Date(moment(b.datum, "D.M.YYYY.").format('YYYY-MM-DD'));
        });
        this.operacije.sort(function(a, b)  {
          return <any>new Date(moment(a.datum, "D.M.YYYY.").format('YYYY-MM-DD')) - <any>new Date(moment(b.datum, "D.M.YYYY.").format('YYYY-MM-DD'));
        });
        this.sortDatum=false;
      }
      else{
      this.pregledi.sort(function(a, b)  {
        return <any>new Date(moment(b.datum, "D.M.YYYY.").format('YYYY-MM-DD')) - <any>new Date(moment(a.datum, "D.M.YYYY.").format('YYYY-MM-DD'));
      });
      this.operacije.sort(function(a, b)  {
        return <any>new Date(moment(b.datum, "D.M.YYYY.").format('YYYY-MM-DD')) - <any>new Date(moment(a.datum, "D.M.YYYY.").format('YYYY-MM-DD'));
      });
      this.sortDatum=true;
    }
    }

  }
  