package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.LekarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LekarService {

    @Autowired
    private LekarRepository lekarRepository;

    public List<Lekar> findAll() {
    return lekarRepository.findAll();
    }

    public Page<Lekar> findAll(Pageable page) {
    return lekarRepository.findAll(page);
    }

    public List<Lekar> findAllByIme(String ime) {
    return lekarRepository.findAllByIme(ime);
    }

    public List<Lekar> findAllByPrezime(String prezime) {
    return lekarRepository.findAllByPrezime(prezime);
    }

    public List<Lekar> findByImeAndPrezime(String ime, String prezime) {
    return lekarRepository.findByImeAndPrezimeAllIgnoringCase(ime, prezime);
    }
}
