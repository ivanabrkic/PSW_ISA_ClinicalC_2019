package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacijent")
public class PacijentController {
    @Autowired
    private PacijentService pacijentService;

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


}
