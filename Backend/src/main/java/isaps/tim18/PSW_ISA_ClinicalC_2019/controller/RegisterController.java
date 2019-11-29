package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KorisnikService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PacijentService pacijentService;

    @Transactional
    @PostMapping(value = "/registrationSubmit", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> Register(@RequestBody Korisnik korisnik){

        Korisnik pacijentUsername = korisnikService.findByKorIme(korisnik.getKorIme());
        if(pacijentUsername!=null){
            return new ResponseEntity<>(korisnik, HttpStatus.BAD_REQUEST);
        }

        Korisnik pacijentEmail = korisnikService.findByEmail(korisnik.getEmail());
        if(pacijentEmail!=null){
            return new ResponseEntity<>(korisnik, HttpStatus.BAD_REQUEST);
        }

        Korisnik pacijentJbo = korisnikService.findByJbo(korisnik.getJbo());
        if(pacijentJbo!=null){
            return new ResponseEntity<>(korisnik, HttpStatus.BAD_REQUEST);
        }

        //Kreiranje pacijenta
        Pacijent noviPacijent = new Pacijent();
        noviPacijent.setKorIme(korisnik.getKorIme());
        noviPacijent.setEmail(korisnik.getEmail());
        noviPacijent.setLozinka(korisnik.getLozinka());
        noviPacijent.setIme(korisnik.getIme());
        noviPacijent.setPrezime(korisnik.getPrezime());
        noviPacijent.setAdresa(korisnik.getAdresa());
        noviPacijent.setGrad(korisnik.getGrad());
        noviPacijent.setDrzava(korisnik.getDrzava());
        noviPacijent.setJbo(korisnik.getJbo());
        noviPacijent.setKontaktTelefon(korisnik.getKontaktTelefon());
        noviPacijent.setAktivnostNaloga(Boolean.TRUE);
        noviPacijent.setTipKorisnika("Pacijent");

        pacijentService.addPacijent(noviPacijent);

        System.out.println("Account with username " + noviPacijent.getKorIme() + "has been created!");

        return new ResponseEntity<>(noviPacijent, HttpStatus.OK);
    }

    }
