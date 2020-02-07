package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZahtevOdsustvo;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.ZahtevOdsustvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "zahtevOdsustvo")
public class ZahtevOdsustvoController {

    @Autowired
    private ZahtevOdsustvoService zahtevOdsustvoService;

    @PostMapping(value = "/posaljiZahtev", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZahtevOdsustvo> posaljiZahtev(@RequestBody ZahtevOdsustvo zo){

        zahtevOdsustvoService.add(zo);

        return new ResponseEntity<>(zo, HttpStatus.OK);
    }

    @GetMapping(value = "/getNeodobrene", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ZahtevOdsustvo>> getNeodobrene(){

        List<ZahtevOdsustvo> zahtevi = zahtevOdsustvoService.findByNeodobren();

        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }

    @PostMapping(value = "/updateOveren", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZahtevOdsustvo> update(@RequestBody ZahtevOdsustvo zo){

        zahtevOdsustvoService.update(zo);

        return new ResponseEntity<>(zo, HttpStatus.OK);
    }

    @PostMapping(value = "/deleteNeoveren", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZahtevOdsustvo> deleteNeoveren(@RequestBody ZahtevOdsustvo zo){

        zahtevOdsustvoService.delete(zo);

        return new ResponseEntity<>(zo, HttpStatus.OK);
    }
}
