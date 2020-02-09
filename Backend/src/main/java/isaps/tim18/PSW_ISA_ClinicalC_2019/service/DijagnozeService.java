package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Dijagnoze;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.DijagnozeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DijagnozeService {

    @Autowired
    DijagnozeRepository dijagnozeRepo;

    public Optional<Dijagnoze> findById(Long id) { return dijagnozeRepo.findById(id); }

    public Dijagnoze findBySifraDijagnoze(String sifra) { return dijagnozeRepo.findBySifraDijagnoze(sifra); }

    public List<String> find(Long id){ return dijagnozeRepo.find(id); }

    @Transactional
    public Dijagnoze add(Dijagnoze novaDijagnoza){
        return dijagnozeRepo.save(novaDijagnoza);
    }

    public Dijagnoze findByNazivDijagnoze(String nazivDijagnoze) {
        return dijagnozeRepo.findByNazivDijagnoze(nazivDijagnoze);
    }

    public List<Dijagnoze> findAll(){
        return dijagnozeRepo.findAll();
    }
}
