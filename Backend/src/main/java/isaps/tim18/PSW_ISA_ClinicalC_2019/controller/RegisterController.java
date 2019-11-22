package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.PacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private PacijentService pacijentService;

    @PostMapping(value = "/registrationSubmit", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public String Register(@RequestBody Pacijent pac){

        //provera email-a i username-a:

        Pacijent pacijentUsername = (Pacijent) pacijentService.getPacijent(pac.getKorIme());
        if(pacijentUsername!=null){
            return "Korisničko ime je zauzeto";
        }

        Pacijent pacijentEmail = (Pacijent) pacijentService.findByEmail(pac.getEmail());
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

        pacijentService.addPacijent(noviPacijent);
        System.out.println("Account with username " + noviPacijent.getKorIme() + "has been created");
        return "";
    }

    }
