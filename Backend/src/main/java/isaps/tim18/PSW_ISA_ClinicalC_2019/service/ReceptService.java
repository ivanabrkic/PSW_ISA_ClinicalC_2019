package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Izvestaj;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekovi;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Recept;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.IzvestajRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.ReceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceptService {
    @Autowired
    ReceptRepository receptRepository;

    @Autowired
    LekoviService lekoviService;

    @Autowired
    IzvestajRepository izvestajRepository;

    public Recept findById(Long id) {
        Optional<Recept> r = receptRepository.findById(id);

        if(r.isPresent()){
            return r.get();
        }

        return null;
    }

    public List<Recept> findAll() { return receptRepository.findAll(); }

    @Transactional
    public Recept add(Recept r) {
        List<Lekovi> lekovi = new ArrayList<Lekovi>();
        Lekovi lek = new Lekovi();
        for(int i = 0;i < r.getLekovi().size(); i++){
            lek = lekoviService.findBySifra(r.getLekovi().get(i).getSifra());

            if(lek != null){
                lekovi.add(lek);
            }
        }

        Recept recept = new Recept();
        recept.setLekovi(lekovi);
        recept.setOveren(false);
        recept.setMedicinskaSestra(r.getMedicinskaSestra());
        recept.setPacijent(r.getPacijent());

        return receptRepository.save(recept);
    }

    @Transactional
    public void remove(Recept r) {
        receptRepository.obrisiLekove(r.getId());
        receptRepository.delete(r);
    }

    public List<Recept> findByPacijent(Pacijent p) { return receptRepository.findByPacijent(p); }

    public List<Recept> findByOveren(Boolean o) { return receptRepository.findByOveren(o); }

//    public Recept findByIzvestaj(Long izvestajID) {
//        Optional<Izvestaj> izv = izvestajRepository.findById(izvestajID);
//
//        if(izv.isPresent()){
//            System.out.println("Nasao je izvestaj");
//            return receptRepository.findByIzvestaj(izv.get());
//        }
//
//
//        return null;
//    }

    @Transactional
    public Recept updateOveren(Recept recept){
        Optional<Recept> o = receptRepository.findById(recept.getId());
        Recept r;
        if(o.isPresent()){
            r = o.get();
            r.setOveren(recept.isOveren());
            r.setMedicinskaSestra(recept.getMedicinskaSestra());
            receptRepository.save(r);
            return r;
        }

        return null;
    }

    @Transactional
    public Recept removeById(Recept recept){
        Optional<Recept> opt = receptRepository.findById(recept.getId());

        if(opt.isPresent()){
            receptRepository.obrisiLekove(opt.get().getId());
            receptRepository.delete(opt.get());
        }

        return recept;
    }

    @Transactional
    public Recept update(Recept recept){
        Optional<Recept> opt = receptRepository.findById(recept.getId());
        List<Lekovi> lekovi = new ArrayList<Lekovi>();

        if(opt.isPresent()){
            Recept r = opt.get();
            Lekovi lek = new Lekovi();
            for(int i = 0;i < recept.getLekovi().size(); i++){
                lek = lekoviService.findBySifra(recept.getLekovi().get(i).getSifra());

                if(lek != null){
                    lekovi.add(lek);
                }
            }

            r.setLekovi(lekovi);

            r.setOveren(false);
            receptRepository.save(r);
        }

        return recept;
    }
}
