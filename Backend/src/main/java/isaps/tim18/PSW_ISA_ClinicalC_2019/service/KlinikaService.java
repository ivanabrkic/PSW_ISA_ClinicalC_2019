package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KlinikaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class KlinikaService {

    @Autowired
    private KlinikaRepository klinikaRepo;

    public Klinika findByNaziv(String naziv) { return klinikaRepo.findByNaziv(naziv); }

    public Klinika findById(int id) { return klinikaRepo.findById(id); }

    public List<Klinika> findAll() { return klinikaRepo.findAll(); }

    @Transactional
    public Klinika add(Klinika klinika){ return klinikaRepo.save(klinika); }

    @Transactional
    public Klinika update(Klinika klinika) {
        Klinika p = klinikaRepo.findById(klinika.getId()).get();
        if (p == null) {
            return null;
        }
        p.setAdresa(klinika.getAdresa());
        p.setDrzava(klinika.getDrzava());
        p.setEmail(klinika.getEmail());
        p.setGrad(klinika.getGrad());
        p.setNaziv(klinika.getNaziv());
        p.setKontaktTelefon(klinika.getKontaktTelefon());
        p.setOcena(klinika.getOcena());
        klinikaRepo.save(p);
        return p;
    }
}
