package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.MedicinskaSestra;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Sala;
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

    @PostMapping(value = "/getSale", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sala>> getSale(@RequestBody Long id){

        System.out.println(id);
        List<Sala> listaSala =  klinikaService.findSale(id);

        return new ResponseEntity<>(listaSala, HttpStatus.OK);
    }

    @PostMapping(value = "/update", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Klinika> update(@RequestBody Klinika klinika) throws Exception {

        System.out.println(klinika.getId());

        Klinika l = klinikaService.update(klinika);

        if (l != null){
            return new ResponseEntity<>(klinika, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/removeSala", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Klinika> remove(@RequestBody Long id) throws Exception {

        Klinika poruka = klinikaService.remove(id);

        return new ResponseEntity<>(poruka, HttpStatus.OK);
    }

    @PostMapping(value = "/registerSala", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sala> Register(@RequestBody Sala sala){

        Sala poruka = klinikaService.addNovaSala(sala.getNaziv(), sala.getId());

        return new ResponseEntity<>(poruka, HttpStatus.OK);
    }

    @PostMapping(value = "/getOperacije", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Operacija>> operacije(@RequestBody Sala sala){

        List<Operacija> operacije = klinikaService.getOperacije(sala);

        return new ResponseEntity<>(operacije, HttpStatus.OK);
    }

    @PostMapping(value = "/getPregledi", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pregled>> pregled(@RequestBody Sala sala) {

        List<Pregled> pregledi = klinikaService.getPregledi(sala);

        return new ResponseEntity<>(pregledi, HttpStatus.OK);
    }
}
