package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Klinika;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.oceneKlinike;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.oceneLekari;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KlinikaRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KlinikeOceneRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.LekariOceneRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PacijentRepository;

@Service 
public class oceneKlinikeService {
	
	@Autowired	
	private KlinikeOceneRepository oceneKlinikeRepo;
	
	@Autowired 
	private KlinikaRepository klinikaRepo;
	
	@Autowired
	private PacijentRepository pacijentRepo;
	
	public List<oceneKlinike> findAll(){
		return oceneKlinikeRepo.findAll();
	}
	
	public List<oceneKlinike> findByKlinikaId(Long id){
		return this.oceneKlinikeRepo.findByKlinikaId(id);
	}
	
	public oceneKlinike findByPacijentAndKlinikaId(Long id,Long idl) {
		return this.oceneKlinikeRepo.findByPacijentIdAndKlinikaId(id,idl);
	}
	
	//Metoda za dodavanje nove ocene Lekara
		@Transactional
		public void add(oceneKlinike ocenaklinike) {
			
			Optional<Pacijent> pacijent = pacijentRepo.findById(ocenaklinike.getPacijent().getId());
			Optional<Klinika> klinika = klinikaRepo.findById(ocenaklinike.getKlinika().getId());
			
			//Unos nove ocene u tabelu ocena klinike
			if(pacijent.isPresent() && klinika.isPresent()) {
				oceneKlinikeRepo.save(ocenaklinike);
			
			
			//Nakon unete ocene, osvezi ocenu u tabeli klinika
			List<oceneKlinike> sveOceneKlinike=oceneKlinikeRepo.findByKlinikaId(ocenaklinike.getKlinika().getId());
			float sum=0;
			float count=sveOceneKlinike.size();
			for (oceneKlinike touple: sveOceneKlinike) {
				
				sum+=touple.getOcena();
			}
			float novaOcena=sum/count;
			klinika.get().setOcena(novaOcena);
			klinikaRepo.save(klinika.get());
			}
			
		}
		
		@Transactional
		public void update(oceneKlinike ocenaKlinike, float ocena) {
			
			//Osvezavanje ocene u tabeli ocena klinika
			oceneKlinike postojecaOcena=oceneKlinikeRepo.findByPacijentIdAndKlinikaId(ocenaKlinike.getPacijent().getId(), ocenaKlinike.getKlinika().getId());
			postojecaOcena.setOcena(ocena);
			oceneKlinikeRepo.save(postojecaOcena);
			
			//Osvezavanje ocene u tabeli Klinika
			Optional<Klinika> klinika=klinikaRepo.findById(ocenaKlinike.getKlinika().getId());
			if(klinika.isPresent()) {
				
				List<oceneKlinike> sveOceneKlinike=oceneKlinikeRepo.findByKlinikaId(klinika.get().getId());
				float sum=0;
				float count=sveOceneKlinike.size();
				for (oceneKlinike touple: sveOceneKlinike) {
					
					sum+=touple.getOcena();
				}
				float novaOcena=sum/count;
				klinika.get().setOcena(novaOcena);
				klinikaRepo.save(klinika.get());
			}
			
		}

		
}