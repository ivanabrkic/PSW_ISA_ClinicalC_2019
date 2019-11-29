package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.MedicinskaSestra;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.MedicinskaSestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicinskaSestraService {
    @Autowired
    private MedicinskaSestraRepository medicinskaSestraRepository;

    public List<MedicinskaSestra> findAll() {
        return medicinskaSestraRepository.findAll();
    }

    public Page<MedicinskaSestra> findAll(Pageable page) {
        return medicinskaSestraRepository.findAll(page);
    }

    public List<MedicinskaSestra> findAllByIme(String ime) {
        return medicinskaSestraRepository.findAllByIme(ime);
    }

    public List<MedicinskaSestra> findAllByPrezime(String prezime) {
        return medicinskaSestraRepository.findAllByPrezime(prezime);
    }

    public List<MedicinskaSestra> findByImeAndPrezime(String ime, String prezime) {
        return medicinskaSestraRepository.findByImeAndPrezimeAllIgnoringCase(ime, prezime);
    }

    public MedicinskaSestra update(MedicinskaSestra medicinskaSestra) {
        MedicinskaSestra p = medicinskaSestraRepository.findByJbo(medicinskaSestra.getJbo());
        if(p != null){
            p.setAdresa(medicinskaSestra.getAdresa());
            p.setDrzava(medicinskaSestra.getDrzava());
            p.setEmail(medicinskaSestra.getEmail());
            p.setGrad(medicinskaSestra.getGrad());
            p.setIme(medicinskaSestra.getIme());
            p.setPrezime(medicinskaSestra.getPrezime());
            p.setKontaktTelefon(medicinskaSestra.getKontaktTelefon());
            p.setKorIme(medicinskaSestra.getKorIme());
            p.setLozinka(medicinskaSestra.getLozinka());
            medicinskaSestraRepository.save(p);
            return p;
        }
        return null;
    }
}
