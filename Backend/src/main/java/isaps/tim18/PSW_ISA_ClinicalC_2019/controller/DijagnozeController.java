package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Dijagnoze;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZdravstveniKarton;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.DijagnozeService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.ZdravstveniKartonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "dijagnoze")
public class DijagnozeController {

    @Autowired
    private DijagnozeService dijagnozeService;

    @GetMapping(value = "/get", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dijagnoze>> getDijagnozePacijenta(@RequestBody Pacijent pacijent){

      List<Dijagnoze> dijagnoze =dijagnozeService.find(pacijent.getId());

      return new ResponseEntity<>(dijagnoze, HttpStatus.OK);

    }
}
