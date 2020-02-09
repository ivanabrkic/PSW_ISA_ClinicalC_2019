package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Dijagnoze;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZdravstveniKarton;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.DijagnozeService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.ZdravstveniKartonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "dijagnoze")
public class DijagnozeController {

    @Autowired
    private DijagnozeService dijagnozeService;

    @GetMapping(value = "/getAll", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dijagnoze>> getAll(){

        List<Dijagnoze> dijagnoze = dijagnozeService.findAll();

        return new ResponseEntity<>(dijagnoze, HttpStatus.OK);
    }

    @GetMapping(value = "/get", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getDijagnozePacijenta(@RequestParam String id){
      System.out.println(id);
      List<String> dijagnoze = dijagnozeService.find(Long.parseLong(id));

      return new ResponseEntity<>(dijagnoze, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addDijagnozu(@RequestBody Dijagnoze novaDijagnoza){
        Dijagnoze d = dijagnozeService.findBySifraDijagnoze(novaDijagnoza.getSifraDijagnoze());
        if(d != null){
            System.out.println("Nije null");
            return "Dijagnoza sa datom sifrom vec postoji";
        }
        System.out.println("Jeste null");
        dijagnozeService.add(novaDijagnoza);

        return "Uspesno dodata dijagnoza";
    }
}
