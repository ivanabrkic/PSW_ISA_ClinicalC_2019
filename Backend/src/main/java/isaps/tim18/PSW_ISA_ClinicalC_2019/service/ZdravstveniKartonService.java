package isaps.tim18.PSW_ISA_ClinicalC_2019.service;


import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZdravstveniKarton;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.ZdravstveniKartonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZdravstveniKartonService {

    @Autowired
    private ZdravstveniKartonRepository zdravstveniKartonRepo;

    public ZdravstveniKarton findById(int id) { return zdravstveniKartonRepo.findById(id); }

    public List<ZdravstveniKarton> findAll() { return zdravstveniKartonRepo.findAll(); }

    public ZdravstveniKarton getByPacijent(Pacijent pacijent) { return  zdravstveniKartonRepo.findByPacijent(pacijent); }

    public ZdravstveniKarton update(ZdravstveniKarton zk){
        Optional<ZdravstveniKarton> zdravstveniKarton = zdravstveniKartonRepo.findById(zk.getId());

        ZdravstveniKarton noviZdravstveniKarton;

        if(zdravstveniKarton.isPresent()){
            noviZdravstveniKarton = zdravstveniKarton.get();
            noviZdravstveniKarton.setDijagnoze(zk.getDijagnoze());
            noviZdravstveniKarton.setOpstiIzvestaj(zk.getOpstiIzvestaj());
            zdravstveniKartonRepo.save(noviZdravstveniKarton);
            return noviZdravstveniKarton;
        }

        return null;
    }

    public ZdravstveniKarton updateDijagnoze(ZdravstveniKarton zk){
        Optional<ZdravstveniKarton> zdravstveniKarton = zdravstveniKartonRepo.findById(zk.getId());

        ZdravstveniKarton noviZdravstveniKarton;

        if(zdravstveniKarton.isPresent()){
            System.out.println("Nasao je zk u bazi");
            noviZdravstveniKarton = zdravstveniKarton.get();
            noviZdravstveniKarton.setDijagnoze(zk.getDijagnoze());
            zdravstveniKartonRepo.save(noviZdravstveniKarton);
            return noviZdravstveniKarton;
        }

        return null;
    }
}
