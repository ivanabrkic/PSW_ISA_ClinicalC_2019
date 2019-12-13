package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Poseta;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PosetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "poseta")
public class PosetaController {

    @Autowired
    private PosetaService posetaService;

    @GetMapping(value = "/all", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Poseta>> getAll(){

        List<Poseta> listaPoseta =  posetaService.findAll();

        return new ResponseEntity<>(listaPoseta, HttpStatus.OK);
    }

    @GetMapping(value = "/pacijent_id", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Poseta>> getByPatientId(@RequestBody int id){

        List<Poseta> listaPoseta =  posetaService.findByPacijent_id(id);

        return new ResponseEntity<>(listaPoseta, HttpStatus.OK);
    }

}
