package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekovi;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.LekoviRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LekoviService {

    @Autowired
    LekoviRepository lekoviRepository;

    public Lekovi findBySifra(String sifra) { return lekoviRepository.findBySifra(sifra); }

    public Lekovi findByNaziv(String nazivDijagnoze) {
        return lekoviRepository.findByNaziv(nazivDijagnoze);
    }

    @Transactional
    public Lekovi add(Lekovi novaDijagnoza){
        return lekoviRepository.save(novaDijagnoza);
    }

    public List<Lekovi> findAll(){
        return lekoviRepository.findAll();
    }
}
