package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Zahtev;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.LekarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lekar")
public class LekarController {

    @Autowired
    private LekarService lekarService;

    @PostMapping(value = "/update", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lekar> update(@RequestBody Lekar lekar) throws Exception {
        Lekar l = lekarService.update(lekar);

        if (l != null){
            return new ResponseEntity<>(lekar, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/register", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lekar> register(@RequestBody Lekar lekar) throws Exception {
        lekar.setAktivnostNaloga(true);
        lekar.setLozinka("12345678b");
        lekar.setBrSlobodnihDana(60);
        lekar.setPrvoLogovanje(true);
        Lekar l = lekarService.add(lekar);

        if (l != null){
            return new ResponseEntity<>(lekar, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/remove", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lekar> remove(@RequestBody Long id) throws Exception {

        Lekar lekar = lekarService.remove(id);

        if (lekar == null){
            return new ResponseEntity<>(lekar, HttpStatus.OK);
        }
        return new ResponseEntity<>(lekar, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Lekar>> getAllLekari() {

        List<Lekar> lekari = lekarService.findAll();

        return new ResponseEntity<>(lekari, HttpStatus.OK);
    }


    @PostMapping(value = "/getSlobodniLekari", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Lekar>> getSlobodniLekari(@RequestBody Zahtev zahtev) throws Exception {

        List<Lekar> lekari = lekarService.getSlobodniLekari(zahtev);

        return new ResponseEntity<>(lekari, HttpStatus.OK);
    }

    @PostMapping(value = "/lekarSlobodan", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> lekarSlobodan(@RequestBody Zahtev zahtev){

        Boolean lekarSlobodan =  lekarService.lekarSlobodan(zahtev);

        return new ResponseEntity<>(lekarSlobodan, HttpStatus.OK);
    }

    @PostMapping(value = "/klinika", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Lekar>> getAllLekariKlinike(@RequestBody Klinika k) {

        List<Lekar> lekari = lekarService.findAllByKlinika(k);

        return new ResponseEntity<>(lekari, HttpStatus.OK);
    }

}
