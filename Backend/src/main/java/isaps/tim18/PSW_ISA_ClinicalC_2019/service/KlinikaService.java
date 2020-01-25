package isaps.tim18.PSW_ISA_ClinicalC_2019.service;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
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
    public Klinika remove(Long id){
        Klinika klinika = new Klinika();
        if(salaRepository.findById(id).isPresent()) {
            if (operacijaRepository.findBySalaId(id).size() != 0 || pregledRepository.findBySalaId(id).size() != 0){
                System.out.println(id);
                System.out.println(operacijaRepository.findBySalaId(id).size());
                System.out.println(pregledRepository.findBySalaId(id).size());
                klinika.setNaziv("Ne možete obrisati salu koja ima zakazane preglede ili operacije.");
            }
            else {
                salaRepository.deleteById(id);
                klinika.setNaziv("Sala uspešno obrisana!");
            }
        }
        else{
            klinika.setNaziv("Tražena sala ne postoji!");
        }
        return klinika;
    }

    public Sala addNovaSala(String naziv, Long idKlinike) {

        Klinika k = klinikaRepository.findById(idKlinike).get();

        Sala s = new Sala();

        s.setNaziv(naziv);
        s.setKlinika(k);

        salaRepository.saveAndFlush(s);

        Sala poruka = new Sala();
        poruka.setNaziv("Uspešno dodata nova sala!");

        return poruka;
    }

    public List<Operacija> getOperacije(Sala sala) {
        return operacijaRepository.findBySalaId(sala.getId());
    }

    public List<Pregled> getPregledi(Sala sala) {
        return pregledRepository.findBySalaId(sala.getId());
    }
}
