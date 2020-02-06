package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KlinikaService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.LekarService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PredefTerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "klinika")
public class KlinikaController {

    @Autowired
    private KlinikaService klinikaService;

    @Autowired
    private LekarService lekarService;

    @Autowired
    private PredefTerminService predefTerminService;

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
    
    @PostMapping(value = "/getPreglediPredef", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<predefInfoDTO>> pregledpredef(@RequestBody Long id) {

        List<predefInfoDTO> pregledi = klinikaService.getPreglediPredef(id);

        return new ResponseEntity<>(pregledi, HttpStatus.OK);
    }

    ////////////////////////////////////// USLUZIVANJE ZAHTEVA //////////////////////////////////////////

    @PostMapping(value = "/getZahtevi", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Zahtev>> getZahtevi(@RequestBody Long idKlinike) {

        List<Zahtev> zahtevi = klinikaService.getZahtevi(idKlinike);

        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
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

        String zakazi =  klinikaService.zakaziPregled(pregled);
        Message m = new Message(zakazi);

        return new ResponseEntity<>(m, HttpStatus.OK);
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
    
    //////////////////////////////////// TESLA ///////////////////////////////////////////////////

    @PostMapping(value="/slobodneKlinike", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Klinika>> slobodneKlinike(@RequestBody lekariterminiDTO zahtev) throws ParseException {


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

        return new ResponseEntity<>(slobodneKlinike, HttpStatus.OK); //vracanje slobodnih
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
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
