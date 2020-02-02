package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PregledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PregledService {

    @Autowired
    PregledRepository pregledRepository;

    public List<PregledDTO> findAll() { return pregledRepository.findAll("smh"); }

    public void deleteZavrsen(Pregled p) { pregledRepository.delete(p);}
}
