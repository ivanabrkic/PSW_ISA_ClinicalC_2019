package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PregledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "pregled")
public class PregledController {

    @Autowired
    PregledService pregledService;

    @GetMapping(value = "/allPreglede", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PregledDTO>> getPreglede() {
        List<PregledDTO> pregledi = pregledService.findAll();

        return new ResponseEntity<>(pregledi, HttpStatus.OK);
    }

    @PostMapping(value = "/zavrsenPregled", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pregled> zavrsenPregled(Pregled p){
        pregledService.deleteZavrsen(p);

        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}
