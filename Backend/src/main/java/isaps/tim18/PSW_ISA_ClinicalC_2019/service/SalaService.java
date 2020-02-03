package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.Message;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Sala;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KlinikaRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.OperacijaRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PregledRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaService {

    @Autowired
    SalaRepository salaRepository;

    @Autowired
    KlinikaRepository klinikaRepository;

    @Autowired
    OperacijaRepository operacijaRepository;

    @Autowired
    PregledRepository pregledRepository;

    public List<Sala> findSale(Long id) {
        if (klinikaRepository.findById(id).isPresent()){
            return salaRepository.findByKlinikaId(id);
        }
        return new ArrayList<Sala>();
    }

    public Message remove(Long id){
        if(salaRepository.findById(id).isPresent()) {
            if (operacijaRepository.findBySalaId(id).size() != 0 || pregledRepository.findBySalaIdAndStatus(id).size() != 0){
                return new Message("Ne možete obrisati salu koja ima zakazane preglede ili operacije.");
            }
            else {
                salaRepository.deleteById(id);
                return new Message("Sala uspešno obrisana!");
            }
        }
        return new Message("Tražena sala ne postoji!");
    }

    public Message addNovaSala(Sala sala) {

        if (klinikaRepository.findById(sala.getKlinika().getId()).isPresent()){
            Klinika k = klinikaRepository.findById(sala.getKlinika().getId()).get();
            if (salaRepository.findByNazivAndKlinikaAndBroj(sala.getNaziv(), sala.getKlinika(), sala.getBroj()) == null) {

                Sala s = new Sala();

                s.setNaziv(sala.getNaziv());
                s.setBroj(sala.getBroj());
                s.setKlinika(k);

                salaRepository.saveAndFlush(s);

                return new Message("Uspešno dodata nova sala!");
            }
            else{
                return new Message("Sala sa željenim imenom već postoji!");
            }
        }
        return new Message("Klinika ne postoji!");
    }

    public Message izmeniSalu(Sala sala){
        if(salaRepository.findById(sala.getId()).isPresent()) {
            if (salaRepository.findByNazivAndKlinikaAndBroj(sala.getNaziv(), sala.getKlinika(), sala.getBroj()) == null) {

                Sala s = salaRepository.findById(sala.getId()).get();
                s.setNaziv(sala.getNaziv());
                s.setBroj(sala.getBroj());

                salaRepository.save(s);

                return new Message("Uspešno izmenjeni podaci o sali!");
            }
            else{
                return new Message("Sala sa željenim imenom već postoji!");
            }
        }
        return new Message("Tražena sala ne postoji!");
    }


}
