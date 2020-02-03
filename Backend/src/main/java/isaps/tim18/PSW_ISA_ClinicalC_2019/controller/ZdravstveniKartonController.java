package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZdravstveniKarton;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.ZdravstveniKartonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "zk")
public class ZdravstveniKartonController {

    @Autowired
    private ZdravstveniKartonService zdravstveniKartonService;

    @GetMapping(value = "/all", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ZdravstveniKarton>> getAllZKartone(){

        List<ZdravstveniKarton> listaKartona =  zdravstveniKartonService.findAll();

        return new ResponseEntity<>(listaKartona, HttpStatus.OK);
    }

    @PostMapping(value = "/update", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZdravstveniKarton> update(@RequestBody ZdravstveniKarton zk){

        zdravstveniKartonService.update(zk);

        return new ResponseEntity<>(zk, HttpStatus.OK);
    }
}
