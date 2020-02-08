package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Izvestaj;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Recept;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.ReceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "recept")
public class ReceptController {

    @Autowired
    private ReceptService receptService;

    @GetMapping(value = "/all", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Recept>> getAllRecepte(){

        List<Recept> listaRecepata =  receptService.findAll();

        return new ResponseEntity<>(listaRecepata, HttpStatus.OK);
    }

    @GetMapping(value = "/getNeoverene", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Recept>> getAllNeoverene(){

        List<Recept> listaRecepata =  receptService.findByOveren(false);

        return new ResponseEntity<>(listaRecepata, HttpStatus.OK);
    }

    @GetMapping(value = "/getByPacijent", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Recept>> getAllByPacijent(Pacijent p){

        List<Recept> listaRecepata =  receptService.findByPacijent(p);

        return new ResponseEntity<>(listaRecepata, HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recept> Register(@RequestBody Recept recept){
        Recept r = recept;

        receptService.add(r);

        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @PostMapping(value = "/updateOveren", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recept> updateAktivnost(@RequestBody Recept recept) throws Exception {
        receptService.updateOveren(recept);

        return new ResponseEntity<>(recept, HttpStatus.OK);
    }

    @PostMapping(value = "/neoveren", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recept> ukloniOdbijen(@RequestBody Recept recept) throws Exception {


        receptService.removeById(recept);

        return new ResponseEntity<>(recept, HttpStatus.OK);
    }

    @PostMapping(value = "/findById", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recept> findById(@RequestBody Long receptId) throws Exception {
        Recept recept = receptService.findById(receptId);

        return new ResponseEntity<>(recept, HttpStatus.OK);
    }

//    @PostMapping(value = "/findByIzvestaj", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Recept> findByIzvestaj(@RequestBody Long id) throws Exception {
//        Recept recept = receptService.findByIzvestaj(id);
//        System.out.println("Id izvestaja: " + id);
//        return new ResponseEntity(recept, HttpStatus.OK);
//    }

    @PostMapping(value = "/update", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recept> update(@RequestBody Recept noviRecept) throws Exception {
        Recept recept = receptService.update(noviRecept);

        return new ResponseEntity<>(recept, HttpStatus.OK);
    }

}
