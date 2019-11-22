package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KorisnikService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class RegisterController {

//    @RequestMapping(value = "/registrationSubmit", method = RequestMethod.POST)
//    public String Register(@RequestBody Pacijent pac){
//
//        //provera email-a i username-a:
//
//        Pacijent pacijentUsername = KorisnikService.findAllByKorisnickoIme(pac.getKorIme());
//        if(pacijentUsername!=null){
//            return "Korisničko ime je zauzeto"
//        }
//
//        Pacijent pacijentEmail = KorisnikService.findAllByEmail(pac.getEmail());
//        if(pacijentEmail!=null){
//            return "Uneti e-mail je već u upotrebi";
//        }
//
//        //Kreiranje pacijenta
//
//        Pacijent noviPacijent = new Pacijent();
//        noviPacijent.setKorIme(pac.getKorIme());
//        noviPacijent.setEmail(pac.getEmail());
//        noviPacijent.setLozinka(pac.getLozinka());
//        noviPacijent.setIme(pac.getIme());
//        noviPacijent.setPrezime(pac.getPrezime());
//        noviPacijent.setAdresa(pac.getAdresa());
//        noviPacijent.setGrad(pac.getGrad());
//        noviPacijent.setDrzava(pac.getDrzava());
//        noviPacijent.setJbo(pac.getJbo());
//        noviPacijent.setKontaktTelefon(pac.getKontaktTelefon());
//        noviPacijent.setStatus(StatusNaloga.NERESEN);
//
//        userService.addUser(rk);
//    }
}
