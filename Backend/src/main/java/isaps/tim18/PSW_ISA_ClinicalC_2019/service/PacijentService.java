package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PacijentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public Pacijent findByEmail(String email){
        return pacijentRepository.findByEmail(email);
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

    @Transactional
    public Pacijent update(Pacijent pacijent) {
        Pacijent p = pacijentRepository.findByJbo(pacijent.getJbo());
        if (p == null) {
            return null;
        }
        p.setAdresa(pacijent.getAdresa());
        p.setDrzava(pacijent.getDrzava());
        p.setEmail(pacijent.getEmail());
        p.setGrad(pacijent.getGrad());
        p.setIme(pacijent.getIme());
        p.setPrezime(pacijent.getPrezime());
        p.setKontaktTelefon(pacijent.getKontaktTelefon());
        p.setLozinka(pacijent.getLozinka());
        p.setPrvoLogovanje(pacijent.isPrvoLogovanje());
        pacijentRepository.save(p);
        return p;
    }

    @Transactional
    public void add(Pacijent pacijent){
        pacijentRepository.save(pacijent);
    }

    public Pacijent findByJbo(String jbo){
        return pacijentRepository.findByJbo(jbo);
    }

    public List<Pacijent> findByAktivnostNaloga(boolean aktivnost){
        return pacijentRepository.findByAktivnostNaloga(aktivnost);
    }

    @Transactional
    public Integer removeByJbo(String jbo){
        return pacijentRepository.removePacijentByJbo(jbo);
    }

    public Pacijent findPacijentByJbo(String jbo) {
        return pacijentRepository.findByJbo(jbo);
    }
  
	public Optional<Pacijent> findById(Long id) {
		return pacijentRepository.findById(id);
	}

}
