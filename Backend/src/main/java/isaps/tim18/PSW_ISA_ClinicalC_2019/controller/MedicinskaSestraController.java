package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.MedicinskaSestra;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.MedicinskaSestraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicinska_sestra")
public class MedicinskaSestraController {

    @Autowired
    private MedicinskaSestraService medicinskaSestraService;

    @PostMapping(value = "/update", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicinskaSestra> update(@RequestBody MedicinskaSestra medicinskaSestra) throws Exception {

        MedicinskaSestra med = medicinskaSestraService.update(medicinskaSestra);

        if (med != null) {
            return new ResponseEntity<>(medicinskaSestra, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicinskaSestra>> getAllMedSestre() {

        List<MedicinskaSestra> medSestre = medicinskaSestraService.findAll();

        return new ResponseEntity<>(medSestre, HttpStatus.OK);
    }

    @PostMapping(value = "/remove", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicinskaSestra> remove(@RequestBody Long id) throws Exception {

        MedicinskaSestra med = medicinskaSestraService.remove(id);

        if (med == null){
            return new ResponseEntity<>(med, HttpStatus.OK);
        }
        return new ResponseEntity<>(med, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/register", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicinskaSestra> register(@RequestBody MedicinskaSestra medSestra) throws Exception {
        medSestra.setAktivnostNaloga(true);
        medSestra.setLozinka("12345678b");
        medSestra.setBrSlobodnihDana(60);
        medSestra.setPrvoLogovanje(true);
        MedicinskaSestra l = medicinskaSestraService.add(medSestra);

        if (l != null){
            return new ResponseEntity<>(medSestra, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
