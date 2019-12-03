package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.AdministratorKlinike;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.AdministratorKlinikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "administrator_k")
public class AdministratorKlinikeController {

    @Autowired
    private AdministratorKlinikeService adminKlinikeService;

    @GetMapping(value = "/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdministratorKlinike>> getAllAdmineKlinike() {

        List<AdministratorKlinike> adminK = adminKlinikeService.findAll();

        return new ResponseEntity<>(adminK, HttpStatus.OK);
    }

    @RequestMapping(value = "/registrationSubmitAdmin", method = RequestMethod.POST)
    public String Register(@RequestBody Korisnik korisnik){

        AdministratorKlinike adminUsername = adminKlinikeService.findByKorIme(korisnik.getKorIme());
        if(adminUsername != null){
            return "Korisničko ime je zauzeto";
        }

        AdministratorKlinike adminEmail = adminKlinikeService.findByEmail(korisnik.getEmail());
        if(adminEmail != null){
            return "Email je već u upotrebi!";
        }

        Korisnik noviAdmin = Korisnik.builder()
                .korIme(korisnik.getKorIme())
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
                .tipKorisnika("Administrator klinike").build();

        AdministratorKlinike admin = new AdministratorKlinike(noviAdmin);
        adminKlinikeService.add(admin);

        return "Uspešno registrovan administrator!";
    }

    @PostMapping(value = "/update", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdministratorKlinike> update(@RequestBody AdministratorKlinike administratorKlinike) throws Exception {

        adminKlinikeService.update(administratorKlinike);

        return new ResponseEntity<>(administratorKlinike, HttpStatus.OK);
    }
}
