package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.IzvestajDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Izvestaj;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Recept;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.ZdravstveniKarton;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.IzvestajRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class IzvestajService {

    @Autowired
    private IzvestajRepository izvestajRepository;

    public List<IzvestajDTO> findIzvestajByZdravstveniKartonDTO(Long zkId) { return izvestajRepository.findIzvestajByZdravstveniKartonDTO(zkId); }

    @Transactional
    public IzvestajDTO updateIzvestaj(IzvestajDTO izvestajDTO) {
        System.out.println(izvestajDTO.getId());
        Optional<Izvestaj> izvestaj = izvestajRepository.findById(izvestajDTO.getId());
        Izvestaj i = new Izvestaj();
        if(izvestaj.isPresent()){
            i = izvestaj.get();

            i.setIzvestaj(izvestajDTO.getIzvestaj());

            izvestajRepository.save(i);
            return izvestajDTO;
        }
        System.out.println("Error");
        return null;
    }

    public Recept findReceptByIzvestaj(Long izvestaj) {
        Optional<Izvestaj> izv = izvestajRepository.findById(izvestaj);
        if(izv.isPresent()){
            Izvestaj i = izv.get();
            return i.getRecept();
        }
        System.out.println("Error");
        return null;
    }

    public Izvestaj save(Izvestaj izvestaj) { return izvestajRepository.save(izvestaj); }
}
