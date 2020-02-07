package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.Message;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.lekariterminiDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.LekarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("lekar")
public class LekarController {

    @Autowired
    private LekarService lekarService;

    @PostMapping(value = "/findLekarByJbo", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lekar> findLekarByJbo(@RequestBody String jbo) throws Exception {
        Lekar lekar = lekarService.findLekarByJbo(jbo);

        if (lekar != null){
            return new ResponseEntity<>(lekar, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/update", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lekar> update(@RequestBody Lekar lekar) throws Exception {
        Lekar l = lekarService.update(lekar);

        if (l != null){
            return new ResponseEntity<>(lekar, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/register", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> register(@RequestBody Lekar lekar) throws Exception {

        lekar.setAktivnostNaloga(true);
        lekar.setLozinka("12345678b");
        lekar.setBrSlobodnihDana(60);

        Message message = lekarService.add(lekar);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(value = "/remove", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> remove(@RequestBody Long id) throws Exception {

        Message message = lekarService.remove(id);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Lekar>> getAllLekari() {

        List<Lekar> lekari = lekarService.findAll();

        return new ResponseEntity<>(lekari, HttpStatus.OK);
    }


    @PostMapping(value = "/getSlobodniLekariTermini", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String,Lekar>> getSlobodniLekariTermini(@RequestBody lekariterminiDTO zahtev) throws Exception {

        HashMap<String,Lekar> lekari = lekarService.getSlobodniLekariTermini(zahtev);

        return new ResponseEntity<>(lekari, HttpStatus.OK);
    }

//    @PostMapping(value = "/lekarSlobodan", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Boolean> lekarSlobodan(@RequestBody Zahtev zahtev){
//
//        Boolean lekarSlobodan =  lekarService.lekarSlobodan(zahtev);
//
//        return new ResponseEntity<>(lekarSlobodan, HttpStatus.OK);
//    }

    @PostMapping(value = "/klinika", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Lekar>> getAllLekariKlinike(@RequestBody Klinika k) {

        List<Lekar> lekari = lekarService.findAllByKlinika(k);

        return new ResponseEntity<>(lekari, HttpStatus.OK);
    }

    @PostMapping(value = "/getBySpecijalizacija", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lekar> getBySpecijalizacija(@RequestBody String spec) {

        Lekar lekari = lekarService.findBySpecijalizacija(spec);

        return new ResponseEntity<>(lekari, HttpStatus.OK);
    }

}
