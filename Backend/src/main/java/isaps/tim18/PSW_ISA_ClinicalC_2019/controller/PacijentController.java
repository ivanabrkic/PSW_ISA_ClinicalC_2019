package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaIdTipDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaLekarKlinikaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefInfoDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Operacija;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.OperacijaService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PacijentService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PregledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pacijent")
public class PacijentController {

    @Autowired
    private PacijentService pacijentService;

    @Autowired
    private OperacijaService operacijaService;

    @Autowired
    private PregledService pregledService;

    @PostMapping(value = "/update", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pacijent> update(@RequestBody Pacijent pacijent) throws Exception {

        Pacijent p = pacijentService.update(pacijent);

        if(p != null){
            return new ResponseEntity<>(pacijent, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pacijent>> getAllPacijenti() {

        List<Pacijent> pacijenti = pacijentService.findAll();

        return new ResponseEntity<>(pacijenti, HttpStatus.OK);
    }

    @GetMapping(value = "/allZahtevi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pacijent>> getAllZahtevi() {

        List<Pacijent> pacijenti = pacijentService.findByAktivnostNaloga(false);

        return new ResponseEntity<>(pacijenti, HttpStatus.OK);
    }

    @GetMapping(value = "/getOperacije", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Operacija>> getOperacijeById(@RequestBody Long id) {

        List<Operacija> operacije = operacijaService.findByPacijentId(id);
        if(operacije.size()>0)
            return new ResponseEntity<>(operacije, HttpStatus.OK);
        else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/getPregledi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pregled>> getPreglediById(@RequestBody Long id) {

        List<Pregled> pregledi = pregledService.findByPacijentId(id);
        if(pregledi.size()>0)
            return new ResponseEntity<>(pregledi, HttpStatus.OK);
        else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/viseInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<posetaLekarKlinikaDTO> viseInfo(@RequestBody posetaIdTipDTO dto) {

        Optional<Pregled> p= Optional.of(new Pregled());
        Optional<Operacija> o;
        posetaLekarKlinikaDTO info;

        if(dto.getTipPosete().equals("Poseta")) {
            p = pregledService.findById(dto.getIdPosete());
            info = pregledService.findInfo(p.get().getId());
            info.setId(p.get().getId());
        }
        else {
            o = operacijaService.findById(dto.getIdPosete());
            info=operacijaService.findInfo(p.get().getId());
            info.setId(o.get().getId());
        }

        return new ResponseEntity<>(info,HttpStatus.OK);
    }

    //update:
    //popuni ocene u tabeli
    //napravi tabelu i za klinike
    //kad stigne ocena :
    // POZOVI TRAZENJE PACIJENT TO.
    // AKO NEMA uzmi length vracenih za tu kliniku ili lekara dodaj novu saberi sve i leng+1 podeli i upisi
    // AKO IMA izmeni njegovu saberi sve i podeli sa length.

}
