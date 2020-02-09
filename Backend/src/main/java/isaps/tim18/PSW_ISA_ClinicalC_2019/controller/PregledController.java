package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.LekarPacijentPregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledIzvestajDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PreglediStatusDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PregledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "pregled")
public class PregledController {

    @Autowired
    PregledService pregledService;

    @PostMapping(value = "/allPregledeByLekar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PregledIzvestajDTO>> getPreglede(@RequestBody Long idLekara) {
        List<PregledIzvestajDTO> pregledi = pregledService.findPregledeById(idLekara);

        return new ResponseEntity(pregledi, HttpStatus.OK);
    }

    @PostMapping(value = "/zavrsenPregled", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PregledIzvestajDTO> zavrsenPregled(@RequestBody PregledIzvestajDTO p){
        System.out.println(p.getJboLekara());
        pregledService.updateZavrsen(p);

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping(value = "/getZakazane", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PregledIzvestajDTO>> getZakazane(@RequestBody LekarPacijentPregledDTO lekarPacijentPregledDTO){
        List<PregledIzvestajDTO> zakazani = pregledService.getZakazane(lekarPacijentPregledDTO);

        return new ResponseEntity<>(zakazani, HttpStatus.OK);
    }

    @PostMapping(value = "/getZakazaneSestra", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PregledIzvestajDTO>> getZakazaneSestra(@RequestBody String jboPacijent){
        List<PregledIzvestajDTO> zakazani = pregledService.getZakazaneSestra(jboPacijent);

        return new ResponseEntity<>(zakazani, HttpStatus.OK);
    }

    @PostMapping(value = "/getAllPregledeByLekar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PreglediStatusDTO>> getPredefinisaneByLekar(@RequestBody String jbo){
        List<PreglediStatusDTO> zakazani = pregledService.getPregledeByLekar(jbo);

        return new ResponseEntity<>(zakazani, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PreglediStatusDTO>> findAll(){
        List<PreglediStatusDTO> zakazani = pregledService.findAll();

        return new ResponseEntity<>(zakazani, HttpStatus.OK);
    }
}
