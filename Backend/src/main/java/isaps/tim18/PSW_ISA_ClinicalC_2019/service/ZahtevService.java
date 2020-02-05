package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Zahtev;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.ZahtevRepository;

@Service
public class ZahtevService {
	
	@Autowired
	private  ZahtevRepository zahtevRepo;
	
	 @Transactional
	    public Zahtev add(Zahtev zahtev){
	        return zahtevRepo.save(zahtev);
	    }
	 

}
