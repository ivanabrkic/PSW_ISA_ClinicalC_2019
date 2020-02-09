package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.DateValueDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.PrihodDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Operacija;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.OperacijaRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PregledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class IzvestajKlinikeService {

    @Autowired
    private OperacijaRepository operacijaRepository;

    @Autowired
    private PregledRepository pregledRepository;

    public PrihodDTO prihodDatum(PrihodDTO prihodDTO){

        int godinaPocetak = Integer.parseInt(prihodDTO.getPocetak().split("\\.")[2]);
        int mesecPocetak = Integer.parseInt(prihodDTO.getPocetak().split("\\.")[1]);
        int danPocetak = Integer.parseInt(prihodDTO.getPocetak().split("\\.")[0]);

        int godinaKraj = Integer.parseInt(prihodDTO.getKraj().split("\\.")[2]);
        int mesecKraj = Integer.parseInt(prihodDTO.getKraj().split("\\.")[1]);
        int danKraj = Integer.parseInt(prihodDTO.getKraj().split("\\.")[0]);

        LocalDateTime start = LocalDateTime.of(godinaPocetak, mesecPocetak, danPocetak, 0, 0, 0);

        LocalDateTime end = LocalDateTime.of(godinaKraj, mesecKraj, danKraj, 0, 0, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.YYYY.");

        float prihod = 0;

        for (LocalDateTime date = start; date.isBefore(end); date = date.plusDays(1)) {
            List<Pregled> pregledi = pregledRepository.findByDatumAndStatusAndKlinikaId(formatter.format(date.toLocalDate()), "Završen", prihodDTO.getIdKlinike());
            List<Operacija> operacije = operacijaRepository.findByDatumAndStatusAndKlinikaId(formatter.format(date.toLocalDate()), "Završen", prihodDTO.getIdKlinike());

            System.out.println(formatter.format(date.toLocalDate()));
            System.out.println(pregledi.size());

            if(!pregledi.isEmpty()){
                for (Pregled pregled : pregledi){
                    if(pregled.getPopust() == null || pregled.getPopust() == 0){
                        prihod += pregled.getCenovnik().getCena();
                    }
                    else{
                        prihod += (pregled.getCenovnik().getCena() - pregled.getCenovnik().getCena() * pregled.getPopust());
                    }
                }
            }

            if(!operacije.isEmpty()){
                for (Operacija operacija : operacije){
                    prihod += operacija.getCenovnik().getCena();
                }
            }

        }

        prihodDTO.setPrihod(prihod);

        return prihodDTO;
    }

    public List<DateValueDTO> dnevniNivoBrojPregleda(Long idKlinike) {
        LocalDateTime start = LocalDateTime.of(2020, 1, 1, 0, 0, 0);

        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 0, 0, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.YYYY.");

        List<DateValueDTO> dnevniBrojLista = new ArrayList<>();

        for (LocalDateTime date = start; date.isBefore(end); date = date.plusDays(1)) {

            int dnevniBroj = 0;

            List<Pregled> pregledi = pregledRepository.findByDatumAndStatusAndKlinikaId(formatter.format(date.toLocalDate()), "Završen", idKlinike);

            if(!pregledi.isEmpty()){
                dnevniBroj = pregledi.size();
            }

            DateValueDTO dateValueDTO = new DateValueDTO(formatter.format(date.toLocalDate()), dnevniBroj);
            dnevniBrojLista.add(dateValueDTO);
        }
        return  dnevniBrojLista;
    }

    public List<DateValueDTO> nedeljniBrojPregleda(Long idKlinike) {
        LocalDateTime start = LocalDateTime.of(2020, 1, 1, 0, 0, 0);

        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 0, 0, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.YYYY.");

        List<DateValueDTO> nedeljniBrojLista = new ArrayList<>();

        for (LocalDateTime date = start; date.isBefore(end); date = date.plusDays(7)) {

            int nedeljniBroj = 0;

            for(LocalDateTime date2 = date; date2.isBefore(date.plusDays(7)); date2 = date2.plusDays(1)){
                List<Pregled> pregledi = pregledRepository.findByDatumAndStatusAndKlinikaId(formatter.format(date2.toLocalDate()), "Završen", idKlinike);
                if(!pregledi.isEmpty()){
                    nedeljniBroj += pregledi.size();
                }
            }

            DateValueDTO dateValueDTO = new DateValueDTO(formatter.format(date.toLocalDate()), nedeljniBroj);
            nedeljniBrojLista.add(dateValueDTO);
        }
        return  nedeljniBrojLista;
    }

    public List<DateValueDTO> mesecniBrojPregleda(Long idKlinike) {
        LocalDateTime start = LocalDateTime.of(2020, 1, 1, 0, 0, 0);

        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 0, 0, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.YYYY.");

        List<DateValueDTO> mesecniBrojLista = new ArrayList<>();

        for (LocalDateTime date = start; date.isBefore(end); date = date.plusMonths(1)) {

            int mesecniBroj = 0;

            for(LocalDateTime date2 = date; date2.isBefore(date.plusMonths(1)); date2 = date2.plusDays(1)){
                List<Pregled> pregledi = pregledRepository.findByDatumAndStatusAndKlinikaId(formatter.format(date2.toLocalDate()), "Završen", idKlinike);
                if(!pregledi.isEmpty()){
                    mesecniBroj += pregledi.size();
                }
            }

            DateValueDTO dateValueDTO = new DateValueDTO(formatter.format(date.toLocalDate()), mesecniBroj);
            mesecniBrojLista.add(dateValueDTO);
        }
        return  mesecniBrojLista;
    }

}
