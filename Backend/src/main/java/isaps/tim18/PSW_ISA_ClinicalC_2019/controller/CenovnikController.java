package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.Message;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Cenovnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.CenovnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "cenovnik")
public class CenovnikController {

    @Autowired
    private CenovnikService cenovnikService;

    @PostMapping(value = "/getTipovi", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cenovnik>> getTipovi(@RequestBody Long id) throws Exception {

        List<Cenovnik> listaTipova =  cenovnikService.getTipovi(id);

        return new ResponseEntity<>(listaTipova, HttpStatus.OK);
    }

    @PostMapping(value = "/removeTip", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> removeTip(@RequestBody Long id) throws ExceptionInInitializerError {

        Message poruka = cenovnikService.removeTip(id);

        return new ResponseEntity<>(poruka, HttpStatus.OK);
    }

    @PostMapping(value = "/registerTip", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> registerTip(@RequestBody Cenovnik cenovnik) throws Exception{

        Message poruka = cenovnikService.registerTip(cenovnik);

        return new ResponseEntity<>(poruka, HttpStatus.OK);
    }

    @PostMapping(value = "/updateTip", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> updateTip(@RequestBody Cenovnik cenovnik) throws Exception{

        Message poruka = cenovnikService.izmeniTip(cenovnik);

        return new ResponseEntity<>(poruka, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cenovnik>> getAllKlinike(){

        List<Cenovnik> listaCenovnika =  cenovnikService.findAll();
//        List<String>tipovi=new ArrayList<>();
//
//        for (Cenovnik c:listaCenovnika){
//            tipovi.add(c.getNaziv());
//        }

        return new ResponseEntity<>(listaCenovnika, HttpStatus.OK);
    }

}