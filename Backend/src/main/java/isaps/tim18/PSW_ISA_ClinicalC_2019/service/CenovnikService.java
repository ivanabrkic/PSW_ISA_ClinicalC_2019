package isaps.tim18.PSW_ISA_ClinicalC_2019.service;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.Message;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Cenovnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Operacija;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.CenovnikRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KlinikaRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.OperacijaRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PregledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Cenovnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.CenovnikRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CenovnikService {

    @Autowired
    CenovnikRepository cenovnikRepository;

    @Autowired
    KlinikaRepository klinikaRepository;

    @Autowired
    OperacijaRepository operacijaRepository;

    @Autowired
    PregledRepository pregledRepository;

    public List<Cenovnik> getTipovi(Long idKlinike){
        return cenovnikRepository.findByKlinikaId(idKlinike);
    }

    public Message removeTip(Long idTipa){

        if (!cenovnikRepository.findById(idTipa).isPresent()){
            return new Message("Tip pregleda ne postoji!");
        }

        List<Operacija> operacije = operacijaRepository.findByCenovnikId(idTipa);
        List<Pregled> pregledi = pregledRepository.findByCenovnikId(idTipa);

        if (operacije.isEmpty() && pregledi.isEmpty()){
            cenovnikRepository.deleteById(idTipa);
            return new Message("Uspešno obrisan tip pregleda!");
        }

        return new Message("Ne možete obrisati tip pregleda!");
    }

    public Message registerTip(Cenovnik cenovnik){

        if (klinikaRepository.findById(cenovnik.getKlinika().getId()).isPresent()) {
            if (!cenovnikRepository.findByNazivAndSpecijalizacijaAndKlinikaAndCena(cenovnik.getNaziv(), cenovnik.getSpecijalizacija(), cenovnik.getKlinika().getId(), cenovnik.getCena()).isEmpty()) {
                return new Message("Tip pregleda već postoji u sklopu Vaše klinike!");
            }
            cenovnikRepository.save(cenovnik);
            return new Message("Uspešno ste dodali novi tip pregleda!");
        }

        return new Message("Klinika ne postoji!");
    }

    public Message izmeniTip(Cenovnik cenovnik){
        if (cenovnikRepository.findById(cenovnik.getId()).isPresent() && klinikaRepository.findById(cenovnik.getKlinika().getId()).isPresent()){
            if(cenovnikRepository.findByNazivAndSpecijalizacijaAndKlinikaAndCena(cenovnik.getNaziv(), cenovnik.getSpecijalizacija(), cenovnik.getKlinika().getId(), cenovnik.getCena()).isEmpty()){
                Cenovnik c = cenovnikRepository.findById(cenovnik.getId()).get();
                c.setCena(cenovnik.getCena());
                c.setSpecijalizacija(cenovnik.getSpecijalizacija());
                c.setNaziv(cenovnik.getNaziv());
                cenovnikRepository.save(c);

                return new Message("Uspešno ste izmenili tip pregleda!");
            }
            return new Message("Tip pregleda sa željenim nazivom i specijalizacijom već postoji u sklopu Vaše klinike!");
        }

        return new Message("Tip pregleda ne postoji!");
    }

    public String findByNaziv(String ime){
        List<Cenovnik> found=cenovnikRepository.findByNaziv(ime);
        return found.get(0).getSpecijalizacija();
    }

    public float findByNazivAndKlinikaId(String n,Long id) {
        return cenovnikRepository.findByNazivAndKlinikaId(n, id).getCena();
    }

    public List<Cenovnik> findAll(){
        return cenovnikRepository.findAll();
    }
}