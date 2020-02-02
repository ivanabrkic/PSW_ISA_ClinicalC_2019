package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Cenovnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.CenovnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "cenovnik")
public class CenovnikController {

    @Autowired
    private CenovnikService cenovnikService;

    @GetMapping(value = "/all", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllKlinike(){

        List<Cenovnik> listaCenovnika =  cenovnikService.findAll();
        List<String>tipovi=new ArrayList<>();

        for (Cenovnik c:listaCenovnika){
            tipovi.add(c.getNaziv());
        }

        return new ResponseEntity<>(tipovi, HttpStatus.OK);
    }

}
