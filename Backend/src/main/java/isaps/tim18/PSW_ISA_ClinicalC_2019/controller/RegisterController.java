package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KorisnikService;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class RegisterController {

    @Autowired
    private KorisnikService korisnikService;

    @RequestMapping(value = "/registrationSubmit", method = RequestMethod.POST)
    public String Register(@RequestBody Pacijent pac){

        //provera email-a i username-a:

        Pacijent pacijentUsername = (Pacijent) korisnikService.getKorisnik(pac.getKorIme());
        if(pacijentUsername!=null){
            return "Korisničko ime je zauzeto";
        }

        Pacijent pacijentEmail = (Pacijent) korisnikService.findAllByEmail(pac.getEmail());
        if(pacijentEmail!=null){
            return "Uneti e-mail je već u upotrebi";
        }

        //Kreiranje pacijenta

        Pacijent noviPacijent = new Pacijent();
        noviPacijent.setKorIme(pac.getKorIme());
        noviPacijent.setEmail(pac.getEmail());
        noviPacijent.setLozinka(pac.getLozinka());
        noviPacijent.setIme(pac.getIme());
        noviPacijent.setPrezime(pac.getPrezime());
        noviPacijent.setAdresa(pac.getAdresa());
        noviPacijent.setGrad(pac.getGrad());
        noviPacijent.setDrzava(pac.getDrzava());
        noviPacijent.setJbo(pac.getJbo());
        noviPacijent.setKontaktTelefon(pac.getKontaktTelefon());
        noviPacijent.setAktivnostNaloga(Boolean.FALSE);

        korisnikService.addKorisnik(noviPacijent);
        System.out.println("Account with username " + noviPacijent.getKorIme() + "has been created");
        return "";
    }

    }
