package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.LockTimeoutException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Korisnik;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Zahtev;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.ZahtevRepository;

@Service
public class ZahtevService {
	
	@Autowired
	private  ZahtevRepository zahtevRepo;

	public static boolean isOverlapping(String pocetak1, String kraj1,String pocetak2, String kraj2) throws ParseException {
		DateFormat dateFormat= new SimpleDateFormat("hh:mm");
		if (dateFormat.parse(pocetak1).compareTo(dateFormat.parse(kraj2))<0 && dateFormat.parse(pocetak2).compareTo(dateFormat.parse(kraj1))<0){
			return true;
		}
		return false;
	}

	@Transactional
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	    public Zahtev add(Zahtev zahtev) throws ParseException {

			try{
				List<Zahtev> sviZahtevi=zahtevRepo.findAll();
				for (Zahtev z:sviZahtevi
				) {
					if(z.getJboLekara().equals(zahtev.getJboLekara()) && isOverlapping(z.getPocetak(),z.getKraj(),zahtev.getPocetak(),zahtev.getKraj())){
						throw new ValidationException("Lekar je zauzet u izabranom terminu!");
					}else{
						return zahtevRepo.save(zahtev);
					}

				}
			}
			catch(LockTimeoutException e){
				throw new ValidationException("Doslo je do greske prilikom dodavanja zahteva");
			}
		return null;
	    }
	 

}
