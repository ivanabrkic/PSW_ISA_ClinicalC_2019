package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekovi;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.LekoviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "lekovi")
public class LekoviController {

    @Autowired
    private LekoviService lekoviService;

    @GetMapping(value = "/getAll", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Lekovi>> getAll(){

        List<Lekovi> lekovi = lekoviService.findAll();

        return new ResponseEntity<>(lekovi, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addLek(@RequestBody Lekovi novLek){
        Lekovi d = lekoviService.findBySifra(novLek.getSifra());
        if(d != null){
            System.out.println("Nije null");
            return "Dijagnoza sa datom sifrom vec postoji";
        }
        System.out.println("Jeste null");
        lekoviService.add(novLek);

        return "Uspesno dodata dijagnoza";
    }
}
