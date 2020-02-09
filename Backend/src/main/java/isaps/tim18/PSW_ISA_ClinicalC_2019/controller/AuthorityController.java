package isaps.tim18.PSW_ISA_ClinicalC_2019.controller;

import isaps.tim18.PSW_ISA_ClinicalC_2019.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorityController {

    @Autowired
    private KorisnikService korisnikService;

    public boolean checkRole(Long userId, String role){

//        Korisnik k = korisnikService.findById(userId);
//
//        if (k.getTipKorisnika().equals(role)){
//            return true;
//        }

        return false;
    }
}
