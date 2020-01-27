package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.OperacijaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PregledDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.*;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public Sala addNovaSala(String naziv, String broj, Long idKlinike) {
        Sala poruka = new Sala();
        Klinika k = klinikaRepository.findById(idKlinike).get();

        if (salaRepository.findByNazivAndKlinikaAndBroj(naziv, k, broj) == null) {

            Sala s = new Sala();

            s.setNaziv(naziv);
            s.setBroj(broj);
            s.setKlinika(k);

            salaRepository.saveAndFlush(s);

            poruka.setNaziv("Uspešno dodata nova sala!");
        }
        else{
            poruka.setNaziv("Sala sa željenim imenom već postoji!");
        }

        return poruka;
    }

    public List<OperacijaDTO> getOperacije(Long id) {
        List<OperacijaDTO> operacijeDTO = operacijaRepository.findBySalaId(id);

        for (OperacijaDTO opDTO:operacijeDTO
             ) {
            List<Lekar> lekari = findLekariOperacije(opDTO.getDatum(), opDTO.getPocetak(), opDTO.getKraj(), id);
            List<String> jboLekara = new ArrayList<String>();
            for (Lekar lekar: lekari
                 ) {
                jboLekara.add(lekar.getJbo());
            }
            opDTO.setJboLekara(jboLekara);
        }

        return operacijeDTO;
    }

    public List<PregledDTO> getPregledi(Long id) {
        return pregledRepository.findBySalaId(id);
    }

    public List<Lekar> findLekariOperacije(String datum, String pocetak, String kraj, Long id){
        return operacijaRepository.findLekareOperacije(datum, pocetak, kraj, id);
    }
}
