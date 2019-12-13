package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Poseta;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PosetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PosetaService {

    @Autowired
    private PosetaRepository posetaRepo;

    public Poseta findById(int id){
        return posetaRepo.findById(id);
    }

    public List<Poseta> findAll(){
        return posetaRepo.findAll();
    }

    public List<Poseta> findByPacijent_id(int id){
        return posetaRepo.findByPacijent_id(id);
    }

    @Transactional
    public Poseta add(Poseta poseta){
        return posetaRepo.save(poseta);
    }
}
