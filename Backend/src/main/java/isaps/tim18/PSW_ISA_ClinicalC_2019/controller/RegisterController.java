package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.VerificationToken;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZdravstveniKarton;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.IUserService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KorisnikService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private PacijentService pacijentService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private IUserService service;

    @PostMapping(value = "/registrationSubmit", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> Register(@RequestBody Korisnik korisnik){

        Korisnik pacijentEmail = korisnikService.findByEmail(korisnik.getEmail());
        if(pacijentEmail!=null){
            korisnik.setIme("Email vec postoji!");
            return new ResponseEntity<>(korisnik, HttpStatus.BAD_REQUEST);
        }

        Korisnik pacijentJbo = korisnikService.findByJbo(korisnik.getJbo());
        if(pacijentJbo!=null){
            korisnik.setIme("Jbo vec postoji!");
            return new ResponseEntity<>(korisnik, HttpStatus.BAD_REQUEST);
        }

        //Builder pattern

        Korisnik noviPacijent = Korisnik.builder()
                .lozinka(korisnik.getLozinka())
                .email(korisnik.getEmail())
                .ime(korisnik.getIme())
                .prezime(korisnik.getPrezime())
                .grad(korisnik.getGrad())
                .drzava(korisnik.getDrzava())
                .adresa(korisnik.getAdresa())
                .aktivnostNaloga(false)
                .jbo(korisnik.getJbo())
                .kontaktTelefon(korisnik.getKontaktTelefon())
                .tipKorisnika("Pacijent")
                .prvoLogovanje(true)
                .build();
        ZdravstveniKarton zdravstveniKarton = new ZdravstveniKarton();
        //Dodavanje novog pacijenta preko konstruktora koji prima Korisnika kao parametar
        //Dodaje se u isto vreme u obe tabele, u tabelu Pacijent kao referenca na tabelu Korisnik
        Pacijent p = new Pacijent(noviPacijent);
        pacijentService.add(p);

        System.out.println("Account with email " + noviPacijent.getEmail() + "has been created!");

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @GetMapping(value = "/registrationConfirm")
    public String confirmRegistration(WebRequest request, @RequestParam("token") String token){

        VerificationToken verificationToken = service.getVerificationToken(token);
        if(verificationToken == null){
            return "redirect:/welcome";
        }

        Korisnik korisnik = verificationToken.getKorisnik();
        korisnik.setAktivnostNaloga(true);
        korisnikService.updateAktivnost(korisnik);
        return "redirect:/login";

    }

}
