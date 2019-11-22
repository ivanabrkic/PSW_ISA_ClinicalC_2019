package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.AdministratorKlinickogCentra;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.AdministratorKlinickogCentraRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.LekarRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class AdministratorKlinickogCentraService {

    @Autowired
    private AdministratorKlinickogCentraRepository administratorKlinickogCentraRepository;

    public List<AdministratorKlinickogCentra> findAll() {
        return administratorKlinickogCentraRepository.findAll();
    }

    public Page<AdministratorKlinickogCentra> findAll(Pageable page) {
        return administratorKlinickogCentraRepository.findAll(page);
    }

    public List<AdministratorKlinickogCentra> findAllByIme(String ime) {
        return administratorKlinickogCentraRepository.findAllByIme(ime);
    }

    public List<AdministratorKlinickogCentra> findAllByPrezime(String prezime) {
        return administratorKlinickogCentraRepository.findAllByPrezime(prezime);
    }

    public List<AdministratorKlinickogCentra> findByImeAndPrezime(String ime, String prezime) {
        return administratorKlinickogCentraRepository.findByImeAndPrezimeAllIgnoringCase(ime, prezime);
    }
}
