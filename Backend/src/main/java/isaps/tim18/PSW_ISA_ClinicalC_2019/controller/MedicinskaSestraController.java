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

}
