package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.Message;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Sala;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sala")
public class SalaController{

    @Autowired
    private SalaService salaService;

    @PostMapping(value = "/getSale", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sala>> getSale(@RequestBody Long id) throws Exception {

        List<Sala> listaSala =  salaService.findSale(id);

        return new ResponseEntity<>(listaSala, HttpStatus.OK);
    }

    @PostMapping(value = "/removeSala", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> removeSala(@RequestBody Long id) throws ExceptionInInitializerError {

        Message poruka = salaService.remove(id);

        return new ResponseEntity<>(poruka, HttpStatus.OK);
    }

    @PostMapping(value = "/registerSala", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> registerSala(@RequestBody Sala sala) throws Exception{

        Message poruka = salaService.addNovaSala(sala);

        return new ResponseEntity<>(poruka, HttpStatus.OK);
    }

    @PostMapping(value = "/updateSala", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> updateSala(@RequestBody Sala sala) throws Exception{

        Message poruka = salaService.izmeniSalu(sala);

        return new ResponseEntity<>(poruka, HttpStatus.OK);
    }
}
