package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.IzvestajDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Izvestaj;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Recept;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZdravstveniKarton;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.IzvestajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "izvestaj")
public class IzvestajController {

    @Autowired
    private IzvestajService izvestajService;

    @PostMapping(value = "/getIzvestaje", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IzvestajDTO>> getIzvestaje(@RequestBody Long zkId) throws Exception {
        System.out.println(zkId);
        List<IzvestajDTO> izvestaji = izvestajService.findIzvestajByZdravstveniKartonDTO(zkId);

        return new ResponseEntity<>(izvestaji, HttpStatus.OK);
    }

    @PostMapping(value = "/updateIzvestaj", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IzvestajDTO> updateIzvestaj(@RequestBody IzvestajDTO izvestajDTO) throws Exception {
        System.out.println(izvestajDTO.getIzvestaj());
        IzvestajDTO izvestaj = izvestajService.updateIzvestaj(izvestajDTO);

        return new ResponseEntity(izvestaj, HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Izvestaj> save(@RequestBody Izvestaj izvestaj) throws Exception {
        Izvestaj smth = izvestajService.save(izvestaj);

        return new ResponseEntity(smth, HttpStatus.OK);
    }

    @PostMapping(value = "/getReceptByIzvestajId", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Izvestaj> getReceptByIzvestajId(@RequestBody Long izvestaj) throws Exception {
        Recept smth = izvestajService.findReceptByIzvestaj(izvestaj);

        return new ResponseEntity(smth, HttpStatus.OK);
    }
}
