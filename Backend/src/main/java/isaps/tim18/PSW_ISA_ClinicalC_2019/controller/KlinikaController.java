package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KlinikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@RestController
@RequestMapping(value = "/api/registracijaKlinike")
public class KlinikaController {

    @Autowired
    private KlinikaService klinikaService;

    @RequestMapping(value = "/registracijaKlinike", method = RequestMethod.POST)
    public String Register(@RequestBody Klinika klinika){

        Klinika novaKlinika = new Klinika();

        novaKlinika.setNaziv(klinika.getNaziv());

        klinikaService.addKliniku(novaKlinika);
        return "Uspešno registrovana klinika!";
    }
}
