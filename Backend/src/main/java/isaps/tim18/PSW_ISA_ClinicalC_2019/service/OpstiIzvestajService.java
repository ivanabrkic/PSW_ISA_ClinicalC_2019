package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.OpstiIzvestaj;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.OpstiIzvestajRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OpstiIzvestajService {

    @Autowired
    private OpstiIzvestajRepository opstiIzvestajRepository;

    public List<OpstiIzvestaj> findAll() { return opstiIzvestajRepository.findAll(); }

    @Transactional
    public OpstiIzvestaj update(OpstiIzvestaj opstiIzvestaj) {
        Optional<OpstiIzvestaj> o = opstiIzvestajRepository.findById(opstiIzvestaj.getId());
        if (o == null) {
            return null;
        }
        OpstiIzvestaj izvestaj;

        if(!o.isPresent()){
            opstiIzvestajRepository.save(opstiIzvestaj);
            return opstiIzvestaj;
        } else {
            izvestaj = o.get();
            izvestaj.setDioptrija(opstiIzvestaj.getDioptrija());
            izvestaj.setVisina(opstiIzvestaj.getVisina());
            izvestaj.setTezina(opstiIzvestaj.getTezina());
            izvestaj.setAlergijeNaLek(opstiIzvestaj.getAlergijeNaLek());
            izvestaj.setKrvnaGrupa(opstiIzvestaj.getKrvnaGrupa());
            opstiIzvestajRepository.save(izvestaj);
            return izvestaj;
        }


    }
}
