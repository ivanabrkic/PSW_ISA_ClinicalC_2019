package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "klinika")
public class KlinikaController {

    @Autowired
    private KlinikaService klinikaService;

    @Autowired
    private LekarService lekarService;

    @Autowired
    private PredefTerminService predefTerminService;

    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private PregledService pregledService;

    @Autowired
    private AdministratorKlinikeService adminklService;

    @Autowired
    private CenovnikService cenovnikService;

    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping(value = "/registracijaKlinike", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public String Register(@RequestBody Klinika klinika){
        float ocena = (float)1.0;
        Klinika novaKlinika = klinika;
        klinika.setOcena(ocena);

        try {
            klinikaService.add(novaKlinika);
        } catch(Exception e){
            klinikaService.update(novaKlinika);
        }
        return "Uspešno registrovana klinika!";
    }

    @GetMapping(value = "/all", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Klinika>> getAllKlinike(){

        List<Klinika> listaKlinika =  klinikaService.findAll();

        return new ResponseEntity<>(listaKlinika, HttpStatus.OK);
    }

    @PostMapping(value = "/getMedSestre", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicinskaSestra>> getMedSestre(@RequestBody Long id){

        List<MedicinskaSestra> listaMedSestara =  klinikaService.findMedSestre(id);

        return new ResponseEntity<>(listaMedSestara, HttpStatus.OK);
    }

    @PostMapping(value = "/getLekari", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Lekar>> getLekari(@RequestBody Long id){

        List<Lekar> listaLekara =  klinikaService.findLekari(id);

        return new ResponseEntity<>(listaLekara, HttpStatus.OK);
    }

    @PostMapping(value = "/update", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Klinika> update(@RequestBody Klinika klinika) throws Exception {

        Klinika l = klinikaService.update(klinika);

        if (l != null){
            return new ResponseEntity<>(klinika, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/getOperacije", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OperacijaDTO>> operacije(@RequestBody Long id){

        List<OperacijaDTO> operacije = klinikaService.getOperacije(id);

        return new ResponseEntity<>(operacije, HttpStatus.OK);
    }

    @PostMapping(value = "/getPregledi", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PregledDTO>> pregled(@RequestBody Long id) {

        List<PregledDTO> pregledi = klinikaService.getPregledi(id);

        return new ResponseEntity<>(pregledi, HttpStatus.OK);
    }

    @PostMapping(value = "/getPreglediPredefKlinikaId", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<predefInfoDTO>> getPreglediPredefKlinikaId(@RequestBody Long id) throws Exception {

        List<predefInfoDTO> pregledi = klinikaService.getPreglediPredef(id);
        return new ResponseEntity<>(pregledi, HttpStatus.OK);
    }
      
    //Vodi racuna o tome kad je pacijent zauzet kad vraca termine
    @PostMapping(value = "/getPreglediPredef", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<predefInfoDTO>> pregledpredef(@RequestBody klinikaPacDTO k) throws Exception {
    	
        List<predefInfoDTO> pregledi = klinikaService.getPreglediPredefKlinPac(k.getIdKlin(),k.getIdPac()); //Prosli termini se ne izlistavaju.
        
        System.out.print(pregledi);

        return new ResponseEntity<>(pregledi, HttpStatus.OK);
    }

    @PostMapping(value = "/getZahtevi", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Zahtev>> getZahtevi(@RequestBody Long idKlinike) {
    	
    	try {
	        List<Zahtev> zahtevi = klinikaService.getZahtevi(idKlinike);
	
	        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
	        }
    	catch(Exception E) {
    		
    		List<Zahtev> zahtevi =new ArrayList<Zahtev>();
    		return new ResponseEntity<>(zahtevi, HttpStatus.BAD_REQUEST);
    	}
    }

    @PostMapping(value = "/getSlobodniLekari", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Lekar>> getSlobodniLekari(@RequestBody Zahtev zahtev) throws Exception {
    	
    	try {
	        List<Lekar> lekari = klinikaService.getSlobodniLekariCeoLekar(zahtev);
	
	        return new ResponseEntity<>(lekari, HttpStatus.OK);
    	}catch(Exception e) {
    		 List<Lekar> lekari = new ArrayList<Lekar>();
    		 return new ResponseEntity<>(lekari, HttpStatus.BAD_REQUEST);
    	}
    }


    @PostMapping(value = "/getSlobodneSale", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SalaDTO>> getSaleSlobodneOd(@RequestBody Zahtev zahtev){

        List<SalaDTO> listaSala =  klinikaService.findSlobodneSale(zahtev);

        return new ResponseEntity<>(listaSala, HttpStatus.OK);
    }

    @PostMapping(value = "/getDrugiTermin", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SalaDTO>> getDrugiTermin(@RequestBody Zahtev zahtev){

        List<SalaDTO> listaSala =  klinikaService.findDrugiTermin(zahtev);

        return new ResponseEntity<>(listaSala, HttpStatus.OK);
    }

    @PostMapping(value = "/zakaziPregled", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> zakaziPregled(@RequestBody PregledDTO pregled){
        try {

            String zakazi = klinikaService.zakaziPregled(pregled);
            Message m = new Message(zakazi);

            return new ResponseEntity<>(m, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new Message("fail"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/zakaziOperaciju", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> zakaziOperaciju(@RequestBody OperacijaDTO operacija){

        String zakazi =  klinikaService.zakaziOperaciju(operacija);
        Message m = new Message(zakazi);

        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @PostMapping(value = "/removeZahtev", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> removeZahtev(@RequestBody Long idZahteva){

        Boolean zahtevi =  klinikaService.removeZahtev(idZahteva);

        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }

    @PostMapping(value = "/obavestiMejlomZahtevPrihvacen", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> obavestiMejlomZahtevPrihvacen(@RequestBody EmailDTO emailDTO) throws Exception {

        mailSenderService.sendSimpleMessage(emailDTO.getEmail(), emailDTO.getSubject(), emailDTO.getText());

        return new ResponseEntity<>(new Message("Uspešno poslat mejl korisniku!"), HttpStatus.OK);
    }
    
    //////////////////////////////////// TESLA ///////////////////////////////////////////////////

    @PostMapping(value="/slobodneKlinike", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<klinikaCena>> slobodneKlinike(@RequestBody lekariterminiDTO zahtev) throws ParseException {

    	try {
	        List<Klinika> slobodneKlinike=new ArrayList<>();
	        HashMap<String,Lekar> lekari;
	
	        List<Klinika> listaKlinika =  klinikaService.findAll(); //sve klinike
	        for (Klinika k : listaKlinika){  //filtriranje zauzetih
	            zahtev.setIdKlinike(k.getId());
	            lekari=lekarService.getSlobodniLekariTermini(zahtev);
	            if(!lekari.isEmpty()){
	                slobodneKlinike.add(k);
	            }
	        }
	        
	        List<klinikaCena> klinikaICena=new ArrayList<klinikaCena>();
	        for(Klinika k:slobodneKlinike) {
	        	float cena=cenovnikService.findByNazivAndKlinikaId(zahtev.getSpecijalizacija(),k.getId());
	        	klinikaICena.add(new klinikaCena(k,cena));
	        	
	        }
	        
	        return new ResponseEntity<>(klinikaICena, HttpStatus.OK); //vracanje slobodnih
        }
    	catch(Exception e) {
    		
    		 List<klinikaCena> praznaLista=new ArrayList<klinikaCena>();
    		return new ResponseEntity<>(praznaLista, HttpStatus.BAD_REQUEST);
    	}
    }

    ////////////////////// ZA DODAVANJE PREDEF TERMINA ////////////////////////////////////////////////////////////////////////
    @PostMapping(value="/getTermini", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TerminDTO>> getTermini(@RequestBody LekarTrajanjeDTO lekarTrajanjeDTO) throws ParseException {

        List<TerminDTO> termini = predefTerminService.getAllTerminiAndSale(lekarTrajanjeDTO.getIdLekara(), lekarTrajanjeDTO.getTrajanje());

        return new ResponseEntity<>(termini, HttpStatus.OK);
    }

    @PostMapping(value="/getLekariForTip", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Lekar>> getLekariForTip(@RequestBody Long idTipa) throws ParseException {

        List<Lekar> lekari = predefTerminService.getAllLekariForTip(idTipa);

        return new ResponseEntity<>(lekari, HttpStatus.OK);
    }

    @PostMapping(value="/dodajTermin", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> dodajTermin(@RequestBody Pregled pregled) throws ParseException {

        Message message = predefTerminService.dodajTermin(pregled);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(value = "/zakaziTermin", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Zahtev> zakaziTermin(@RequestBody Zahtev zahtev){
    	
    	System.out.print("Zahtev primljen");
       
            zahtevService.add(zahtev);
            List<AdministratorKlinike> administratori=adminklService.findAllByKlinikaId(zahtev.getIdKlinike());
            MailSenderService m=new MailSenderService();
            for (AdministratorKlinike a : administratori) {
            	m.sendSimpleMessage(a.getEmail(),"Novi zahtev",
            			 "Pristigao je novi zahtev za zakazivanje termina"+
            			" Za datum: "+zahtev.getDatum()+" i vreme: "+zahtev.getPocetak()+"-"+zahtev.getKraj()+
            			" za posetu: "+ zahtev.getTipPosete()+" tipa: "
            			+ ""+zahtev.getStavkaCenovnika()+" od strane: "+zahtev.getTipPosiljaoca()+
            			". Ulogujte se na vas nalog kako biste prihvatili ili odbili zahtev."
            			+ ""
            			+ "Ovaj mejl je automatski generisan i na njega nemojte odgovarati.");
            }
            return new ResponseEntity<>(zahtev, HttpStatus.OK); //vracanje slobodnih  
        
    }

    @PostMapping(value = "/zakaziPredefTerminn", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pregled> zakaziPredefTermin(@RequestBody predefDTO pregled)throws Exception{
    	
    	try {
	        System.out.print(pregled.getId());
	        Pregled pronadjenpregled=pregledService.update(pregled);
	        return new ResponseEntity<>(pronadjenpregled, HttpStatus.OK);
    	}
    	catch(Exception e) {
    		Pregled pronadjenpregled=new Pregled();
    		 return new ResponseEntity<>(pronadjenpregled, HttpStatus.BAD_REQUEST);
    	}

       

    }

//    @PostMapping(value = "/getCena", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
//    public float NadjiCenu(@RequestBody lekariterminiDTO zahtev)throws Exception{
//
//        Cenovnik cen=cenovnikRepository.findByNazivAndKlinikaId(zahtev.getSpecijalizacija(),zahtev.getIdKlinike() );

//        Cenovnik cen = cenovnikService.findByNazivAndKlinikaId(zahtev.getSpecijalizacija(),zahtev.getIdKlinike() );

//
//        return cen.getCena();
//
//    }

//



    @PostMapping(value = "/findOperacijeByLekar", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OperacijaKalendarDTO>> nadjiOperacijePoLekaru(@RequestBody Lekar lekar)throws Exception{
        System.out.println(lekar.getJbo());
        List<OperacijaKalendarDTO> operacije = klinikaService.findOperacijeByLekar(lekar);

        return new ResponseEntity<>(operacije, HttpStatus.OK);

    }


    @GetMapping(value = "/getZakazaneOperacije", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OperacijaKalendarDTO>> getZakazaneOperacije() {
        List<OperacijaKalendarDTO> operacije = klinikaService.getZakazaneOperacije();

        return new ResponseEntity<>(operacije, HttpStatus.OK);
    }
}

