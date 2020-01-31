package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Recept;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.ReceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReceptService {
    @Autowired
    ReceptRepository receptRepository;

    public Recept findById(int id) { return receptRepository.findById(id); }

    public List<Recept> findAll() { return receptRepository.findAll(); }

    public Recept add(Recept r) { return receptRepository.save(r); }

    @Transactional
    public void remove(Recept r) { receptRepository.delete(r); }

    public List<Recept> findByPacijent(Pacijent p) { return receptRepository.findByPacijent(p); }

    public List<Recept> findByOveren(Boolean o) { return receptRepository.findByOveren(o); }

    @Transactional
    public Recept updateOveren(Recept recept){
        Recept r = receptRepository.findByBrojAndPacijent(recept.getBroj(), recept.getPacijent());
        if(r != null){
            r.setOveren(recept.isOveren());
            receptRepository.save(r);
            return r;
        }
        return null;
    }

    @Transactional
    public Recept removeByBrojAndPacijent(Recept recept){
        receptRepository.delete(recept);
        return recept;
    }

}
