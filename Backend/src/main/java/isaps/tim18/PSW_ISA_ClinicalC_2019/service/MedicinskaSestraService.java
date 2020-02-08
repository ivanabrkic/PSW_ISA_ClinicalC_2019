package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.Message;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.MedicinskaSestra;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.MedicinskaSestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public MedicinskaSestra update(MedicinskaSestra medicinskaSestra) {
        MedicinskaSestra p = medicinskaSestraRepository.findByJbo(medicinskaSestra.getJbo());
        if (p == null) {
            return null;
        }
        p.setAdresa(medicinskaSestra.getAdresa());
        p.setDrzava(medicinskaSestra.getDrzava());
        p.setEmail(medicinskaSestra.getEmail());
        p.setGrad(medicinskaSestra.getGrad());
        p.setIme(medicinskaSestra.getIme());
        p.setPrezime(medicinskaSestra.getPrezime());
        p.setKontaktTelefon(medicinskaSestra.getKontaktTelefon());
        p.setLozinka(medicinskaSestra.getLozinka());
        p.setPrvoLogovanje(medicinskaSestra.isPrvoLogovanje());
        medicinskaSestraRepository.save(p);
        return p;
    }

    @Transactional
    public Message add(MedicinskaSestra medicinskaSestra){
        if (medicinskaSestraRepository.findByJbo(medicinskaSestra.getJbo()) == null && medicinskaSestraRepository.findByEmail(medicinskaSestra.getEmail()) == null){
            medicinskaSestraRepository.save(medicinskaSestra);
            return new Message("Uspešno ste registrovali medicinsku sestru!");
        }

        return new Message("Ne možete registrovati medicinsku sestru! JBO ili email već postoje!");
    }

    @Transactional
    public Message remove(Long id){

        if(!medicinskaSestraRepository.findById(id).isPresent()) {
            return new Message("Medicinska sestra ne postoji!");
        }
        medicinskaSestraRepository.deleteById(id);

        return new Message("Uspešno otpuštena medicinska sestra!");
    }
}
