package isaps.tim18.PSW_ISA_ClinicalC_2019.service;


import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZdravstveniKarton;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.ZdravstveniKartonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZdravstveniKartonService {

    @Autowired
    private ZdravstveniKartonRepository zdravstveniKartonRepo;

    public ZdravstveniKarton findById(int id) { return zdravstveniKartonRepo.findById(id); }
}
