package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Recept;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.ReceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReceptService {
    @Autowired
    ReceptRepository receptRepository;

    public List<Recept> findAll() { return receptRepository.findAll(); }

    public Recept add(Recept r) { return receptRepository.save(r); }

    @Transactional
    public void remove(Recept r) { receptRepository.delete(r); }

    public List<Recept> findByPacijent(Pacijent p) { return receptRepository.findByPacijent(p); }

    public List<Recept> findByOveren(Boolean o) { return receptRepository.findByOveren(o); }

    @Transactional
    public Recept updateOveren(Recept recept){
        Optional<Recept> o = receptRepository.findById(recept.getId());
        Recept r;
        if(o.isPresent()){
            r = o.get();
            r.setOveren(recept.isOveren());
            receptRepository.save(r);
            return r;
        }

        return null;
    }

    @Transactional
    public Recept removeById(Recept recept){
        Optional<Recept> opt = receptRepository.findById(recept.getId());

        if(opt.isPresent()){
            receptRepository.delete(opt.get());
        }

        return recept;
    }

}
