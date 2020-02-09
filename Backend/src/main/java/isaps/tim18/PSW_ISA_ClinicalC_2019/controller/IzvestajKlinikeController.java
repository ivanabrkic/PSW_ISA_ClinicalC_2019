package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.DateValueDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PrihodDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.IzvestajKlinikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("izvestajklinike")
public class IzvestajKlinikeController {

    @Autowired
    private IzvestajKlinikeService izvestajKlinikeService;

    @PostMapping(value = "/getPrihod", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrihodDTO> getPrihod(@RequestBody PrihodDTO prihodDTO) throws Exception {

        PrihodDTO prihod = izvestajKlinikeService.prihodDatum(prihodDTO);

        return new ResponseEntity<>(prihod, HttpStatus.OK);
    }

    @PostMapping(value = "/getMesecniBroj", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DateValueDTO>> getMesecniBroj(@RequestBody Long idKlinike) throws Exception {

        List<DateValueDTO> mesecniBrojPregleda = izvestajKlinikeService.mesecniBrojPregleda(idKlinike);

        return new ResponseEntity<>(mesecniBrojPregleda, HttpStatus.OK);
    }

    @PostMapping(value = "/getNedeljniBroj", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DateValueDTO>> getNedeljniBroj(@RequestBody Long idKlinike) throws Exception {

        List<DateValueDTO> nedeljniBrojPregleda = izvestajKlinikeService.nedeljniBrojPregleda(idKlinike);

        return new ResponseEntity<>(nedeljniBrojPregleda, HttpStatus.OK);
    }

    @PostMapping(value = "/getDnevniBroj", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DateValueDTO>> getDnevniBroj(@RequestBody Long idKlinike) throws Exception {

        List<DateValueDTO> dnevniNivoBrojPregleda = izvestajKlinikeService.dnevniNivoBrojPregleda(idKlinike);

        return new ResponseEntity<>(dnevniNivoBrojPregleda, HttpStatus.OK);
    }

}
