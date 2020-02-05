import { Component, OnInit } from '@angular/core';
import { Poseta } from 'src/app/models/poseta/poseta';
import { PoseteService } from 'src/app/services/posete-service/posete.service';
import { PacijentService } from 'src/app/services/pacijent-service/pacijent.service';
import { Pacijent } from 'src/app/models/pacijent/pacijent';
import { Pregled } from 'src/app/models/pregled/pregled';
import { Operacija } from 'src/app/models/operacija/operacija';
import { predefInfo } from 'src/app/models/predefInfoDTO/predefInfo';
import { PosetaIdTipDTO } from 'src/app/models/PosetaIdTipDTO/PosetaIdTipDTO';

@Component({
    selector: 'app-lista-poseta',
    templateUrl: './pregledi-operacije-lista.html',
    styleUrls: ['/pregledi-operacije-lista.css']
  })

  export class PoseteComponent implements OnInit {
    private pregledi: Pregled[];
    private operacije: Operacija[];
    private selectedPregled:Pregled;
    private selectedOperacija:Operacija;
    private selected:predefInfo;
    private selectedLOcena:number;
    private selectedKOcena:number;

    private pacijent: Pacijent=new Pacijent();
  
    constructor(private poseteService: PoseteService,private pacijentService: PacijentService) {
    }
  
    getPosete(){
      this.pacijentService.getUlogovanKorisnik()
      .subscribe(
        ulogovanKorisnik => {
          this.pacijent = ulogovanKorisnik;
          this.poseteService.findPreglediByPatientId(this.pacijent.id).subscribe(
            podaci => {this.pregledi = podaci;
              this.poseteService.findOperacijeByPatientId(this.pacijent.id).subscribe(data=>{
                this.operacije=data;
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

    getMoreInfo(id:number,tip:String){
      var dto=new PosetaIdTipDTO();
      dto.idPosete=id;
      dto.tipPosete=tip;
      this.poseteService.getMoreInfo(dto).subscribe(data=>{
        this.selected.idKlinike=data.klinikaId;
        this.selected.nazivKlinike=data.klinikaNaziv;
        this.selected.jboLekara=data.lekarJbo;
        this.selected.lekarIme=data.lekarIme;
        this.selected.lekarPrezime=data.lekarPrezime;
        this.selectedKOcena=data.klinikaOcena;
        this.selectedLOcena=data.lekarOcena;
      });;
    }

    onSelectOp(op:Operacija){
      
      this.selectedOperacija=op;
      this.getMoreInfo(op.id,'Operacija')
    }


    onSelectPr(pr:Pregled){
      this.selectedPregled=pr;
      this.getMoreInfo(pr.id,'Pregled');
    }
  }
  