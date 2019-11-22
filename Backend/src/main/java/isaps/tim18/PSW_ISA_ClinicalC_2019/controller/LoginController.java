package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private KorisnikService korisnikService;

    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    public String Register(@RequestBody Korisnik kor){

        //provera email-a i username-a:

        Korisnik korisnikEmail = (Korisnik) korisnikService.findAllByEmail(kor.getEmail());
        if(korisnikEmail==null){
            return "Uneti e-mail je nepostojeći";
        }
        else{
            Korisnik korisnikPass = (Korisnik) korisnikService.findAllByPassword(kor.getEmail());
            if(korisnikPass.getLozinka()!=kor.getLozinka()){
                return "Uneta lozinka je neispravna";
            }
        }

        System.out.println("Account with username " + kor.getKorIme() + "has been logged in");

        //Sacuvati korisnika u sesiji

        return "";

    }
}
