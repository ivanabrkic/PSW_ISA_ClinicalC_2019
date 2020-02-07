package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaLekarKlinikaDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Operacija;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.OperacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OperacijaService {

    @Autowired
    OperacijaRepository operacijaRepo;

    public List<Operacija> findByPacijentId(Long id) { return operacijaRepo.findByPacijentId(id); }

    public Optional<Operacija> findById(Long id) { return operacijaRepo.findById(id); }

    public List<posetaLekarKlinikaDTO> findInfo(Long id) {
        return operacijaRepo.findInfo(id);
    }
    
//    public List<Operacija> findHistoryByPacijentId(Long id) {
//    	
//    	Date date = new Date();
//		String modifiedDate= new SimpleDateFormat("d.M.yyyy.").format(date);
//		System.out.print(modifiedDate);
//
//		return operacijaRepo.findHistoryByPacijentId(id);
//	}
}
