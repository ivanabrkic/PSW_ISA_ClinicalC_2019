package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.AdministratorKlinickogCentra;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.AdministratorKlinickogCentraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
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

    @Transactional
    public AdministratorKlinickogCentra update(AdministratorKlinickogCentra administratorKlinickogCentra) {
        AdministratorKlinickogCentra p = administratorKlinickogCentraRepository.findByJbo(administratorKlinickogCentra.getJbo());
        if (p == null) {
            return null;
        }
        p.setAdresa(administratorKlinickogCentra.getAdresa());
        p.setDrzava(administratorKlinickogCentra.getDrzava());
        p.setEmail(administratorKlinickogCentra.getEmail());
        p.setGrad(administratorKlinickogCentra.getGrad());
        p.setIme(administratorKlinickogCentra.getIme());
        p.setPrezime(administratorKlinickogCentra.getPrezime());
        p.setKontaktTelefon(administratorKlinickogCentra.getKontaktTelefon());
        p.setLozinka(administratorKlinickogCentra.getLozinka());
        p.setPrvoLogovanje(administratorKlinickogCentra.isPrvoLogovanje());
        administratorKlinickogCentraRepository.save(p);
        return p;
    }

    public List<AdministratorKlinickogCentra> findByImeAndPrezime(String ime, String prezime) {
        return administratorKlinickogCentraRepository.findByImeAndPrezimeAllIgnoringCase(ime, prezime);
    }
}
