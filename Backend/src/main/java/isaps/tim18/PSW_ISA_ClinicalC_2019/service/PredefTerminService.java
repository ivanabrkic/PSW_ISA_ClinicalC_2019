package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.Message;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.TerminDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Cenovnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Sala;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PredefTerminService {

    @Autowired
    PacijentRepository pacijentRepository;

    @Autowired
    OperacijaRepository operacijaRepository;

    @Autowired
    PregledRepository pregledRepository;

    @Autowired
    LekarRepository lekarRepository;

    @Autowired
    CenovnikRepository cenovnikRepository;

    @Autowired
    SalaRepository salaRepository;

    public List<Cenovnik> getCenovnikKlinika(Long idKlinike){
        return cenovnikRepository.findByKlinikaId(idKlinike);
    }

    public List<Lekar> getAllLekariForTip(Long idTipa){

        Cenovnik tip = cenovnikRepository.findById(idTipa).get();

        return lekarRepository.findBySpecijalizacijaAndKlinika(tip.getSpecijalizacija(), tip.getKlinika());
    }

    public List<TerminDTO> getAllTerminiAndSale(Long idLekara, Integer trajanje){

        Long idKlinike = lekarRepository.findById(idLekara).get().getKlinika().getId();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);

        start = start.plusDays(1);

        LocalDateTime end = start.plusMonths(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.YYYY.");

        List<TerminDTO> listaTermina = new ArrayList<>();

        for (LocalDateTime date = start; date.isBefore(end); date = date.plusMinutes(15)) {
            if (date.toLocalDate() == date.plusMinutes(trajanje).toLocalDate()) {
                String datum = formatter.format(date.toLocalDate());
                String pocetak = date.toLocalTime().toString();
                String kraj = date.plusMinutes(trajanje).toLocalTime().toString();

                if (isLekarRadnoVreme(idLekara, pocetak, kraj) && lekarSlobodan(idLekara, datum, pocetak, kraj) && !slobodneSale(idKlinike, datum, pocetak, kraj).isEmpty()
                        && (date.getDayOfWeek().name() != "SUNDAY")) {
                    TerminDTO terminDTO = new TerminDTO();
                    terminDTO.setDatum(datum);
                    terminDTO.setPocetak(pocetak);
                    terminDTO.setKraj(kraj);

                    List<Sala> listaSlobodnihSala = slobodneSale(idKlinike, datum, pocetak, kraj);
                    terminDTO.setSale(listaSlobodnihSala);

                    listaTermina.add(terminDTO);
                }

                if (listaTermina.size() == 150) {
                    return listaTermina;
                }
            }
        }

        return listaTermina;
    }

    public List<Sala> slobodneSale(Long idKlinike, String datum, String pocetak, String kraj){

        List<Sala> sveSale = salaRepository.findByKlinikaId(idKlinike);
        List<Sala> slobodne = new ArrayList<>();

        for (Sala s : sveSale){
            if (salaRepository.checkIfSalaZauzeta(s.getId(), datum, pocetak, kraj).isEmpty()){
                slobodne.add(s);
            }
        }

        return slobodne;
    }

    public boolean lekarSlobodan(Long lekarId, String datum, String pocetak, String kraj){
        if (lekarRepository.imaOperacije(lekarId, datum, pocetak, kraj).isEmpty()
                && lekarRepository.imaPreglede(lekarId, datum, pocetak, kraj).isEmpty()){
            return true;
        }
        return false;
    }

    public boolean isLekarRadnoVreme(Long idLekara, String pocetakString, String krajString){
        String vremeZakazivanja = "";

        int osamSati = 8 * 60;
        int sesnaestSati = 16 * 60;
        int dvanaestSati = 24 * 60;

        int satPocetak = Integer.parseInt(pocetakString.split(":")[0]) * 60;
        int minutPocetak = Integer.parseInt(pocetakString.split(":")[1]);

        int satKraj = Integer.parseInt(krajString.split(":")[0]) * 60;
        int minutKraj = Integer.parseInt(krajString.split(":")[1]);

        int pocetak = satPocetak + minutPocetak;
        int kraj = satKraj + minutKraj;

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
        else if (pocetak >= sesnaestSati && kraj <= 0){
            vremeZakazivanja = "Druga smena od 16:00 do 00:00";
        }

        Lekar lekar = lekarRepository.findById(idLekara).get();

        return lekar.getRadnoVreme().equals(vremeZakazivanja);
    }


    public Message dodajTermin(Pregled pregled) {

        if (salaRepository.findById(pregled.getSala().getId()).isPresent() && lekarRepository.findById(pregled.getLekar().getId()).isPresent() && cenovnikRepository.findById(pregled.getCenovnik().getId()).isPresent()){
            Sala s = salaRepository.findById(pregled.getSala().getId()).get();
            Lekar l = lekarRepository.findById(pregled.getLekar().getId()).get();
            Cenovnik c = cenovnikRepository.findById(pregled.getCenovnik().getId()).get();

            Pregled p = new Pregled();
            p.setPocetak(pregled.getPocetak());
            p.setKraj(pregled.getKraj());
            p.setDatum(pregled.getDatum());
            p.setLekar(l);
            p.setPacijent(null);
            p.setCenovnik(c);
            p.setSala(s);
            p.setStatus(pregled.getStatus());
            p.setPopust(pregled.getPopust());

            pregledRepository.save(p);

            return new Message("Uspešno dodat predefinisan termin!");

        }
        return new Message("Nemoguće je dodati željeni termin!");
    }
}
