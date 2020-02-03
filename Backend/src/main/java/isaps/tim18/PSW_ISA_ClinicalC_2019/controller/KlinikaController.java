package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.Message;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KlinikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "klinika")
public class KlinikaController {

    @Autowired
    private KlinikaService klinikaService;

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

    @PostMapping(value = "/getZahtevi", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Zahtev>> getZahtevi(@RequestBody Long idKlinike) {

        List<Zahtev> zahtevi = klinikaService.getZahtevi(idKlinike);

        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }

    @PostMapping(value = "/getSaleSlobodneOd", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sala>> getSaleSlobodneOd(@RequestBody Zahtev zahtev){

        List<Sala> listaSala =  klinikaService.findSaleSlobodneOd(zahtev);

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
}
