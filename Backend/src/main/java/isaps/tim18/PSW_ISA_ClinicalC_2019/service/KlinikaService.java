package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KlinikaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KlinikaService {

    @Autowired
    private KlinikaRepository klinikaRepo;

    public Klinika findByNaziv(String naziv) { return klinikaRepo.findByNaziv(naziv); }

    public Klinika findById(int id) { return klinikaRepo.findById(id); }

    public Klinika addKliniku(Klinika klinika){
        return klinikaRepo.save(klinika);
    }
}
