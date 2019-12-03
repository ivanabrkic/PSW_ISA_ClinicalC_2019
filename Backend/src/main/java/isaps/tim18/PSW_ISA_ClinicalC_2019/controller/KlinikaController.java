package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KlinikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "klinika")
public class KlinikaController {

    @Autowired
    private KlinikaService klinikaService;

    @PostMapping(value = "/registracijaKlinike", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public String Register(@RequestBody Klinika klinika){

        Klinika novaKlinika = new Klinika();

        novaKlinika.setNaziv(klinika.getNaziv());

        klinikaService.add(novaKlinika);
        return "Uspešno registrovana klinika!";
    }
}
