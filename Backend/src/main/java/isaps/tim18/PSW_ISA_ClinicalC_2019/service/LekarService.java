package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Zahtev;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.CenovnikRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.LekarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LekarService {

    @Autowired
    private LekarRepository lekarRepository;

    @Autowired
    private CenovnikRepository cenovnikRepository;

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

    public List<Lekar> findAllByKlinika(Klinika k){return lekarRepository.findByKlinika(k);}

    public List<Lekar> findByImeAndPrezime(String ime, String prezime) {
    return lekarRepository.findByImeAndPrezimeAllIgnoringCase(ime, prezime);
    }

    @Transactional
    public Lekar update(Lekar lekar) {
        Lekar p = lekarRepository.findByJbo(lekar.getJbo());
        if (p == null) {
            return null;
        }
        p.setAdresa(lekar.getAdresa());
        p.setDrzava(lekar.getDrzava());
        p.setEmail(lekar.getEmail());
        p.setGrad(lekar.getGrad());
        p.setIme(lekar.getIme());
        p.setPrezime(lekar.getPrezime());
        p.setKontaktTelefon(lekar.getKontaktTelefon());
        p.setLozinka(lekar.getLozinka());
        lekarRepository.save(p);
        return p;
    }

    @Transactional
    public Lekar add(Lekar lekar){
        return lekarRepository.save(lekar);
    }

    @Transactional
    public Lekar remove(Long id){
        lekarRepository.deleteById(id);

        if(!lekarRepository.findById(id).isPresent()) {
            return null;
        }
        return lekarRepository.findById(id).get();
    }

    public List<Lekar> getSlobodniLekari(Zahtev zahtev){

        String vremeZakazivanja = "";

        int osamSati = 8 * 60;
        int sesnaestSati = 16 * 60;
        int dvanaestSati = 24 * 60;

        int satPocetak = Integer.parseInt(zahtev.getPocetak().split(":")[0]) * 60;
        int minutPocetak = Integer.parseInt(zahtev.getPocetak().split(":")[1]);

        int satKraj = Integer.parseInt(zahtev.getKraj().split(":")[0]) * 60;
        int minutKraj = Integer.parseInt(zahtev.getKraj().split(":")[1]);

        int pocetak = satPocetak + minutPocetak;
        int kraj  = satKraj + minutKraj;

        if (pocetak == 0){
            vremeZakazivanja = "Treca smena od 00:00 do 8:00";
        }
        else if (kraj == 0) {
            vremeZakazivanja = "Druga smena od 16:00 do 00:00";
        }
        else if (pocetak >= osamSati && kraj <= sesnaestSati){
            vremeZakazivanja = "Prva smena od 8:00 do 16:00";
        }
        else if (pocetak >= 0 && kraj <= osamSati){
            vremeZakazivanja = "Treca smena od 00:00 do 8:00";
        }
        else if (pocetak >= sesnaestSati){
            vremeZakazivanja = "Druga smena od 16:00 do 00:00";
        }

        String specijalizacija = cenovnikRepository.findById(zahtev.getIdStavke()).get().getSpecijalizacija();

        List<Long> radeUToVreme = lekarRepository.daLiJeRadnoVreme(zahtev.getIdKlinike(), vremeZakazivanja, specijalizacija);

        HashMap<Long, Long> slobodni = new HashMap<Long, Long>();
        for (Long id : radeUToVreme){
            if (lekarRepository.imaOperacije(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()
                && lekarRepository.imaPreglede(id, zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()){
                slobodni.put(id, id);
            }
        }

        List<Lekar> sviLekari = lekarRepository.findAll();
        List<Lekar> odredjeni = new ArrayList<>();
        for (Lekar l : sviLekari){
            if(slobodni.containsKey(l.getId())){
                odredjeni.add(l);
            }
        }

        return odredjeni;
    }

    public boolean lekarSlobodan(Zahtev zahtev){
        Lekar lekar = lekarRepository.findByJbo(zahtev.getPosiljalacJbo());
        if (lekarRepository.imaOperacije(lekar.getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()
                && lekarRepository.imaPreglede(lekar.getId(), zahtev.getDatum(), zahtev.getPocetak(), zahtev.getKraj()).isEmpty()){
            return true;
        }
        return false;
    }

}
