package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.VerificationToken;

public interface IUserService {
    Korisnik getKorisnik(String verificationToken);
    void createVerificationToken(Korisnik korisnik, String token);
    VerificationToken getVerificationToken(String VerificationToken);
}
