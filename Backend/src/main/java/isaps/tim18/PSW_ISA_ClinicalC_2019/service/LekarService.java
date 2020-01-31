package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.LekarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LekarService {

    @Autowired
    private LekarRepository lekarRepository;

    public List<Lekar> findAll() {
    return lekarRepository.findAll();
    }

    public Page<Lekar> findAll(Pageable page) {
    return lekarRepository.findAll(page);
    }

    public List<Lekar> findAllByIme(String ime) {
    return lekarRepository.findAllByIme(ime);
    }

    public List<Lekar> findAllByPrezime(String prezime) {
    return lekarRepository.findAllByPrezime(prezime);
    }

    public List<Lekar> findByImeAndPrezime(String ime, String prezime) {
    return lekarRepository.findByImeAndPrezimeAllIgnoringCase(ime, prezime);
    }

    @Transactional
    public Lekar update(Lekar lekar) {
        Lekar p = lekarRepository.findByJbo(lekar.getJbo());
        if (p == null) {
            return null;
        }
        p.setAdresa(lekar.getAdresa());
        p.setDrzava(lekar.getDrzava());
        p.setEmail(lekar.getEmail());
        p.setGrad(lekar.getGrad());
        p.setIme(lekar.getIme());
        p.setPrezime(lekar.getPrezime());
        p.setKontaktTelefon(lekar.getKontaktTelefon());
        p.setLozinka(lekar.getLozinka());
        p.setPrvoLogovanje(lekar.isPrvoLogovanje());
        lekarRepository.save(p);
        return p;
    }

    @Transactional
    public Lekar add(Lekar lekar){
        return lekarRepository.save(lekar);
    }

    @Transactional
    public Lekar remove(Long id){
        lekarRepository.deleteById(id);

        if(!lekarRepository.findById(id).isPresent()) {
            return null;
        }
        return lekarRepository.findById(id).get();
    }
}
