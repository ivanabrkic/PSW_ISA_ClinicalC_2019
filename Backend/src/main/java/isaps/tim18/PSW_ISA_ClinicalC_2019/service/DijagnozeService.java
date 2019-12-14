package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Dijagnoze;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.DijagnozeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DijagnozeService {

    @Autowired
    DijagnozeRepository dijagnozeRepo;

    public List<Dijagnoze> findById(int id) { return dijagnozeRepo.findBySifra(id); }

    public List<String> find(Long id){ return dijagnozeRepo.find(id); }
}
