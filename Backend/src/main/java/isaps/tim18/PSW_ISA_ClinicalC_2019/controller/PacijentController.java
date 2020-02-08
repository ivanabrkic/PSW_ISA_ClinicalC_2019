package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaIdTipDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaLekarKlinikaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Operacija;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.oceneKlinike;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.oceneKlinikeKljuc;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.oceneLekari;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.oceneLekariKljuc;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KlinikaService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.LekarService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.OperacijaService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PacijentService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PregledService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.oceneKlinikeService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.oceneLekariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pacijent")
public class PacijentController {

    @Autowired
    private PacijentService pacijentService;

    @Autowired
    private OperacijaService operacijaService;

    @Autowired
    private PregledService pregledService;
    
    @Autowired
    private oceneLekariService oceneLekariService;
    
    @Autowired
    private oceneKlinikeService oceneKlinikeService;
    
    @Autowired
    private LekarService lekarService;
    
    @Autowired 
    KlinikaService klinikeService;

    @PostMapping(value = "/update", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pacijent> update(@RequestBody Pacijent pacijent) throws Exception {

        Pacijent p = pacijentService.update(pacijent);

        if(p != null){
            return new ResponseEntity<>(pacijent, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pacijent>> getAllPacijenti() {

        List<Pacijent> pacijenti = pacijentService.findAll();

        return new ResponseEntity<>(pacijenti, HttpStatus.OK);
    }

    @GetMapping(value = "/allZahtevi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pacijent>> getAllZahtevi() {

        List<Pacijent> pacijenti = pacijentService.findByAktivnostNaloga(false);

        return new ResponseEntity<>(pacijenti, HttpStatus.OK);
    }

    @PostMapping(value = "/getPacijent", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pacijent> getPacijent(@RequestBody String jbo) throws Exception {

        Pacijent p = pacijentService.findByJbo(jbo);

        if(p != null){
            return new ResponseEntity<>(p, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/findPacijentByJbo", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pacijent> findPacijentByJbo(@RequestBody String jbo) throws Exception {
        Pacijent pacijent = pacijentService.findPacijentByJbo(jbo);

        if (pacijent != null){
            return new ResponseEntity<>(pacijent, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
      
    @PostMapping(value = "/getOperacije", consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<posetaLekarKlinikaDTO>> getOperacijeById(@RequestBody Long id) {
    	
    	//vraca operacije u proslosti
        List<posetaLekarKlinikaDTO> operacije = operacijaService.findInfo(id);
        
        return new ResponseEntity<>(operacije, HttpStatus.OK);

    }
    
    @PostMapping(value = "/getPregledi", consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<posetaLekarKlinikaDTO>> getPreglediById(@RequestBody Long id) {
    	
    	//vraca preglede u proslosti
        List<posetaLekarKlinikaDTO> pregledi = pregledService.findInfo(id);
        
        return new ResponseEntity<>(pregledi, HttpStatus.OK);

    }
    
    @GetMapping(value = "/getOcenaLekara", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Float> getOcenaLekara(@RequestParam("pacijentId") String pacijentId, @RequestParam("idLekara")String idLekara){
    	
    			oceneLekari ocenaLekara=oceneLekariService.findByPacijentAndLekarId(Long.parseLong(pacijentId), Long.parseLong(idLekara));
    			if(ocenaLekara!=null) {
    				System.out.print(ocenaLekara.getOcena());
    				return new ResponseEntity<>(ocenaLekara.getOcena(),HttpStatus.OK);	
    			}
    			else {
    				float ocena=0;
    				return new ResponseEntity<>(ocena,HttpStatus.OK);
    			}
	    
    	}
    
    @GetMapping(value = "/getOcenaKlinike", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Float> getOcenaKlinike(@RequestParam("pacijentId") String pacijentId, @RequestParam("idKlinike")String idKlinike){
    	
    			oceneKlinike ocenaKlinike=oceneKlinikeService.findByPacijentAndKlinikaId(Long.parseLong(pacijentId), Long.parseLong(idKlinike));
    			if(ocenaKlinike!=null)
    				return new ResponseEntity<>(ocenaKlinike.getOcena(),HttpStatus.OK);	
    			else {
    				float ocena=0;
    				return new ResponseEntity<>(ocena,HttpStatus.OK);
    			}
	    
    	}

    
    @GetMapping(value = "/oceniLekara", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> oceniLekara(@RequestParam("ocena") String ocena, @RequestParam("jboLekara")String jboLekara,@RequestParam("idPacijenta")String idPacijenta){
    	
    	float ocenaF=Float.parseFloat(ocena);
    	Long idPacijentaL=Long.parseLong(idPacijenta);
    	Optional<Pacijent> pacijent=pacijentService.findById(idPacijentaL);
    	Lekar lekar = lekarService.findByJbo(jboLekara);
    	Long idLekara=lekar.getId();
  
    	
    	try {
	    	//Provera da li je pacijent vec ocenio lekara
	    	oceneLekari ocenaLekara=oceneLekariService.findByPacijentAndLekarId(idPacijentaL,idLekara);
	    	//Ukoliko postoji izmeni je
	    	oceneLekariService.update(ocenaLekara, ocenaF);
    		return new ResponseEntity<>("Ocena izmenjena",HttpStatus.OK);
    	}
    	catch(Exception e){
	    	//Ukoliko ocena ne postoji dodaj je
    			oceneLekari ocenaLekara=new oceneLekari();
    			ocenaLekara.setId(new oceneLekariKljuc(idPacijentaL,idLekara));
    			ocenaLekara.setOcena(ocenaF);
    			ocenaLekara.setLekar(lekar);
    			ocenaLekara.setPacijent(pacijent.get());
	    		oceneLekariService.add(ocenaLekara);
	    		return new ResponseEntity<>("Ocena dodata",HttpStatus.OK);	
	    
    	}
    }
    
    @GetMapping(value = "/oceniKliniku", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> oceniKliniku(@RequestParam("ocena") String ocena, @RequestParam("idKlinike")String idKlinike,@RequestParam("idPacijenta")String idPacijenta){
    	
    	Optional<Pacijent> pacijent=pacijentService.findById(Long.parseLong(idPacijenta));
    	Optional<Klinika> klinika=klinikeService.findById(Long.parseLong(idKlinike));
    	
 
    	//Provera da li je pacijent vec ocenio kliniku
    	oceneKlinike ocenaKlinike=oceneKlinikeService.findByPacijentAndKlinikaId(Long.parseLong(idPacijenta),Long.parseLong(idKlinike));
    	
    	//Ukoliko ocena ne postoji dodaj je
    	if(ocenaKlinike==null) {
    		ocenaKlinike=new oceneKlinike();
    		ocenaKlinike.setId(new oceneKlinikeKljuc(Long.parseLong(idPacijenta),Long.parseLong(idKlinike)));
    		ocenaKlinike.setPacijent(pacijent.get());
    		ocenaKlinike.setKlinika(klinika.get());
    		ocenaKlinike.setOcena(Float.parseFloat(ocena));
    		oceneKlinikeService.add(ocenaKlinike);
    		return new ResponseEntity<>("Ocena dodata",HttpStatus.OK);
    		
    	}
    	//Ukoliko postoji izmeni je
    	else {
    		oceneKlinikeService.update(ocenaKlinike, Float.parseFloat(ocena));
    		return new ResponseEntity<>("Ocena izmenjena",HttpStatus.OK);
    	}
    	
    }

}
