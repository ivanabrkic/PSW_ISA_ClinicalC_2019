package isaps.tim18.PSW_ISA_ClinicalC_2019.service;

import java.util.List;
import java.util.Optional;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.posetaLekarKlinikaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isaps.tim18.PSW_ISA_ClinicalC_2019.dto.predefDTO;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pacijent;
import isaps.tim18.PSW_ISA_ClinicalC_2019.model.Pregled;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.KorisnikRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PacijentRepository;
import isaps.tim18.PSW_ISA_ClinicalC_2019.repository.PregledRepository;

@Service
public class PregledService {
	
	@Autowired
	PregledRepository pregledRepo;
	
	@Autowired
	PacijentRepository pacRepo;
	
	
	public Optional<Pregled> findById(Long id) {return pregledRepo.findById(id);}

	public List<Pregled> findByPacijentId(Long id) {return pregledRepo.findByPacijentId(id);}

	public posetaLekarKlinikaDTO findInfo(Long id){return pregledRepo.findInfo(id);}

	public Optional<Pregled> update(predefDTO pregled) {
		
		System.out.println("Usao si ovde");
		
		Optional<Pregled> pronadjen=pregledRepo.findById(pregled.getId());
		if (pronadjen!=null) {
			Pregled p=pronadjen.get();
			p.setStatus("Zakazan");
			Optional<Pacijent> pac=pacRepo.findById(pregled.getPacijent_id());
			p.setPacijent(pac.get());
			pregledRepo.save(p);
			return pronadjen;
		}
		
		return null;
	}

}
