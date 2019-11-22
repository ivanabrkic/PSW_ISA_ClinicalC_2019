package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.MedicinskaSestra;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PacijentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
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

    public List<Pacijent> findAllByKorIme(String kime){
        return pacijentRepository.findAllByKorIme(kime);
    }

    public List<Pacijent> findAllByEmail(String email){
        return pacijentRepository.findAllByEmail(email);
    }

    public List<Pacijent> findAllByIme(String ime) {
        return pacijentRepository.findAllByIme(ime);
    }

    public List<Pacijent> findAllByPrezime(String prezime) {
        return pacijentRepository.findAllByPrezime(prezime);
    }

    public List<Pacijent> findByImeAndPrezime(String ime, String prezime) {
        return pacijentRepository.findByImeAndPrezimeAllIgnoringCase(ime, prezime);
    }

    public Pacijent update(Pacijent pacijent) {
        Pacijent p = pacijentRepository.findByJbo(pacijent.getJbo());
        if(p != null){
            p.setAdresa(pacijent.getAdresa());
            p.setDrzava(pacijent.getDrzava());
            p.setEmail(pacijent.getEmail());
            p.setGrad(pacijent.getGrad());
            p.setIme(pacijent.getIme());
            p.setPrezime(pacijent.getPrezime());
            p.setKontaktTelefon(pacijent.getKontaktTelefon());
            p.setKorIme(pacijent.getKorIme());
            p.setLozinka(pacijent.getLozinka());
            pacijentRepository.save(p);
            return p;
        }
        return null;
    }
}
