package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    public List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }

    public Page<Korisnik> findAll(Pageable page) {
        return korisnikRepository.findAll(page);
    }

    List<Korisnik> findAllByKorisnickoIme(String kime){
        return korisnikRepository.findAllByKorisnickoIme(kime);
    }
}
