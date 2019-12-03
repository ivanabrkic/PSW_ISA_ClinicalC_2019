package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    public List<Korisnik> findAll() {
        return null;
    }

    public Korisnik findByKorIme(String korIme){
        return korisnikRepository.findByKorIme(korIme);
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
    public Korisnik add(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }
}
