package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.AdministratorKlinike;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.HelperUserClass;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.AdministratorKlinikeService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KlinikaService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.ZahtevOdsustvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.List;

@RestController
@RequestMapping(value = "administrator_k")
public class AdministratorKlinikeController {

    @Autowired
    private AdministratorKlinikeService adminKlinikeService;

    @Autowired
    private KlinikaService klinikaService;

    @Autowired
    private ZahtevOdsustvoService zahtevOdsustvoService;

    @GetMapping(value = "/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdministratorKlinike>> getAllAdmineKlinike() {

        List<AdministratorKlinike> adminK = adminKlinikeService.findAll();

        return new ResponseEntity<>(adminK, HttpStatus.OK);
    }

    @RequestMapping(value = "/registrationSubmitAdmin", method = RequestMethod.POST)
    public String Register(@RequestBody HelperUserClass korisnik){
        AdministratorKlinike adminEmail = adminKlinikeService.findByEmail(korisnik.getEmail());
        if(adminEmail != null){
            return "Email je već zauzet!";
        }

        Korisnik noviAdmin = Korisnik.builder()
                .lozinka(korisnik.getLozinka())
                .email(korisnik.getEmail())
                .ime(korisnik.getIme())
                .prezime(korisnik.getPrezime())
                .grad(korisnik.getGrad())
                .drzava(korisnik.getDrzava())
                .adresa(korisnik.getAdresa())
                .aktivnostNaloga(true)
                .jbo(korisnik.getJbo())
                .kontaktTelefon(korisnik.getKontaktTelefon())
                .prvoLogovanje(true)
                .tipKorisnika("Administrator klinike").build();

        Klinika k = klinikaService.findByNaziv(korisnik.getKlinika());
        AdministratorKlinike admin = new AdministratorKlinike(noviAdmin);
        admin.setKlinika(k);
        adminKlinikeService.add(admin);

        return "Uspešno registrovan administrator!";
    }

    @PostMapping(value = "/update", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdministratorKlinike> update(@RequestBody AdministratorKlinike administratorKlinike, @Context HttpServletRequest request) throws Exception {

        AdministratorKlinike admin = adminKlinikeService.update(administratorKlinike);

        if (admin != null) {
            return new ResponseEntity<>(administratorKlinike, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    }
}
