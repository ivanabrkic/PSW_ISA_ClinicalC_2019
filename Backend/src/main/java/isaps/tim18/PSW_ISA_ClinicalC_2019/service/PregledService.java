package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledIzvestajDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PregledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PregledService {

    @Autowired
    PregledRepository pregledRepository;

    public List<PregledIzvestajDTO> findAll() { return pregledRepository.findAll("smh"); }

    @Transactional
    public void updateZavrsen(PregledIzvestajDTO p) {

        Optional<Pregled> pregled = pregledRepository.findById(p.getId());

        if(pregled.isPresent()){
            Pregled noviPregled = pregled.get();
            noviPregled.setStatus("Završen");
            pregledRepository.save(noviPregled);

            return;
        }

        return;
    }
}
