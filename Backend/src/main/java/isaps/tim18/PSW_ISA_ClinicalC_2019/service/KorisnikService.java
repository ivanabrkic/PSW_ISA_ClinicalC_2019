package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.VerificationToken;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KorisnikRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class KorisnikService implements IUserService{

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    public List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }

    public List<Korisnik> findNeregistrovane() {
        return korisnikRepository.findAllByAktivnostNaloga(false);
    }

    public Korisnik findByEmail(String email){
        return korisnikRepository.findByEmail(email);
    }

    public Korisnik findByEmailAndLozinka(String email, String lozinka){
        return korisnikRepository.findByEmailAndLozinka(email, lozinka);
    }

    public Korisnik findByJbo(String jbo) {
        return korisnikRepository.findByJbo(jbo);
    }

//    public Korisnik findById(Long id) {
//        return korisnikRepository.findById(id);
//    }

    @Transactional
    public Integer removeByJbo(String jbo){
        return korisnikRepository.removeKorisnikByJbo(jbo);
    }

    @Transactional
    public Korisnik add(Korisnik korisnik){

        return korisnikRepository.save(korisnik);
    }

    @Transactional
    public Korisnik update(Korisnik korisnik) {
        Korisnik k = korisnikRepository.findByJbo(korisnik.getJbo());
        if(k != null){
            k.setAdresa(korisnik.getAdresa());
            k.setDrzava(korisnik.getDrzava());
            k.setEmail(korisnik.getEmail());
            k.setGrad(korisnik.getGrad());
            k.setIme(korisnik.getIme());
            k.setPrezime(korisnik.getPrezime());
            k.setKontaktTelefon(korisnik.getKontaktTelefon());
            k.setLozinka(korisnik.getLozinka());
            k.setPrvoLogovanje(korisnik.isPrvoLogovanje());
            korisnikRepository.save(k);
            return k;
        }
        return null;
    }

    @Transactional
    public Korisnik updateAktivnost(Korisnik korisnik){
        Korisnik k = korisnikRepository.findByJbo(korisnik.getJbo());
        if(k != null){
            k.setAktivnostNaloga(korisnik.getAktivnostNaloga());
            korisnikRepository.save(k);
            return k;
        }
        return null;
    }

    @Override
    public Korisnik getKorisnik(String verificationToken) {
        return tokenRepository.findByToken(verificationToken).getKorisnik();
    }

    @Override
    public void createVerificationToken(Korisnik korisnik, String token) {
        VerificationToken myToken = new VerificationToken(token, korisnik);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
}
