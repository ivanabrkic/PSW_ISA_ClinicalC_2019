package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@RestController
@RequestMapping("")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @GetMapping(value = "/ulogovanKorisnik", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> getUlogovanKorisnik(@Context HttpServletRequest request) {

        String jbo = (String) request.getSession().getAttribute("ulogovanKorisnik");
        Korisnik korisnik = korisnikService.findByJbo(jbo);

        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

}
