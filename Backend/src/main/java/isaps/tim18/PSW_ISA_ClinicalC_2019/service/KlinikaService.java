package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.MedicinskaSestra;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Sala;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class KlinikaService {

    @Autowired
    private OperacijaRepository operacijaRepository;

    @Autowired
    private PregledRepository pregledRepository;

    @Autowired
    private KlinikaRepository klinikaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private MedicinskaSestraRepository medicinskaSestraRepository;

    @Autowired
    private LekarRepository lekarRepository;

    public Klinika findByNaziv(String naziv) { return klinikaRepository.findByNaziv(naziv); }

    public Klinika findById(int id) { return klinikaRepository.findById(id); }

    public List<Klinika> findAll() { return klinikaRepository.findAll(); }

    @Transactional
    public Klinika add(Klinika klinika){
        return klinikaRepository.save(klinika);
    }

    @Transactional
    public Klinika update(Klinika klinika) {
        Klinika p = klinikaRepository.findById(klinika.getId()).get();
        if (p == null) {
            return null;
        }
        p.setAdresa(klinika.getAdresa());
        p.setDrzava(klinika.getDrzava());
        p.setEmail(klinika.getEmail());
        p.setGrad(klinika.getGrad());
        p.setNaziv(klinika.getNaziv());
        p.setKontaktTelefon(klinika.getKontaktTelefon());
        p.setOcena(klinika.getOcena());
        klinikaRepository.save(p);
        return p;
    }

    public List<MedicinskaSestra> findMedSestre(Long id){
        Klinika k = klinikaRepository.findById(id).get();

        medicinskaSestraRepository.findByKlinika(k);

        if(medicinskaSestraRepository.findByKlinika(k) == null) {
            return null;
        }
        return medicinskaSestraRepository.findByKlinika(k);
    }

    public List<Lekar> findLekari(Long id){
        Klinika k = klinikaRepository.findById(id).get();

        lekarRepository.findByKlinika(k);

        if(lekarRepository.findByKlinika(k) == null) {
            return null;
        }
        return lekarRepository.findByKlinika(k);
    }

    public List<Sala> findSale(Long id) {
        Klinika k = klinikaRepository.findById(id).get();

        if (k != null){
            return salaRepository.findByKlinikaId(id);
        }
        return null;
    }

    @Transactional
    public Sala remove(Long id){
//        if (operacijaRepository.findBySalaId(id) != null)
//            operacijaRepository.deleteBySalaId(id);
//        if (pregledRepository.findBySalaId(id) != null)
//            pregledRepository.deleteBySalaId(id);
        if(salaRepository.findById(id).isPresent()) {
            salaRepository.deleteById(id);
            return null;
        }
        return null;
    }
}
