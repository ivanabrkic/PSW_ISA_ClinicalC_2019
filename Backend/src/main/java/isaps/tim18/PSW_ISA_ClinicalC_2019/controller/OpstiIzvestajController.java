package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.OpstiIzvestaj;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.OpstiIzvestajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("opstiIzvestaj")
public class OpstiIzvestajController {

    @Autowired
    private OpstiIzvestajService opstiIzvestajService;

    @PostMapping(value = "/update", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OpstiIzvestaj> update(@RequestBody OpstiIzvestaj opstiIzvestaj) throws Exception {
        OpstiIzvestaj izvestaj = opstiIzvestajService.update(opstiIzvestaj);

        if (izvestaj != null){
            return new ResponseEntity<>(opstiIzvestaj, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OpstiIzvestaj>> all() throws Exception {
        List<OpstiIzvestaj> izvestaji = opstiIzvestajService.findAll();
        return new ResponseEntity<>(izvestaji, HttpStatus.OK);
    }

    @PostMapping(value = "/getIzvestajByZKartonId", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OpstiIzvestaj> getById(@RequestBody Long id) throws Exception {
        OpstiIzvestaj izvestaj = opstiIzvestajService.findByZkartonId(id);
        return new ResponseEntity<>(izvestaj, HttpStatus.OK);
    }
}
