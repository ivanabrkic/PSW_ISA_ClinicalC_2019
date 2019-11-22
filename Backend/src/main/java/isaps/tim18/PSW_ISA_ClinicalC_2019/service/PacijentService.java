package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KorisnikRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PacijentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacijentService {

    @Autowired
    private PacijentRepository pacijentRepository;

    public List<Pacijent> findAll() {
        return pacijentRepository.findAll();
    }

    public Page<Pacijent> findAll(Pageable page) {
        return pacijentRepository.findAll(page);
    }

    public List<Pacijent> findAllByKorisnickoIme(String kime){
        return pacijentRepository.findAllByKorisnickoIme(kime);
    }

    public List<Pacijent> findAllByEmail(String email){
        return pacijentRepository.findAllByEmail(email);
    }

}
