package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Lekar;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.oceneLekari;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.LekarRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.LekariOceneRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PacijentRepository;

@Service 
public class oceneLekariService {
	
	@Autowired	
	private LekariOceneRepository oceneLekariRepo;
	
	@Autowired
	private LekarRepository lekariRepo;
	
	@Autowired
	private PacijentRepository pacijentRepo;
	
	public List<oceneLekari> findAll(){
		return oceneLekariRepo.findAll();
	}
	
	public List<oceneLekari> findByLekarId(Long id){
		return this.oceneLekariRepo.findByLekarId(id);
	}
	
	public oceneLekari findByPacijentAndLekarId(Long id,Long idLekara) {
		return this.oceneLekariRepo.findByPacijentIdAndLekarId(id,idLekara);
	}
	
	//Metoda za dodavanje nove ocene Lekara
	@Transactional
	public void add(oceneLekari oceneLekara) {
		
		Optional<Pacijent> pacijent = pacijentRepo.findById(oceneLekara.getPacijent().getId());
		Optional<Lekar> lekar = lekariRepo.findById(oceneLekara.getLekar().getId());
		
		//Unos nove ocene u tabelu ocena lekara
		if(pacijent.isPresent() && lekar.isPresent()) {
			//oceneLekariRepo.save(new oceneLekari(pacijent.get(), lekar.get(), ocena));
			oceneLekariRepo.save(oceneLekara);
		
		
		//Nakon unete ocene, osvezi ocenu u tabeli lekar
		List<oceneLekari> sveOceneLekara=oceneLekariRepo.findByLekarId(oceneLekara.getLekar().getId());
		float sum=0;
		float count=sveOceneLekara.size();
		for (oceneLekari touple: sveOceneLekara) {
			
			sum+=touple.getOcena();
		}
		float novaOcena=sum/count;
		System.out.print("novaOcena");
		System.out.print(novaOcena);
		lekar.get().setOcena(novaOcena);
		lekariRepo.save(lekar.get());
		}
		
	}
	
	@Transactional
	public void update(oceneLekari ocenaLekara, float ocena) {
		
		//Osvezavanje ocene u tabeli ocena lekara
		oceneLekari postojecaOcena=oceneLekariRepo.findByPacijentIdAndLekarId(ocenaLekara.getPacijent().getId(), ocenaLekara.getLekar().getId());
		postojecaOcena.setOcena(ocena);
		oceneLekariRepo.save(postojecaOcena);
		
		//Osvezavanje ocene u tabeli Lekar
		Optional<Lekar> lekar=lekariRepo.findById(ocenaLekara.getLekar().getId());
		if(lekar.isPresent()) {
			
			List<oceneLekari> sveOceneLekara=oceneLekariRepo.findByLekarId(lekar.get().getId());
			float sum=0;
			float count=sveOceneLekara.size();
			for (oceneLekari touple: sveOceneLekara) {
				
				sum+=touple.getOcena();
			}
			float novaOcena=sum/count;
			System.out.print("novaOcena");
			System.out.print(novaOcena);
			lekar.get().setOcena(novaOcena);
			lekariRepo.save(lekar.get());
		}
		
	}


}
